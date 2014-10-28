package pollaxmud.entities;

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
	
	public enum ItemType{
		BOOK, KEY;
	}
}
