package pollaxmud.world;

import java.util.ArrayList;
import java.util.List;

public class World {

	private List<Room> Rooms = new ArrayList<Room>();
	
	public World(){
		
	}
	
	public void addRoom(Room roomToAdd){
		if(roomToAdd != null){
			Rooms.add(roomToAdd);
		}
	}
	
	public Room getRoomAtIndex(int index){
		return Rooms.get(index);
	}
	
}
