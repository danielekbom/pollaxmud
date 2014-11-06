/**
 * @author      Daniel Ekbom, Oscar Sanden
 * @version     1.0
 * @since       2014-10-27
 */

package pollaxmud.main;

import java.awt.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Player;
import pollaxmud.entities.Sphinx;
import pollaxmud.entities.Teacher;
import pollaxmud.gui.MapCanvas;
import pollaxmud.handlers.InputHandler;
import pollaxmud.utilities.BookImporter;
import pollaxmud.utilities.CourseImporter;
import pollaxmud.utilities.TeacherImporter;
import pollaxmud.utilities.WorldImporter;
import pollaxmud.utilities.QuestionImporter;
import pollaxmud.world.World;

import javax.swing.JFrame;

/**
 * The starting point of the game which contains the main method.
 * A World and imported lists of Books, Courses and Teachers are created.
 * Also a Player and a public Scanner is created.
 * @author Daniel
 *
 */
public class Pollaxmud {

	private static World StartingWorld;
	private static Player PlayerOne;
	private static List<Book> Books;
	private static List<Course> Courses;
	private static List<Teacher> Teachers;
	private static List<Sphinx> TheSphinx;
	private static boolean GameFinished;
	public static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Main method which is the starting point of the game.
	 * World, Player, Books, Courses, Teachers and a Sphinx are created.
	 * This method also contains the actual game loop, which is a loop looking for user input until "quit" is inputed, which exits the game.  
	 * @param args Program parameters.
	 */
	public static void main(String[] args) {
		GameFinished = false;
		importStuff();
		
		TheSphinx = new ArrayList<Sphinx>();
		TheSphinx.add(new Sphinx());
		
		initializeWorld();
		
		PlayerOne = new Player(StartingWorld.getRoomAtIndex(0), Courses);
		PlayerOne.getCurrentLocation().printEntranceText();
		
		Canvas mapCanvas = new MapCanvas(PlayerOne);
        JFrame map = new JFrame();
		initializeMap(mapCanvas, map);
		
		//====================== TEST
		/*System.out.println("Printing all questions: ");
		for(Course course: Courses) {
			System.out.println("==========\nCourse: "+ course.getAbbrName()+ "\nQuestions:");
			course.printAllQuestions();
		}*/
		//====================== END TEST
		
		String inputString = "";
		while(!inputString.equalsIgnoreCase("quit") && !GameFinished){
			inputString = scanner.nextLine();
			InputHandler.handleInput(inputString, PlayerOne, mapCanvas);
		}
		exitGame(map);
	}
	
	/**
	 * Method that imports parts of the game.
	 * The Books, Courses and Teachers are imported and assigned to its corresponding fields.
	 * The Questions are also imported and assigned to its corresponding courses.
	 */
	private static void importStuff() {
		Books = BookImporter.ImportBooks();
		Courses = CourseImporter.ImportCourses(Books);
		Teachers = TeacherImporter.ImportTeachers(Courses);
		QuestionImporter.ImportQuestions(Courses);
	}

	/**
	 * Initializes the world.
	 * All the Rooms are imported and items and creatures are put at randomly Rooms in the World.
	 */
	public static void initializeWorld(){
		StartingWorld = WorldImporter.ImportWorld();
		StartingWorld.putItemsRandomly(Books);
		StartingWorld.addKeysToWorld();
		StartingWorld.putCreaturesRandomly(Teachers, false);
		StartingWorld.putCreaturesRandomly(TheSphinx, true);
	}
	
	/**
	 * Initializes the JFrame that shows the map.
	 * @param mapCanvas The canvas that contains the map.
	 * @param map The JFrame to initialize.
	 */
	public static void initializeMap(Canvas mapCanvas, JFrame map){
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setSize(340, 670);
        map.setTitle("Pollax Map");
        map.setResizable(false);
        map.add(mapCanvas);
        map.setVisible(true);
	}
	
	/**
	 * Setter for the GameFinished field.
	 * @param gameFinished The value to set to the GameFinished field.
	 */
	public static void setGameFinished(boolean gameFinished){
		GameFinished = gameFinished;
	}
	
	/**
	 * Method that exists the game.
	 * The map JFrame and Scanner are closed.
	 * @param map The JFrame to close.
	 */
	private static void exitGame(JFrame map){
		map.setVisible(false);
		map.dispose();
		scanner.close();
		System.out.println("Exits game");
	}
}
