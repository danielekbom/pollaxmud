package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.enums.Direction;
import pollaxmud.entities.Book;
import pollaxmud.entities.Key;
import pollaxmud.world.Room;

public class RoomTest {
	
	// Rooms for testing
	private Room testRoom1 = new Room("Test room 1", true);
	private Room testRoom2 = new Room("Test room 2", true, 100, 100);
	private Room testRoom3 = new Room("Test room 1", false);
	// Items for testing
	private Book testItem1 = new Book("Test book 1", "Test author 1", "1999", 3);
	private Book testItem2 = new Book("Test book 2", "Test author 2", "2000", 2);
	private Key testItem3 = new Key();
	private Key testItem4 = new Key();
	
	@Test
	public void testName() {
		assertEquals("Test room 1", testRoom1.getName());
		assertEquals("Test room 2", testRoom2.getName());
	}
	
	@Test
	public void testItems() {
		assertEquals(null, testRoom1.getItemByName("Test book 1"));
		testRoom1.addItem(testItem1); // Add a book.
		assertEquals(testItem1, testRoom1.getItemByName("Test book 1"));
		testRoom1.addItem(testItem3); // Add a keycard
		assertEquals(testItem3, testRoom1.getItemByName("Keycard"));
		testRoom1.addItem(testItem4); // Two keycards in room.
		testRoom1.deleteItemByName("Keycard"); // Delete one keycard.
		assertEquals(testItem3, testRoom1.getItemByName("Keycard")); // Should still be 1 keycard left
		testRoom1.deleteItemByName("Keycard"); // Delete the other keycard.
		assertEquals(null, testRoom1.getItemByName("Keycard"));
		testRoom1.deleteItemByName("Test book 1");
		assertEquals(null, testRoom1.getItemByName("Test book 1"));
	}
	
	@Test
	public void testAdjecentRooms() {
		assertEquals(testRoom1.getRoomInDirection(Direction.NORTH), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.WEST), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.SOUTH), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.EAST), null);
		testRoom1.setRoomInDirection(testRoom2, Direction.NORTH);
		testRoom1.setRoomInDirection(testRoom2, Direction.WEST);
		assertEquals(testRoom1.getRoomInDirection(Direction.NORTH), testRoom2);
		assertEquals(testRoom1.getRoomInDirection(Direction.WEST), testRoom2);
		assertEquals(testRoom1.getRoomInDirection(Direction.SOUTH), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.EAST), null);
		testRoom1.setRoomInDirection(null, Direction.NORTH);
		testRoom1.setRoomInDirection(null, Direction.WEST);
		testRoom1.setRoomInDirection(testRoom2, Direction.SOUTH);
		testRoom1.setRoomInDirection(testRoom2, Direction.EAST);
		assertEquals(testRoom1.getRoomInDirection(Direction.NORTH), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.WEST), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.SOUTH), testRoom2);
		assertEquals(testRoom1.getRoomInDirection(Direction.EAST), testRoom2);
		testRoom1.setRoomInDirection(null, Direction.SOUTH);
		testRoom1.setRoomInDirection(null, Direction.EAST);
		assertEquals(testRoom1.getRoomInDirection(Direction.NORTH), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.WEST), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.SOUTH), null);
		assertEquals(testRoom1.getRoomInDirection(Direction.EAST), null);
		testRoom1.setRoomInDirection(testRoom1, Direction.EAST);
		assertEquals(testRoom1.getRoomInDirection(Direction.EAST), testRoom1);
	}
	
	@Test
	public void testLocked() {
		assertEquals(testRoom1.getUnlocked(), true);
		assertEquals(testRoom2.getUnlocked(), true);
		assertEquals(testRoom3.getUnlocked(), false);
	}
	
	@Test
	public void testPositions() {
		assertEquals(testRoom1.getXPosition(), 0);
		assertEquals(testRoom1.getYPosition(), 0);
		assertEquals(testRoom2.getXPosition(), 100);
		assertEquals(testRoom2.getYPosition(), 100);
		assertEquals(testRoom3.getXPosition(), 0);
		assertEquals(testRoom3.getYPosition(), 0);
	}

}
