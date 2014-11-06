package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pollaxmud.enums.Direction;
import pollaxmud.world.Room;

/**
 * Class for representing the Player that the person that is playing the game is controlling.
 * A Player has a list of Unfinished courses, a list of Finished courses, a Backpack,
 * a CurrentLocation which is the Room the Player is currently in,
 * and an integer representing how many finished high school points the Player should start with.
 * @author Daniel and Oscar
 *
 */
public class Player {

	private final int STARTING_HP = 60;
	private List<Course> UnfinishedCourses;
	private List<Course> FinishedCourses;
	private Backpack CurrentBackpack;
	private Room CurrentLocation;
	
	/**
	 * The constructor for a player.
	 * @param location The starting location(Room) of the player.
	 * @param courses The courses the player can take.
	 */
	public Player(Room location, List<Course> courses){
		FinishedCourses = initializeFinishedCourses(courses);
		UnfinishedCourses = new ArrayList<Course>();//initializeUnfinishedCourses(FinishedCourses, courses);
		CurrentLocation = location;
		CurrentBackpack = new Backpack();
	}

	/**
	 * Initializes the Players finished courses.
	 * Builds an list of random courses taken from the parameter "courses" until sum of the courses HP
	 * in the new list is greater than or equal to the players starting high school points.
	 * @param courses A list of courses to build the new list of.
	 * @return A list of courses randomly picked from the "courses" parameter.
	 */
	private List<Course> initializeFinishedCourses(List<Course> courses) {
		List<Course> finishedCourses = new ArrayList<Course>();
		int coursesSize = courses.size();
		// TEST
		if(coursesSize < 1) {
			return finishedCourses;
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
	
	/**
	 * Get the sum of the high school points the player have from the finished courses.
	 * @return The sum of the high school points in the Players FinishedCourses.
	 */
	public int getFinishedCredits() {
		return getCoursesPoints(this.FinishedCourses);
	}
	
	/**
	 * Calculates the sum of high school points of the courses in the courses list parameter.
	 * @param courses The list of courses used in the calculation.
	 * @return The sum of the credits of the courses in the "courses" parameter.
	 */
	private int getCoursesPoints(List<Course> courses){
		if (courses.isEmpty()) {
			return 0;
		}
		int points = 0;
		for(Course course : courses){
			points += course.getHP();
		}
		return points;
	}
	
	/**
	 * Checks whether the Player have finished a course or not.
	 * @param chk_course Course to check if it is finished or not.
	 * @return True if "chk_course" is finished, else false.
	 */
	public boolean isPassedCourse(Course chk_course) {
		for(Course course : FinishedCourses) {
			if (course == chk_course) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks whether the Course parameter is in the Player's list of unfinished courses or not.
	 * @param chk_course The course to check.
	 * @return True if "chk_course" is in the Player's list of unfinished courses, else false.
	 */
	public boolean isUnfinishedCourse(Course chk_course) {
		for(Course course : UnfinishedCourses) {
			if (course == chk_course) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Moves a course to the Player's list of finished or unfinished courses.
	 * If the parameter "finished" is true, then the course in the parameters is moved from the list of
	 * unfinished courses to the list of finished courses, else the other way around.
	 * @param course The course to be moved.
	 * @param finished Whether the course should be moved to finished or unfinished courses.
	 * @return True if the course was successfully moved, else false.
	 */
	public boolean moveCourse(Course course, boolean finished){
		if(course == null) { return false; }
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
	
	/**
	 * Checks if a course with a given name exists in a given list of courses.
	 * @param courseName The name of the course to look for.
	 * @param courses A list of courses to look in.
	 * @return True if the course was found in the list, else false.
	 */
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
	 * @return True if the move succeeded, else false.
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
	 * @param  newLocation The new location(Room) of the Player.
	 */
	public void setCurrentLocation(Room newLocation){
		CurrentLocation = newLocation;
	}

	/**
	 * Get the Player's current position
	 * @return The Room that the Player is currently in.
	 */
	public Room getCurrentLocation(){
		return CurrentLocation;
	}
	
	/**
	 * Get the available space in the backpack of the player.
	 * @return Available space in backpack.
	 */
	public int getBackpackCapacity(){
		return CurrentBackpack.getSpace();
	}
	
	/**
	 * Will put an item into the backpack of the player if there is room.
	 * @param item True if the Item was successfully added to the backpack, else false.
	 * @return True if the add was successful, else false.
	 */
	public boolean addItemToBackpack(Item item){
		if(CurrentBackpack.addItem(item)){
			return true;
		}
		return false;
	}
	
	/**
	 * Prints the items inside the Player's backpack.
	 */
	public void printBackpackInventory(){
		CurrentBackpack.printInventory();
	}

	/**
	 * Prints the courses the player have finished or not finished.
	 * I.e. the courses in the players list of finished courses, or in the list of unfinished courses.
	 * If the parameter "finished" is true, then finished courses are printed, else unfinished courses.
	 * @param finished Whether the method should print finished or unfinished courses.
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
	
	/**
	 * Checks whether the player has at least one key in his backpack.
	 * @return True if the backpack contains at least one key, else false.
	 */
	public boolean hasKey(){
		return CurrentBackpack.containsKey();
	}
	
	/**
	 * Removes an item from the Player's backpack.
	 * @param itemName The name of the item to remove.
	 * @return True if the item was successfully removed, else false.
	 */
	public boolean removeItemFromBackpack(String itemName){
		if(CurrentBackpack.removeItem(itemName)){
			return true;
		}
		return false;
	}
	
	/**
	 * Removes an item from the Player's backpack
	 * and adds it to the current locations list of items.
	 * @param itemName Item to remove from backpack and add to the current location.
	 * @return True if the operation was successful, else false.
	 */
	public boolean dropItem(String itemName){
		Item itemToDrop = CurrentBackpack.getItemByName(itemName);
		if(itemToDrop != null){
			CurrentBackpack.removeItem(itemName);
			CurrentLocation.addItem(itemToDrop);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a course to the player's list of unfinished courses
	 * if it does not already exists in it.
	 * @param course The course to add.
	 * @return True if the add was successful, elase false.
	 */
	public boolean addNewCourseToUnfinished(Course course){
		if(!courseExists(course.getName(), UnfinishedCourses)){
			UnfinishedCourses.add(course);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether the player have the corresponding book for a specific course in his backpack.
	 * @param course Course to check if the player has the book for.
	 * @return True if the player have the book, else false.
	 */
	public boolean haveBookForCourse(Course course){
		return CurrentBackpack.containsItem((Item)course.getBook());
	}
	
	/**
	 * Getter for the player's unfinished courses.
	 * @return A list of the player's unfinished courses.
	 */
	public List<Course> getUnfinishedCourses(){
		return UnfinishedCourses;
	}
	
	/**
	 * Getter for the player's finished courses.
	 * @return A list of the player's finished courses.
	 */
	public List<Course> getFinishedCourses(){
		return FinishedCourses;
	}
	
}
