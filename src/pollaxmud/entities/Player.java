package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;

//import com.sun.javafx.tk.PrintPipeline;

import pollaxmud.world.Room;

public class Player {

	private List<Course> UnfinishedCourses;
	private List<Course> FinishedCourses;
	private Backpack CurrentBackpack;
	private int HP;
	private Room CurrentLocation;
	
	public Player(Room location){
		UnfinishedCourses = new ArrayList<Course>();
		FinishedCourses = new ArrayList<Course>();
		HP = 60;
		CurrentLocation = location;
		CurrentBackpack = new Backpack();
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
	
	
	/**
	 * Set the players location to newLocation
	 *
	 * @param  newLocation The new location of the player.
	 */
	public void setCurrentLocation(Room newLocation){
		CurrentLocation = newLocation;
	}

	public Room getCurrentLocation(){
		return CurrentLocation;
	}
	
	public int getBackpackCapacity(){
		return CurrentBackpack.getSpace();
	}
	
	public void addItemToBackpack(Item item){
		CurrentBackpack.addItem(item);
	}
	
	public void printBackpackInventory(){
		CurrentBackpack.printInventory();
	}
	
}
