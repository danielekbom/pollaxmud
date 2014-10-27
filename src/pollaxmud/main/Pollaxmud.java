/**
 * @author      Daniel Ekbom, Oscar Sandén <address @ example.com>
 * @version     1.0
 * @since       2014-10-27
 */

package pollaxmud.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Item;
import pollaxmud.entities.Player;
import pollaxmud.handlers.InputHandler;
import pollaxmud.utilities.BookImporter;
import pollaxmud.utilities.CourseImporter;
import pollaxmud.utilities.WorldImporter;
import pollaxmud.world.World;

public class Pollaxmud {

	private static World StartingWorld;
	private static Player PlayerOne;
	private static List<Book> Books;
	private static List<Course> Courses;
	
	public static void main(String[] args) {
		Books = BookImporter.ImportBooks();
		Courses = CourseImporter.ImportCourses(Books);
		StartingWorld = WorldImporter.ImportWorld();
		StartingWorld.putItemsRandomly(Books);
		
		PlayerOne = new Player(StartingWorld.getRoomAtIndex(0));
		PlayerOne.getCurrentLocation().printEntranceText();
		
		Scanner scanner = new Scanner(System.in);
		String inputString = "";
		while(!inputString.equalsIgnoreCase("quit")){
			inputString = scanner.next();
			InputHandler.handleInput(inputString, PlayerOne);
		}
		scanner.close();
		
		System.out.println("Exits game");
		
	}

}
