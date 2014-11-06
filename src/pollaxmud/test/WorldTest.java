package pollaxmud.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Creature;
import pollaxmud.entities.Item;
import pollaxmud.entities.Key;
import pollaxmud.entities.Teacher;
import pollaxmud.world.Room;
import pollaxmud.world.World;

public class WorldTest {

	World TestWorld_1 = new World();
	World TestWorld_2 = new World();
	List<Item> ItemList = new ArrayList<Item>();
	List<Creature> CreatureList = new ArrayList<Creature>();
	List<Creature> CreatureList2 = new ArrayList<Creature>();
	Course TestCourse = new Course("TestCourse", new Book("TestBook","test","2000",2), 10);
	
	@Before
	public void prepareTestData(){
		ItemList.add(new Key());
		CreatureList.add(new Teacher("TestTeacher",TestCourse));
		CreatureList2.add(new Teacher("TestTeacher2",TestCourse));
	}
	
	@Test
	public void test() {
		assertTrue("getRoom", TestWorld_1.getRooms().size() == 0);
		TestWorld_1.addRoom(new Room("TestWorld_1_Room_1", true));
		TestWorld_1.addRoom(null);
		assertTrue("addRoom", TestWorld_1.getRooms().size() == 1);
		TestWorld_1.putItemsRandomly(ItemList);
		assertTrue("putItemsRandomly & getRoomByName", TestWorld_1.getRoomByName("TestWorld_1_Room_1").getItemByName("Keycard") != null);
		TestWorld_1.putCreaturesRandomly(CreatureList, false);
		TestWorld_1.addRoom(new Room("TestWorld_1_Room_2", false));
		TestWorld_1.putCreaturesRandomly(CreatureList2, true);
		assertTrue("putCreaturesRandomly (onlyLockedRooms = false)", TestWorld_1.getRoomByName("TestWorld_1_Room_1").getCreatureByName("TestTeacher") != null);
		assertTrue("putCreaturesRandomly (onlyLockedRooms = true)", TestWorld_1.getRoomByName("TestWorld_1_Room_2").getCreatureByName("TestTeacher2") != null);
		assertTrue("getLockedRooms", TestWorld_1.getLockedRooms().size() == 1);
		assertTrue("getLockedRooms", TestWorld_1.getLockedRooms().get(0).getUnlocked() == false);
		assertTrue("getNumberOfLockedRooms", TestWorld_1.getNumberOfLockedRooms() == 1);
		assertTrue("getRoomAtIndex", TestWorld_1.getRoomAtIndex(1).getName().equals("TestWorld_1_Room_2"));
		assertTrue("getRoomAtIndex", TestWorld_1.getRoomAtIndex(2) == null);
		assertTrue("getRoomAtIndex", TestWorld_1.getRoomAtIndex(-1) == null);
		assertTrue("getRoomByName", TestWorld_1.getRoomByName("noRoom") == null);
		TestWorld_2.addRoom(new Room("TestWorld_2_Room_1", false));
		TestWorld_2.addKeysToWorld();
		assertTrue("addKeysToWorld", TestWorld_2.getRoomAtIndex(0).getItemByName("Keycard") != null);
	}

}
