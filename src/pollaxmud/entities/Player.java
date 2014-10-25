package pollaxmud.entities;

import pollaxmud.world.Room;

public class Player {

	Room CurrentLocation;
	
	public Player(Room location){
		CurrentLocation = location;
	}
	
	public void setCurrentLocation(Room newLocation){
		CurrentLocation = newLocation;
	}
	
	public Room getCurrentLocation(){
		return CurrentLocation;
	}
	
}
