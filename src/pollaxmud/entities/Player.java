package pollaxmud.entities;

import pollaxmud.world.Room;

public class Player {

	Room CurrentLocation;
	
	public Player(Room location){
		CurrentLocation = location;
	}
	
	public void walkNorth(){
		setCurrentLocation(CurrentLocation.getRoomNorth());
	}
	
	public void walkEast(){
		setCurrentLocation(CurrentLocation.getRoomEast());
	}
	
	public void walkSouth(){
		setCurrentLocation(CurrentLocation.getRoomSouth());
	}
	
	public void walkWest(){
		setCurrentLocation(CurrentLocation.getRoomWest());
	}
	
	public void setCurrentLocation(Room newLocation){
		CurrentLocation = newLocation;
	}
	
	public Room getCurrentLocation(){
		return CurrentLocation;
	}
	
}
