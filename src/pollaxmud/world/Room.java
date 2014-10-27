package pollaxmud.world;

import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Book;
import pollaxmud.entities.Creature;
import pollaxmud.entities.Item;
import pollaxmud.entities.Item.ItemType;

public class Room {

	private String Name;
	private boolean Unlocked;
	
	private Creature[] Creatures;
	private List<Item> Items = new ArrayList<Item>();
	
	private Room RoomNorth;
	private Room RoomEast;
	private Room RoomSouth;
	private Room RoomWest;
	
	public Room(String name, boolean unlocked){
		this.Name = name;
		this.Unlocked = unlocked;
	}
	
	public void addItem(Item itemToAdd){
		Items.add(itemToAdd);
	}
	
	public String getName(){
		return Name;
	}
	
	public void printEntranceText(){
		System.out.println("Current location: " + Name);
		if(!Items.isEmpty()){
			System.out.println("Items on this location: ");
			for(Item item : Items){
				if(item.getType() == ItemType.BOOK){
					System.out.println("Book: " + ((Book) item).getName());
				}else if(item.getType() == ItemType.KEY){
					System.out.println("A key");
				}
			}
		}
		System.out.println("---------------------------------------");
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
	
	public boolean getUnlocked(){
		return Unlocked;
	}
	
}
