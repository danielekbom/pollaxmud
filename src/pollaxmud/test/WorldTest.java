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

	World TestWorld = new World();
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
		assertTrue("getRoom", TestWorld.getRooms().size() == 0);
		TestWorld.addRoom(new Room("TestRoom", true));
		TestWorld.addRoom(null);
		assertTrue("addRoom", TestWorld.getRooms().size() == 1);
		TestWorld.putItemsRandomly(ItemList);
		assertTrue("putItemsRandomly & getRoomByName", TestWorld.getRoomByName("TestRoom").getItemByName("Keycard") != null);
		TestWorld.putCreaturesRandomly(CreatureList, false);
		TestWorld.addRoom(new Room("TestRoomLocked", false));
		TestWorld.putCreaturesRandomly(CreatureList2, true);
		assertTrue("putCreaturesRandomly (onlyLockedRooms = false)", TestWorld.getRoomByName("TestRoom").getCreatureByName("TestTeacher") != null);
		assertTrue("putCreaturesRandomly (onlyLockedRooms = true)", TestWorld.getRoomByName("TestRoomLocked").getCreatureByName("TestTeacher2") != null);
		assertTrue("getLockedRooms", TestWorld.getLockedRooms().size() == 1);
		assertTrue("getLockedRooms", TestWorld.getLockedRooms().get(0).getUnlocked() == false);
		assertTrue("getNumberOfLockedRooms", TestWorld.getNumberOfLockedRooms() == 1);
		assertTrue("getRoomAtIndex", TestWorld.getRoomAtIndex(1).getName().equals("TestRoomLocked"));
		assertTrue("getRoomAtIndex", TestWorld.getRoomAtIndex(2) == null);
		assertTrue("getRoomAtIndex", TestWorld.getRoomAtIndex(-1) == null);
		assertTrue("getRoomByName", TestWorld.getRoomByName("noRoom") == null);
		TestWorld_2.addRoom(new Room("TestWorld_2_Room_1", false));
		TestWorld_2.addKeysToWorld();
		assertTrue("addKeysToWorld", TestWorld_2.getRoomAtIndex(0).getItemByName("Keycard") != null);
	}

}
