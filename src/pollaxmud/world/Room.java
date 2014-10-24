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
	
}
