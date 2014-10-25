package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;

import pollaxmud.world.Room;

public class Player {

	private List<Course> UnfinishedCourses = new ArrayList<Course>();
	private List<Course> FinishedCourses = new ArrayList<Course>();
	private Item[] Backpack = new Item[10];
	int HP = 60;
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
