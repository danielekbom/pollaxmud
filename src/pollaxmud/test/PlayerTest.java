package pollaxmud.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Player;
import pollaxmud.enums.Direction;
import pollaxmud.world.Room;

public class PlayerTest {
	// Rooms for testing
	private Room testRoom1 = new Room("Test room 1", true);
	private Room testRoom2 = new Room("Test room 2", true);
	
	//Items for testing
	private Book testItem1 = new Book("Test book 1", "Test author 1", "1999", 3);
	private Book testItem2 = new Book("Test book 2", "Test author 2", "2000", 2);
	private Book testItem3 = new Book("Test book 3", "Test author 3", "2001", 5);
	private Book testItem4 = new Book("Test book 4", "Test author 4", "2002", 4);
	
	private List<Course> testCourses1 = new ArrayList<Course>();
	private Player tester = new Player(testRoom1, testCourses1);
	
	
	@Test
	public void testSetGetLocation() {
		tester.setCurrentLocation(testRoom2);
		assertEquals(testRoom2, tester.getCurrentLocation());
		tester.setCurrentLocation(testRoom1);
		assertEquals(testRoom1, tester.getCurrentLocation());
	}
	
	@Test
	public void testBackpackActions() {
		// Get Empty backpack space
		assertEquals(10, tester.getBackpackCapacity()); // Empty backpack
		// Add items
		assertTrue(tester.addItemToBackpack(testItem1));
		assertEquals(7, tester.getBackpackCapacity()); // 7 empty space left
		assertTrue(tester.addItemToBackpack(testItem2));
		assertEquals(5, tester.getBackpackCapacity()); // 5 empty space left
		// Try to add item that already exists
		//assertFalse(tester.addItemToBackpack(testItem1));
		assertEquals(5, tester.getBackpackCapacity()); // Still 5 empty space left
		// Fill backpack
		assertTrue(tester.addItemToBackpack(testItem3));
		assertEquals(0, tester.getBackpackCapacity()); // Full
		// Try to add stuff to full backpack.
		assertFalse(tester.addItemToBackpack(testItem4));
		// Remove items from backpack
		assertTrue(tester.removeItemFromBackpack("Test book 1"));
		assertEquals(3, tester.getBackpackCapacity()); // 3 empty space
		// Add item that doesn't fit
		assertFalse(tester.addItemToBackpack(testItem4));
		assertEquals(3, tester.getBackpackCapacity()); // Still 3 empty space
		//Remove item that isn't in backpack
		assertFalse(tester.removeItemFromBackpack("Test book 1"));
		// Dropping items
		assertTrue(tester.dropItem("Test book 2"));
		assertEquals(5, tester.getBackpackCapacity());
		// Drop item you don't have.
		assertFalse(tester.dropItem("Test book 2"));
		// See if dropped item is in the room
		assertEquals(tester.getCurrentLocation().getItemByName("Test book 2"), testItem2);
	}
	
	@Test
	public void testWalk() {
		testRoom1.setRoomInDirection(testRoom2, Direction.EAST);
		testRoom2.setRoomInDirection(testRoom1, Direction.WEST);
		assertEquals(testRoom1, tester.getCurrentLocation());
		tester.walk(Direction.EAST);
		assertEquals(testRoom2, tester.getCurrentLocation());
		tester.walk(Direction.EAST);
		assertEquals(testRoom2, tester.getCurrentLocation());
		tester.walk(Direction.WEST);
		assertEquals(testRoom1, tester.getCurrentLocation());
		tester.walk(Direction.WEST);
		assertEquals(testRoom1, tester.getCurrentLocation());
		tester.walk(Direction.NORTH);
		assertEquals(testRoom1, tester.getCurrentLocation());
		tester.walk(Direction.SOUTH);
		assertEquals(testRoom1, tester.getCurrentLocation());
	}
	
	@Test
	public void testCourses() {
		// Courses for testing
		Course testCourse1 = new Course("Test course 1", testItem1, 5);
		Course testCourse2 = new Course("Test course 2", null, 10);
		Course testCourse3 = new Course("Test course 3", testItem3, 15);

		// Test empty lists of courses.
		assertTrue(tester.getFinishedCourses().isEmpty());
		assertTrue(tester.getUnfinishedCourses().isEmpty());
		assertEquals(tester.getFinishedCredits(), 0);
		// Test if player has a book for a course
		assertFalse(tester.haveBookForCourse(testCourse1));
		assertTrue(tester.addItemToBackpack(testItem1));
		assertTrue(tester.haveBookForCourse(testCourse1));
		// Add courses to players unfinished courses
		assertTrue(tester.addNewCourseToUnfinished(testCourse1));
		// Try adding it again.
		assertFalse(tester.addNewCourseToUnfinished(testCourse1));
		// See if the course was added.
		assertTrue(tester.isUnfinishedCourse(testCourse1));
		assertFalse(tester.isPassedCourse(testCourse1));
		assertFalse(tester.isUnfinishedCourse(testCourse2));
		assertFalse(tester.isPassedCourse(testCourse2));
		// Move course to finished
		assertTrue(tester.moveCourse(testCourse1, true));
		assertTrue(tester.isPassedCourse(testCourse1));
		assertFalse(tester.isUnfinishedCourse(testCourse1));
		// Add second course and move it.
		assertTrue(tester.addNewCourseToUnfinished(testCourse2));
		assertTrue(tester.isUnfinishedCourse(testCourse2));
		assertTrue(tester.moveCourse(testCourse2, true));
		assertTrue(tester.isPassedCourse(testCourse2));
		assertFalse(tester.isUnfinishedCourse(testCourse2));
		// Check credits
		assertEquals(tester.getFinishedCredits(), 15);
		// Move courses back
		assertTrue(tester.moveCourse(testCourse1, false));
		assertFalse(tester.isPassedCourse(testCourse1));
		assertTrue(tester.isUnfinishedCourse(testCourse1));
		assertTrue(tester.moveCourse(testCourse2, false));
		assertFalse(tester.isPassedCourse(testCourse2));
		assertTrue(tester.isUnfinishedCourse(testCourse2));
		assertEquals(tester.getFinishedCredits(), 0);
		// Try to move course to same category
		assertFalse(tester.moveCourse(testCourse1, false));
		// Try to move course that doesn't exists.
		assertFalse(tester.moveCourse(testCourse3, true));
		assertFalse(tester.isPassedCourse(testCourse3));
		assertFalse(tester.isUnfinishedCourse(testCourse3));
		// Try move with null
		assertFalse(tester.moveCourse(null, true));
	}
}
