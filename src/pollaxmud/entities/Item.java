package pollaxmud.entities;
import pollaxmud.enums.ItemType;

/**
 * This class is used to represent Items, which other classes representing more specific items inherits from.
 * An item has a Weight, an ItemType and a Name.
 * @author Daniel and Oscar
 *
 */
public class Item {
	protected int Weight;
	protected ItemType Type;
	protected String Name;
	
	/**
	 * Getter for the Item's ItemType.
	 * @return The Type of the Item.
	 */
	public ItemType getType(){
		return Type;
	}
	
	/**
	 * Getter for the Item's Weight.
	 * @return The Weight of the Item.
	 */
	public int getWeight(){
		return Weight;
	}
	
	/**
	 * Getter for the Item's Name.
	 * @return The Name of the Item.
	 */
	public String getName(){
		return Name;
	}
	
	/**
	 * Prints the Item's Name.
	 */
	public void printName(){
		System.out.print(Name);
	}
}
