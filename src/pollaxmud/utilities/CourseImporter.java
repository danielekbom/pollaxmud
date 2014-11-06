package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.exceptions.CustomException;

/**
 * Imports Courses from courses.txt
 * The format of the file is one Course on each line.
 * The format of a line is "CourseName;BookName;High school points".
 * The text file needs to be placed in the project root.
 * @author Daniel and Oscar
 */
public class CourseImporter {

	/**
	 * Imports the Courses.
	 * @param books A list of books.
	 * @return A list of the imported Courses.
	 */
	public static List<Course> ImportCourses(List<Book> books){
		List<Course> courses = new ArrayList<Course>();

		
		try{
			File file = new File("courses.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] data;
			Course newCourse;
			while((line = bufferedReader.readLine()) != null){
				data = line.split(";");
				if(data.length != 3){
					throw new CustomException("Import file courses.txt has invalid format!","CorruptedImportFileException");
				}
				Book cBook = findBook(data[1], books);
				newCourse = new Course(data[0], cBook ,Integer.parseInt(data[2]));
				courses.add(newCourse);
			}
			bufferedReader.close();
			fileReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(CustomException e){
			e.printMessage();
		}catch(Exception e){
			System.err.println(e.getMessage() + ":\n\tError while impoting Courses! You can not play like this.\n"
					+ "Your courses.txt may be corrupted.");
		}
		return courses;
	}
	
	/**
	 * Method to find the Book with the same name as a given string.
	 * @param name The given string used to find a book which name equals the string.
	 * @param Books A list of books to look in.
	 * @return The Book with the same name as the given string if such book exists, else null.
	 */
	private static Book findBook(String name, List<Book> Books) {
		for(Book book : Books) {
			if(book.getName().equals(name)) {
				return book;
			}
		}
		return null;
	}
}
