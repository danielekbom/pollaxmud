package pollaxmud.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pollaxmud.Enums.Direction;
import pollaxmud.entities.Creature;
import pollaxmud.entities.Teacher;
import pollaxmud.Enums.CreatureType;
import pollaxmud.entities.Item;

public class Room {

	private String Name;
	private boolean Unlocked;
	
	private int XPosition;
	private int YPosition;
	
	private List<Creature> Creatures = new ArrayList<Creature>();
	private List<Item> Items = new ArrayList<Item>();
	
	private Room RoomNorth;
	private Room RoomEast;
	private Room RoomSouth;
	private Room RoomWest;
	
	/**
	 * First constructor for a Room. 
	 * Set the center coordinates to (0,0).
	 * @param name The name of the room.
	 * @param unlocked True if unlocked door false if locked.
	 */
	public Room(String name, boolean unlocked){
		this(name, unlocked, 0, 0);
	}
	
	/**
	 * Second constructor for a Room.
	 * @param name The name of the room
	 * @param unlocked True for unlocked false for locked door.
	 * @param x Center X-coordinate.
	 * @param y Center Y-coordinate
	 */
	public Room(String name, boolean unlocked, int x, int y){
		this.Name = name;
		this.Unlocked = unlocked;
		XPosition = x;
		YPosition = y;
	}
	
	/**
	 * Puts an item in room.
	 * @param itemToAdd The item to add.
	 */
	public void addItem(Item itemToAdd){
		Items.add(itemToAdd);
	}
	
	/**
	 * Puts a creature in a room.
	 * @param creatureToAdd The creature to add.
	 */
	public void addCreature(Creature creatureToAdd) {
		Creatures.add(creatureToAdd);
	}
	
	/**
	 * Gets the name of a room.
	 * @return The rooms name as a String.
	 */
	public String getName(){
		return Name;
	}
	
	/**
	 * Gets an item from a room by its name.
	 * @param name The name of the item.
	 * @return The item.
	 */
	public Item getItemByName(String name){
		for(Item item : Items){
			if(item.getName().equalsIgnoreCase(name)){
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Deletes an item from a room by it's name.
	 * @param name The name of the item to delete.
	 */
	public void deleteItemByName(String name){
		boolean found = false;
		int itemIndex = 0;
		for(Item item : Items){
			if(item.getName().equalsIgnoreCase(name)){
				itemIndex = Items.indexOf(item);
				found = true;
			}
		}
		if(found){
			Items.remove(itemIndex);
		}
	}
	
	/**
	 * Prints info about the room.
	 * The name of the room, what items are in it, 
	 * what creatures are in it and where it's possible to go.
	 */
	public void printEntranceText(){
		System.out.println("***************************************");
		System.out.println("Current location: " + Name);
		if(!Items.isEmpty()){
			System.out.println("---------------------------------------");
			System.out.println("Items on this location:");
			for(Item item : Items){
				item.printName();
				System.out.println();
			}
		}
		if(!Creatures.isEmpty()) {
			System.out.println("---------------------------------------");
			System.out.println("People in this location:");
			for(Creature creature : Creatures) {
				if(creature.getType() == CreatureType.TEACHER) {
					System.out.println("Teacher: " + creature.getName());
				}
			}
		}
		System.out.println("---------------------------------------");
		System.out.println("Possible movements:");
		if(RoomNorth != null){
			System.out.println("To the north: " + RoomNorth.getName());
		}
		if(RoomEast != null){
			System.out.println("To the east: " + RoomEast.getName());
		}
		if(RoomSouth != null){
			System.out.println("To the south: " + RoomSouth.getName());
		}
		if(RoomWest != null){
			System.out.println("To the west: " + RoomWest.getName());
		}
		System.out.println("***************************************");
	}
	
	/**
	 * Set what room is in different directions of the current room.
	 * @param room The room to add in a direction.
	 * @param direction The direction to add the room to.
	 */
	public void setRoomInDirection(Room room, Direction direction){
		switch(direction){
		case NORTH:
			RoomNorth = room;
			break;
		case EAST:
			RoomEast = room;
			break;
		case SOUTH:
			RoomSouth = room;
			break;
		case WEST:
			RoomWest = room;
			break;
		default:
			System.out.println("Invalid direction!");
		}
	}
	
	/**
	 * Gets the room in a direction of the current room.
	 * @param direction The direction to get the room from.
	 * @return The room. Null if no room there.
	 */
	public Room getRoomInDirection(Direction direction){
		switch(direction){
		case NORTH:
			return RoomNorth;
		case EAST:
			return RoomEast;
		case SOUTH:
			return RoomSouth;
		case WEST:
			return RoomWest;
		default:
			return null;
		}
	}
	
	/**
	 * Get the locked/unlocked status of a room.
	 * @return True for unlocked and false for locked.
	 */
	public boolean getUnlocked(){
		return Unlocked;
	}
	
	/**
	 * Gets the x-coordinate for a room.
	 * @return The x-coordinate as a Int.
	 */
	public int getXPosition(){
		return XPosition;
	}
	
	/**
	 * Gets the y-coordinate for a room.
	 * @return The y-coordinate as a Int.
	 */
	public int getYPosition(){
		return YPosition;
	}
	
	public void unlock(){
		Unlocked = true;
	}
	
	public boolean containsTeacher(){
		boolean contains = false;
		for(Creature creature : Creatures){
			if(creature.getType() == CreatureType.TEACHER){
				contains = true;
			}
		}
		return contains;
	}
	
	public Teacher getRandomTeacher(){
		List<Teacher> teachers = new ArrayList<Teacher>();
		for(Creature creature : Creatures){
			if(creature.getType() == CreatureType.TEACHER){
				teachers.add((Teacher)creature);
			}
		}
		int teachersSize = teachers.size();
		if(teachersSize == 0){
			return null;
		}
		Random random = new Random();
		Teacher randomTeacher = teachers.get(random.nextInt(teachersSize));
		return randomTeacher;
	}
	
	public Creature getCreatureByName(String name){
		for(Creature creature : Creatures){
			if(creature.getName().equalsIgnoreCase(name)){
				return creature;
			}
		}
		return null;
	}
	
}
