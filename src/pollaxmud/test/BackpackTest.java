package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.entities.Backpack;
import pollaxmud.entities.Book;
import pollaxmud.entities.Key;

public class BackpackTest {
	// Test backpack
	private Backpack TestBackpack = new Backpack();
	
	// Items for testing
	private Book testItem1 = new Book("Test book 1", "Test author 1", "1999", 3);
	private Book testItem2 = new Book("Test book 2", "Test author 2", "2000", 2);
	private Key testItem3 = new Key();
	private Key testItem4 = new Key();
	
	@Test
	public void testBackpack() {
		assertEquals(TestBackpack.getSpace(), 10);
		TestBackpack.addItem(testItem1);
		assertEquals(TestBackpack.getSpace(), (7));
		TestBackpack.addItem(testItem2);
		assertEquals(TestBackpack.getSpace(), (5));
		TestBackpack.addItem(testItem1);
		TestBackpack.addItem(testItem2);
		assertEquals(TestBackpack.getSpace(), (0));
		TestBackpack.addItem(testItem1);
		TestBackpack.addItem(testItem2);
		assertEquals(TestBackpack.getSpace(), (0));
	}

}
