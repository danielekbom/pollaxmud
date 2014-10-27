package pollaxmud.entities;
import pollaxmud.entities.Item;

public class Book extends Item{
	private String name;
	private String author;
	private String year;
	private int weight;
	
	public Book(String name, String author, String year, int weight) {
		this.name = name;
		this.author = author;
		this.year = year;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	
}	
