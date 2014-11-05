package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.entities.Key;
import pollaxmud.enums.ItemType;

public class KeyTest {
	Key testKey = new Key();

	@Test
	public void testGetName() {
		assertEquals(testKey.getName(), "Keycard");
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(testKey.getWeight(), 1);
	}
	
	@Test
	public void testGetType() {
		assertEquals(testKey.getType(), ItemType.KEY);
	}

}
