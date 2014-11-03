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
	
	/**
	 * The constructor for a player.
	 * @param location The starting location of the player.
	 * @param courses The courses the player can take.
	 */
	public Player(Room location, List<Course> courses){
		FinishedCourses = initializeFinishedCourses(courses);
		UnfinishedCourses = initializeUnfinishedCourses(FinishedCourses, courses);
		CurrentLocation = location;
		CurrentBackpack = new Backpack();
	}

	private List<Course> initializeFinishedCourses(List<Course> courses) {
		List<Course> finishedCourses = new ArrayList<Course>();
		int coursesSize = courses.size();
		// TEST
		if(coursesSize < 1) {
			return null;
		}
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

	/**
	 * Will move the player in directions if there is a room in that direction
	 * and the door there is unlocked.
	 * @param direction The direction we want to move the player.
	 * @return If the move succeeded or not.
	 */
	public boolean walk(Direction direction){
		if(this.getCurrentLocation().getRoomInDirection(direction) == null) {
			System.out.println("There's no door there.");
			return false;
		}
		if(this.getCurrentLocation().getRoomInDirection(direction).getUnlocked() == false) {
			System.out.println("The door is locked.");
			return false;
		}
		setCurrentLocation(CurrentLocation.getRoomInDirection(direction));
		return true;
	}
	
	/**
	 * Set the players location.
	 * @param  newLocation The new location of the player as a Room.
	 */
	public void setCurrentLocation(Room newLocation){
		CurrentLocation = newLocation;
	}

	/**
	 * Get the players current position
	 * @return The position as a Room.
	 */
	public Room getCurrentLocation(){
		return CurrentLocation;
	}
	
	/**
	 * Get the available space in the backpack of the player.
	 * @return Space in backpack as an Int.
	 */
	public int getBackpackCapacity(){
		return CurrentBackpack.getSpace();
	}
	
	/**
	 * Will put an item into the backpack of the player if there is room.
	 * @param item The item to add to the backpack.
	 */
	public void addItemToBackpack(Item item){
		CurrentBackpack.addItem(item);
	}
	
	/**
	 * Prints the items inside the backpack of the player.
	 */
	public void printBackpackInventory(){
		CurrentBackpack.printInventory();
	}

	/**
	 * Prints the courses the player have taken and can take.
	 * @param finished ?
	 */
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
	
	public void removeKeyFromBackpack(){
		CurrentBackpack.removeKey();
	}
	
	public boolean hasKey(){
		return CurrentBackpack.containsKey();
	}
	
}
