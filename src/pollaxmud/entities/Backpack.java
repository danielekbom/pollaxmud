package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;
import pollaxmud.Enums.ItemType;

/**
 * This is the class for the players Backpack
 * @author Oscar and Daniel
 *
 */
public class Backpack {

	private int Capacity;
	private List<Item> Inventory;
	
	/**
	 * Constructor for a backpack
	 */
	public Backpack(){
		Capacity = 10;
		Inventory = new ArrayList();
	}
	
	/**
	 * Return the amount of free space in a backpack.
	 * @return The amount of free space as an int.
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
	 * @return Return true if the item was added successfully.
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
	
}
