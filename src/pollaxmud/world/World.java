package pollaxmud.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pollaxmud.entities.Item;
import pollaxmud.entities.Key;
import pollaxmud.entities.Creature;
import pollaxmud.exceptions.CustomException;

/**
 * Class representing a World.
 * A World consists of a list of Rooms.
 * @author Daniel and Oscar
 *
 */
public class World {

	private List<Room> Rooms = new ArrayList<Room>();
	
	/**
	 * Constructor of a World.
	 */
	public World(){
		
	}
	
	/**
	 * Adds a Room to the world.
	 * @param roomToAdd Room to be added to the world.
	 */
	public void addRoom(Room roomToAdd){
		try{
			if(roomToAdd != null){
				Rooms.add(roomToAdd);
			}else{
				throw new CustomException("Cant add null to a World's Rooms list!","NullRoomException");
			}
		}catch(CustomException e){
			e.printMessage();
		}
	}
	
	/**
	 * Puts given items in randomly chosen Rooms in the world.
	 * @param items List of items to add.
	 */
	public void putItemsRandomly(List<? extends Item> items){
		int numberOfRooms = Rooms.size();
		Room locationToAddTo;
		Random rand = new Random();
		for(Item item : items){
			locationToAddTo = this.getRoomAtIndex(rand.nextInt(numberOfRooms));
			locationToAddTo.addItem(item);
		}
	}
	
	/**
	 * Puts given creatures in randomly chosen Rooms in the world.
	 * @param creatures List of creatures to add.
	 * @param onlyLockedRooms Whether the creatures should be added to locked rooms only. True means only locked rooms, false means all rooms.
	 */
	public void putCreaturesRandomly(List<? extends Creature> creatures, boolean onlyLockedRooms){
		List<Room> roomsList;
		if(onlyLockedRooms){
			roomsList = getLockedRooms();
		}else{
			roomsList = Rooms;
		}
		if(roomsList.size() == 0){
			System.out.println("No locked rooms to add creatures to!");
			return;
		}
		int numberOfRooms = roomsList.size();
		Room locationToAddTo;
		Random rand = new Random();
		for(Creature creature : creatures){
			locationToAddTo = roomsList.get(rand.nextInt(numberOfRooms));
			locationToAddTo.addCreature(creature);
		}
	}
	
	/**
	 * Get all Rooms in the world which is locked.
	 * @return A list of the locked Rooms in the world.
	 */
	public List<Room> getLockedRooms(){
		List<Room> lockedRooms = new ArrayList<Room>();
		for(Room room : Rooms){
			if(!room.getUnlocked()){
				lockedRooms.add(room);
			}
		}
		return lockedRooms;
	}
	
	/**
	 * Get the number of locked Rooms in the world.
	 * @return The number of locked Rooms in the world.
	 */
	public int getNumberOfLockedRooms(){
		int counter = 0;
		for(Room room : Rooms){
			if(room.getUnlocked() == false){
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Get the Room from the world's rooms list at a specific index.
	 * @param index The index of the Room to get.
	 * @return The room with the given index in the worlds rooms list.
	 */
	public Room getRoomAtIndex(int index){
		try{
			return Rooms.get(index);
		}catch(IndexOutOfBoundsException e){
			System.err.println("IndexOutOfBoundsException:\n\tTried to get a room at an invalid index!");
			return null;
		}
	}
	
	/**
	 * Get a room from the world with the same name as a given string.
	 * @param name A string which to look for a room with the same name as.
	 * @return The room in the world's rooms list with the same name as the given string if such room exists, else null.
	 */
	public Room getRoomByName(String name){
		for(Room room : Rooms){
			if(room.getName().equals(name)){
				return room;
			}
		}
		return null;
	}
	
	/**
	 * Adds keys to random positions of the world.
	 * The amount of keys to add is equal to the amount of locked rooms times 1,5.
	 */
	public void addKeysToWorld(){
		int numberOfKeysToAdd = (int)(getNumberOfLockedRooms()*1.5);
		List<Key> keys = new ArrayList<Key>();
		for(int i = 0; i < numberOfKeysToAdd; i++){
			keys.add(new Key());
		}
		putItemsRandomly(keys);
	}
	
	/**
	 * Getter for the World's list of rooms.
	 * @return A list of the Rooms in the world.
	 */
	public List<Room> getRooms(){
		return Rooms;
	}
	
}
