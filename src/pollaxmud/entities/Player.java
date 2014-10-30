package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import pollaxmud.Enums.Direction;
import pollaxmud.world.Room;

public class Player {

	private final int STARTING_HP = 60;
	private List<Course> UnfinishedCourses;
	private List<Course> FinishedCourses;
	private Backpack CurrentBackpack;
	private Room CurrentLocation;
	
	public Player(Room location, List<Course> courses){
		FinishedCourses = initializeFinishedCourses(courses);
		UnfinishedCourses = initializeUnfinishedCourses(FinishedCourses, courses);
		CurrentLocation = location;
		CurrentBackpack = new Backpack();
	}

	private List<Course> initializeFinishedCourses(List<Course> courses) {
		List<Course> finishedCourses = new ArrayList<Course>();
		int coursesSize = courses.size();
		Course courseToAdd = null;
		Random rand = new Random();
			
		while(getCoursesPoints(finishedCourses) < STARTING_HP){
			courseToAdd = courses.get(rand.nextInt(coursesSize));
			if(!courseExists(courseToAdd.getName(), finishedCourses)){
				finishedCourses.add(courseToAdd);
			}
		}
		return finishedCourses;
	}
	
	private List<Course> initializeUnfinishedCourses(List<Course> finishedCourses, List<Course> courses) {
		List<Course> unfinishedCourses = new ArrayList<Course>();
		for(Course course : courses){
			if(!courseExists(course.getName(), finishedCourses)){
				unfinishedCourses.add(course);
			}
		}
		return unfinishedCourses;
	}
	
	private int getCoursesPoints(List<Course> courses){
		int points = 0;
		for(Course course : courses){
			points += course.getHP();
		}
		return points;
	}
	
	private boolean courseExists(String courseName, List<Course> courses) {
		for(Course course : courses) {
			if(course.getName().equals(courseName)) {
				return true;
			}
		}
		return false;
	}

	public void walkNorth(){
		setCurrentLocation(CurrentLocation.getRoomInDirection(Direction.NORTH));
	}
	
	public void walkEast(){
		setCurrentLocation(CurrentLocation.getRoomInDirection(Direction.EAST));
	}
	
	public void walkSouth(){
		setCurrentLocation(CurrentLocation.getRoomInDirection(Direction.SOUTH));
	}
	
	public void walkWest(){
		setCurrentLocation(CurrentLocation.getRoomInDirection(Direction.WEST));
	}
	
	
	/**
	 * Set the players location to newLocation
	 *
	 * @param  newLocation The new location of the player.
	 */
	public void setCurrentLocation(Room newLocation){
		CurrentLocation = newLocation;
	}

	public Room getCurrentLocation(){
		return CurrentLocation;
	}
	
	public int getBackpackCapacity(){
		return CurrentBackpack.getSpace();
	}
	
	public void addItemToBackpack(Item item){
		CurrentBackpack.addItem(item);
	}
	
	public void printBackpackInventory(){
		CurrentBackpack.printInventory();
	}

	public void printCourses(boolean finished){
		System.out.println("---------------------------------------");
		List<Course> courses = finished ? FinishedCourses : UnfinishedCourses;
		int totalCredits = 0;
		System.out.printf("%-30s%-10s%-30s\n", "Name:", "Credits:", "Book:");
		for(Course course : courses){
			System.out.printf("%-30s%-10d%-30s\n", course.getAbbrName(), course.getHP(), course.getBookName());
			totalCredits += course.getHP();
		}
		System.out.printf("%-30s%-10d\n", "Total:", totalCredits);
	}
	
}
