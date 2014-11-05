package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.entities.Book;

public class BookTest {
	private Book testBook1 = new Book("Test book 1", "Test author 1", "2014", 3);
	private Book testBook2 = new Book("Test book 1", "Test author 1", null, 3);
	private Book testBook3 = new Book(null, "Test author 1", "2014", 3);

	@Test
	public void testGetYear() {
		assertEquals(testBook1.getYear(), "2014");
		assertEquals(testBook2.getYear(), null);
	}
	
	@Test
	public void testGetName() {
		assertEquals(testBook1.getName(), "Test book 1");
		assertEquals(testBook3.getName(), null);
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(testBook1.getWeight(), 3);

	}

}
