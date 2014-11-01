/**
 * @author      Daniel Ekbom, Oscar Sandén
 * @version     1.0
 * @since       2014-10-27
 */

package pollaxmud.main;

import java.awt.Canvas;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Item;
import pollaxmud.entities.Player;
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

public class Pollaxmud {

	private static World StartingWorld;
	private static Player PlayerOne;
	private static List<Book> Books;
	private static List<Course> Courses;
	private static List<Teacher> Teachers;
	
	public static void main(String[] args) {		
		Books = BookImporter.ImportBooks();
		Courses = CourseImporter.ImportCourses(Books);
		Teachers = TeacherImporter.ImportTeachers(Courses);
		QuestionImporter.ImportQuestions(Courses);

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
		
		Scanner scanner = new Scanner(System.in);
		String inputString = "";
		while(!inputString.equalsIgnoreCase("quit")){
			inputString = scanner.nextLine();
			InputHandler.handleInput(inputString, PlayerOne, mapCanvas);
		}
		
		scanner.close();
		System.out.println("Exits game");
	}
	
	public static void initializeWorld(){
		StartingWorld = WorldImporter.ImportWorld();
		StartingWorld.putItemsRandomly(Books);
		StartingWorld.addKeysToWorld();
		StartingWorld.putCreaturesRandomly(Teachers);
	}
	
	public static void initializeMap(Canvas mapCanvas, JFrame map){
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map.setSize(340, 670);
        map.setTitle("Pollax Map");
        map.setResizable(false);
        map.add(mapCanvas);
        map.setVisible(true);
	}
	
	private boolean validateGameConditiones(){
		boolean validGame = false;
		if(coursesHaveTeachers()){
			validGame = true;
		}
		return validGame;
	}
	
	private boolean coursesHaveTeachers(){
		return true;
	}

}
