package pollaxmud.entities;

public class Item {
	protected int Weight;
	protected ItemType Type;
	
	public ItemType getType(){
		return Type;
	}
	
	public enum ItemType{
		BOOK, KEY;
	}
}
