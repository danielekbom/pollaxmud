package pollaxmud.world;

import pollaxmud.entities.Creature;
import pollaxmud.entities.Item;

public class Room {

	private String Name;
	
	private Creature[] Creatures;
	private Item[] Items;
	
	private Room RoomNorth;
	private Room RoomEast;
	private Room RoomSouth;
	private Room RoomWest;
	
	public Room(String name){
		this.Name = name;
	}
	
	public String getName(){
		return Name;
	}
	
}
