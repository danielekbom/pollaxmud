package pollaxmud.entities;
import pollaxmud.enums.ItemType;

public class Item {
	protected int Weight;
	protected ItemType Type;
	protected String Name;
	
	public ItemType getType(){
		return Type;
	}
	
	public int getWeight(){
		return Weight;
	}
	
	public String getName(){
		return Name;
	}
	
	public void printName(){
		System.out.print(Name);
	}
}
