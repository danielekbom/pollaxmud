package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.entities.Backpack;
import pollaxmud.entities.Book;
import pollaxmud.entities.Key;

public class BackpackTest {
	// Test backpack
	private Backpack TestBackpack = new Backpack();
	private Backpack TestBackpack2 = new Backpack();
	private Backpack TestBackpack3 = new Backpack();
	private Backpack TestBackpack4 = new Backpack();
	
	// Items for testing
	private Book testItem1 = new Book("Test book 1", "Test author 1", "1999", 3);
	private Book testItem2 = new Book("Test book 2", "Test author 2", "2000", 2);
	private Book testItem3 = new Book("Test book 3", "Test author 3", "1999", 5);
	private Book testItem4 = new Book("Test book 4", "Test author 4", "1999", 3);
	private Key testItem5 = new Key();
	private Key testItem6 = new Key();
	
	@Test
	public void testAddRemoveSpace() {
		// Add items
		assertEquals(TestBackpack.getSpace(), 10);
		assertTrue(TestBackpack.addItem(testItem1));
		assertEquals(TestBackpack.getSpace(), 7);
		// Add same item twice
		assertFalse(TestBackpack.addItem(testItem1));
		assertEquals(TestBackpack.getSpace(), 7);
		// Add more items
		assertTrue(TestBackpack.addItem(testItem2));
		assertEquals(TestBackpack.getSpace(), 5);
		assertTrue(TestBackpack.addItem(testItem3));
		assertEquals(TestBackpack.getSpace(), 0);
		// Try to add more items than what fits.
		assertFalse(TestBackpack.addItem(testItem4));
		assertEquals(TestBackpack.getSpace(), 0);
		// Remove items
		assertTrue(TestBackpack.removeItem("Test book 1"));
		assertEquals(TestBackpack.getSpace(), 3);
		assertTrue(TestBackpack.removeItem("Test book 2"));
		assertEquals(TestBackpack.getSpace(), 5);
		assertTrue(TestBackpack.removeItem("Test book 3"));
		assertEquals(TestBackpack.getSpace(), 10);
		// Remove something that's not there.
		assertFalse(TestBackpack.removeItem("Test book 3"));
		assertEquals(TestBackpack.getSpace(), 10);
	}
	
	@Test
	public void testContainsKey() {
		// Empty backpack
		assertFalse(TestBackpack2.containsKey());
		// Add keys
		assertTrue(TestBackpack2.addItem(testItem5)); // Add a key
		assertTrue(TestBackpack2.containsKey());
		assertTrue(TestBackpack2.addItem(testItem6)); // Add a key
		assertTrue(TestBackpack2.containsKey());
		// Remove one key
		assertTrue(TestBackpack2.removeItem("Keycard"));
		assertTrue(TestBackpack2.containsKey());
		// Remove last key
		assertTrue(TestBackpack2.removeItem("Keycard"));
		assertFalse(TestBackpack2.containsKey());
	}
	
	@Test
	public void testContainsItem() {
		// Empty backpack
		assertFalse(TestBackpack3.containsItem(testItem1));
		// Add item and look for it.
		assertTrue(TestBackpack3.addItem(testItem1));
		assertTrue(TestBackpack3.containsItem(testItem1));
		// Remove item and look for it.
		assertTrue(TestBackpack3.removeItem("Test book 1"));
		assertFalse(TestBackpack3.containsItem(testItem1));
	}
	
	@Test
	public void testGetItemByName() {
		// Try to find from empty backpack
		assertEquals(TestBackpack4.getItemByName("Test book 2"), null); 
		// Add item
		assertTrue(TestBackpack4.addItem(testItem2));
		// Get it by name
		assertEquals(TestBackpack4.getItemByName("Test book 2"), testItem2);
		// Add two items with the same name.
		assertTrue(TestBackpack4.addItem(testItem5));
		assertTrue(TestBackpack4.addItem(testItem6));
		// Get the one first added to the backpack.
		assertEquals(TestBackpack4.getItemByName("Keycard"), testItem5);
		
	}
	

}
