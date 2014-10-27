package pollaxmud.world;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pollaxmud.entities.Item;

public class World {

	private List<Room> Rooms = new ArrayList<Room>();
	
	public World(){
		
	}
	
	public void addRoom(Room roomToAdd){
		if(roomToAdd != null){
			Rooms.add(roomToAdd);
		}
	}
	
	public void putItemsRandomly(List<? extends Item> items){
		int numberOfRooms = Rooms.size();
		Room locationToAddTo;
		Random rand = new Random();
		for(Item item : items){
			locationToAddTo = this.getRoomAtIndex(rand.nextInt(numberOfRooms));
			locationToAddTo.addItem(item);
		}
	}
	
	public Room getRoomAtIndex(int index){
		return Rooms.get(index);
	}
	
	public Room getRoomByName(String name){
		for(Room room : Rooms){
			if(room.getName().equals(name)){
				return room;
			}
		}
		return null;
	}
	
}
