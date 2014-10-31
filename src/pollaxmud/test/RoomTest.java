package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.entities.Book;
import pollaxmud.entities.Key;
import pollaxmud.world.Room;

public class RoomTest {
	
	// Rooms for testing
	private Room testRoom1 = new Room("Test room 1", true);
	private Room testRoom2 = new Room("Test room 2", true, 100, 100);
	//Items for testing
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
	/*
	@Test
	public void testAdjecentRooms() {
		fail("Not implemented yet");
	}
	*/

}
