package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;

import pollaxmud.enums.ItemType;

/**
 * This is the class for the players Backpack.
 * A Backpack have a max Capacity and a list of Items called Inventory.
 * @author Oscar and Daniel
 *
 */
public class Backpack {

	private int Capacity;
	private List<Item> Inventory;
	
	/**
	 * Constructor for a backpack.
	 */
	public Backpack(){
		Capacity = 10;
		Inventory = new ArrayList<Item>();
	}
	
	/**
	 * Return the amount of free space in a backpack.
	 * @return The amount of free space as an integer.
	 */
	public int getSpace(){
		int weight = 0;
		for(Item item : Inventory){
			weight += item.getWeight();
		}
		return Capacity - weight;
	}
	
	/**
	 * Adds an item to a backpack.
	 * @param item The item to add to the backpack.
	 * @return Return true if the item was added successfully, else false.
	 */
	public boolean addItem(Item item){
		if(getSpace() >= item.getWeight()){
			Inventory.add(item);
			return true;
		}
		System.out.println("Not enough space in backpack!");
		return false;
	}
	
	/**
	 * Will print the backpacks inventory to the terminal.
	 */
	public void printInventory(){
		if(Inventory.isEmpty()){
			System.out.println("Backpack is empty!");
			return;
		}
		System.out.println("Inventory:\tFree space: " + getSpace() + " liter");
		for(Item item : Inventory){
			if(item.getType() == ItemType.BOOK){
				System.out.println("Book: " + ((Book) item).getName() + " (" + item.getWeight() + " liter)");
			}else if(item.getType() == ItemType.KEY){
				System.out.println(item.getName() + " (" + item.getWeight() + " liter)");
			}
		}
		System.out.println("------------------------------");
	}
	
	/**
	 * Checks if at least one key exists in the backpacks inventory.
	 * @return True if a key exists, else false.
	 */
	public boolean containsKey(){
		for(Item item : Inventory){
			if(item.getType() == ItemType.KEY){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Removes an item from the backpacks inventory with the same name as itemName if such item exists.
	 * @param itemName The name of the item to remove.
	 * @return True if an item was successfully removed, else false.
	 */
	public boolean removeItem(String itemName){
		int indexToRemove = -1;
		for(Item item : Inventory){
			if(item.getName().equalsIgnoreCase(itemName)){
				indexToRemove = Inventory.indexOf(item);
			}
		}
		if(indexToRemove != -1){
			Inventory.remove(indexToRemove);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if itemToFind exists in the backpacks inventory list.
	 * @param itemToFind The item to check if it exists.
	 * @return True if itemToFind exists, else false.
	 */
	public boolean containsItem(Item itemToFind){
		if(Inventory.isEmpty()) return false;
		for(Item item : Inventory){
			if(item == itemToFind){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get item from the backpacks inventory list by name.
	 * @param name The name of the item to be returned.
	 * @return The item with the name "name", and null if such item does not exist.
	 */
	public Item getItemByName(String name){
		for(Item item : Inventory){
			if(item.getName().equalsIgnoreCase(name)){
				return item;
			}
		}
		return null;
	}
	
}
