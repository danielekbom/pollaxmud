package pollaxmud.entities;

public class Book extends Item{
	private String Author;
	private String Year;
	
	public Book(String name, String author, String year, int weight) {
		this.Name = name;
		this.Author = author;
		this.Year = year;
		this.Weight = weight;
		this.Type = ItemType.BOOK;
	}
	
	@Override
	public void printName(){
		System.out.print("Book: ");
		super.printName();
		System.out.print(" (" + Author + ")");
	}
	
}	
