package pollaxmud.world;

import java.util.ArrayList;
import java.util.List;

import pollaxmud.Enums.Direction;
import pollaxmud.entities.Creature;
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
	
	public Room(String name, boolean unlocked){
		this(name, unlocked, 0, 0);
	}
	
	public Room(String name, boolean unlocked, int x, int y){
		this.Name = name;
		this.Unlocked = unlocked;
		XPosition = x;
		YPosition = y;
	}
	
	public void addItem(Item itemToAdd){
		Items.add(itemToAdd);
	}
	
	public void addCreature(Creature creatureToAdd) {
		Creatures.add(creatureToAdd);
	}
	
	public String getName(){
		return Name;
	}
	
	public Item getItemByName(String name){
		for(Item item : Items){
			if(item.getName().equalsIgnoreCase(name)){
				return item;
			}
		}
		return null;
	}
	
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
	
	public boolean getUnlocked(){
		return Unlocked;
	}
	
	public int getXPosition(){
		return XPosition;
	}
	
	public int getYPosition(){
		return YPosition;
	}
	
}
