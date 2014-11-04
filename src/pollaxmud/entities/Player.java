package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pollaxmud.enums.Direction;
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
		UnfinishedCourses = new ArrayList<Course>();//initializeUnfinishedCourses(FinishedCourses, courses);
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
	
	/*private List<Course> initializeUnfinishedCourses(List<Course> finishedCourses, List<Course> courses) {
		List<Course> unfinishedCourses = new ArrayList<Course>();
		for(Course course : courses){
			if(!courseExists(course.getName(), finishedCourses)){
				unfinishedCourses.add(course);
			}
		}
		return unfinishedCourses;
	}*/
	
	/**
	 * Return the number of credits the player have from 
	 * the finished courses.
	 * @return Finished credits as an int.
	 */
	public int getFinishedCredits() {
		return getCoursesPoints(this.FinishedCourses);
	}
	
	private int getCoursesPoints(List<Course> courses){
		int points = 0;
		for(Course course : courses){
			points += course.getHP();
		}
		return points;
	}
	
	public boolean isPassedCourse(Course chk_course) {
		for(Course course : FinishedCourses) {
			if (course == chk_course) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isUnfinishedCourse(Course chk_course) {
		for(Course course : UnfinishedCourses) {
			if (course == chk_course) {
				return true;
			}
		}
		return false;
	}
	
	public boolean moveCourse(Course course, boolean finished){
		if(finished && !courseExists(course.getName(), FinishedCourses) && courseExists(course.getName(), UnfinishedCourses)){
			this.FinishedCourses.add(course);
			this.UnfinishedCourses.remove(course);
			return true;
		}
		if(!finished && courseExists(course.getName(), FinishedCourses) && !courseExists(course.getName(), UnfinishedCourses)){
			this.UnfinishedCourses.add(course);
			this.FinishedCourses.remove(course);
			return true;
		}
		return false;
	}
	
	private boolean courseExists(String courseName, List<Course> courses) {
		if(courses != null) {
			for(Course course : courses) {
				if(course.getName().equals(courseName)) {
					return true;
				}
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
	public boolean addItemToBackpack(Item item){
		if(CurrentBackpack.addItem(item)){
			return true;
		}
		return false;
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
		if(!finished && courses.isEmpty()){
			System.out.println("You dont have any unfinished courses!");
			System.out.println("Look for teachers to enroll to new courses if you need more credits for your exam.");
			return;
		}
		int totalCredits = 0;
		System.out.printf("%-30s%-10s%-30s\n", "Name:", "Credits:", "Book:");
		for(Course course : courses){
			System.out.printf("%-30s%-10d%-30s\n", course.getAbbrName(), course.getHP(), course.getBookName());
			totalCredits += course.getHP();
		}
		System.out.printf("%-30s%-10d\n", "Total:", totalCredits);
	}
	
	/*public boolean removeKeyFromBackpack(){
		if(CurrentBackpack.removeKey()){
			return true;
		}
		return false;
	}*/
	
	public boolean hasKey(){
		return CurrentBackpack.containsKey();
	}
	
	public boolean removeItemFromBackpack(String itemName){
		if(CurrentBackpack.removeItem(itemName)){
			return true;
		}
		return false;
	}
	
	public boolean dropItem(String itemName){
		Item itemToDrop = CurrentBackpack.getItemByName(itemName);
		if(itemToDrop != null){
			CurrentBackpack.removeItem(itemName);
			CurrentLocation.addItem(itemToDrop);
			return true;
		}
		return false;
	}
	
	public boolean addNewCourseToUnfinished(Course course){
		if(!courseExists(course.getName(), UnfinishedCourses)){
			UnfinishedCourses.add(course);
			return true;
		}
		return false;
	}
	
	public boolean haveBookForCourse(Course course){
		return CurrentBackpack.containsItem((Item)course.getBook());
	}
	
}
