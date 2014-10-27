package pollaxmud.entities;
import pollaxmud.entities.Item;

public class Book extends Item{
	private String Name;
	private String Author;
	private String Year;
	private int Weight;
	private ItemType Type;
	
	public Book(String name, String author, String year, int weight) {
		this.Name = name;
		this.Author = author;
		this.Year = year;
		this.Weight = weight;
		this.Type = ItemType.BOOK;
	}
	
	public String getName() {
		return Name;
	}
	
	public ItemType getType(){
		return Type;
	}
	
}	
