package pollaxmud.entities;
import pollaxmud.enums.ItemType;

/**
 * This class represents a Book, which inherits from the Item class.
 * A Book have an Author and a year of publication.
 * @author Daniel and Oscar
 *
 */
public class Book extends Item{
	private String Author;
	private String Year;
	
	/**
	 * Constructor for a Book.
	 * @param name The name of a book, probably the title of the book.
	 * @param author The author of the book.
	 * @param year The book's year of publication.
	 * @param weight The book's weight in liters
	 */
	public Book(String name, String author, String year, int weight) {
		this.Name = name;
		this.Author = author;
		this.Year = year;
		this.Weight = weight;
		this.Type = ItemType.BOOK;
	}
	
	/**
	 * Prints the name and author of the book.
	 */
	@Override
	public void printName(){
		System.out.print("Book: ");
		super.printName();
		System.out.print(" (" + Author + ")");
	}
	
	/**
	 * Method to get the book's year of publication.
	 * @return The book's year.
	 */
	public String getYear(){
		return Year;
	}
	
}	
