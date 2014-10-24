package pollaxmud.entities;

import pollaxmud.world.Room;

public class Player {

	Room CurrentLocation;
	
	public Player(Room location){
		CurrentLocation = location;
	}
	
	public Room getCurrentLocation(){
		return CurrentLocation;
	}
	
}
