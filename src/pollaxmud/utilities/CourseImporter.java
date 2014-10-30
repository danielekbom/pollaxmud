package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;

public class CourseImporter {

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
				Book cBook = findBook(data[1], books);
				newCourse = new Course(data[0], cBook ,Integer.parseInt(data[2]));
				courses.add(newCourse);
			}
			fileReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return courses;
	}
	
	private static Book findBook(String name, List<Book> Books) {
		for(Book book : Books) {
			if(book.getName().equals(name)) {
				return book;
			}
		}
		return null;
	}
}
