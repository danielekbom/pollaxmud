package pollaxmud.entities;

public class Item {

	public String getName(){
		return "";
	}
	
	public ItemType getType(){
		return null;
	}
	
	public enum ItemType{
		BOOK, KEY;
	}
}
