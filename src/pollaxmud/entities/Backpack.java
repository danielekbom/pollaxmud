package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Item.ItemType;

public class Backpack {

	int Capacity;
	List<Item> Inventory;
	
	public Backpack(){
		Capacity = 10;
		Inventory = new ArrayList();
	}
	
	public int getSpace(){
		int weight = 0;
		for(Item item : Inventory){
			weight += item.getWeight();
		}
		return Capacity - weight;
	}
	
	public boolean addItem(Item item){
		if(getSpace() >= item.getWeight()){
			Inventory.add(item);
			return true;
		}
		System.out.println("Not enough space in backpack!");
		return false;
	}
	
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
