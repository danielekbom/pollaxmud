package pollaxmud.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pollaxmud.entities.Key;
import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Player;
import pollaxmud.world.Room;
import pollaxmud.Enums.Direction;

public class PlayerTest {
	// Rooms for testing
	Room testRoom1 = new Room("Test room 1", true);
	Room testRoom2 = new Room("Test room 2", true);
	
	//Items for testing
	Book testItem1 = new Book("Test book 1", "Test author 1", "1999", 3);
	Book testItem2 = new Book("Test book 2", "Test author 2", "2000", 2);
	Key testItem3 = new Key();
	
	List<Course> testCourses1 = new ArrayList<Course>();
	Player tester = new Player(testRoom1, testCourses1);
	
	
	@Test
	public void testSetGetLocation() {
		tester.setCurrentLocation(testRoom2);
		assertEquals(testRoom2, tester.getCurrentLocation());
		tester.setCurrentLocation(testRoom1);
		assertEquals(testRoom1, tester.getCurrentLocation());
	}
	
	@Test
	public void testBackpackActions() {
		assertEquals(10, tester.getBackpackCapacity()); // Empty backpack
		tester.addItemToBackpack(testItem1);
		assertEquals(7, tester.getBackpackCapacity()); // 7 empty space left
		tester.addItemToBackpack(testItem2);
		assertEquals(5, tester.getBackpackCapacity()); // 5 empty space left
		tester.addItemToBackpack(testItem1);
		assertEquals(2, tester.getBackpackCapacity()); // 2 empty space
		tester.addItemToBackpack(testItem1); 		   // Not enought room in backpack
		assertEquals(2, tester.getBackpackCapacity()); // 2 empty space
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

}
