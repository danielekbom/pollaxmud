package pollaxmud.world;

import pollaxmud.entities.Creature;
import pollaxmud.entities.Item;

public class Room {

	private Creature[] Creatures;
	private Item[] Items;
	
	private Room RoomNorth;
	private Room RoomSouth;
	private Room RoomWest;
	private Room RoomEast;
	
	public Room(){
		System.out.println("Testar att skapa ett rum");
	}
	
}
