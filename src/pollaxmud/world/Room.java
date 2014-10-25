package pollaxmud.world;

import pollaxmud.entities.Creature;
import pollaxmud.entities.Item;

public class Room {

	private String Name;
	private boolean Unlocked;
	
	private Creature[] Creatures;
	private Item[] Items;
	
	private Room RoomNorth;
	private Room RoomEast;
	private Room RoomSouth;
	private Room RoomWest;
	
	public Room(String name, boolean unlocked){
		this.Name = name;
		this.Unlocked = unlocked;
	}
	
	public String getName(){
		return Name;
	}
	
	public void printEntranceText(){
		System.out.println("Current location: " + Name);
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
	}
	
	public void setRoomNorth(Room room){
		RoomNorth = room;
	}
	
	public void setRoomEast(Room room){
		RoomEast = room;
	}
	
	public void setRoomSouth(Room room){
		RoomSouth = room;
	}
	
	public void setRoomWest(Room room){
		RoomWest = room;
	}
	
	public Room getRoomNorth(){
		return RoomNorth;
	}
	
	public Room getRoomEast(){
		return RoomEast;
	}
	
	public Room getRoomSouth(){
		return RoomSouth;
	}
	
	public Room getRoomWest(){
		return RoomWest;
	}
	
}
