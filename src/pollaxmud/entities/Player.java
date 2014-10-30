package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;

//import com.sun.javafx.tk.PrintPipeline;

import java.util.Random;

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
	
	private List<Course> initializeUnfinishedCourses(List<Course> finishedCourses, List<Course> courses) {
		List<Course> unfinishedCourses = new ArrayList<Course>();
		for(Course course : courses){
			if(!courseExists(course.getName(), finishedCourses)){
				unfinishedCourses.add(course);
			}
		}
		return null;
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
		setCurrentLocation(CurrentLocation.getRoomNorth());
	}
	
	public void walkEast(){
		setCurrentLocation(CurrentLocation.getRoomEast());
	}
	
	public void walkSouth(){
		setCurrentLocation(CurrentLocation.getRoomSouth());
	}
	
	public void walkWest(){
		setCurrentLocation(CurrentLocation.getRoomWest());
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
	
}
