package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Question;

public class QuestionImporter {
	
	public static void ImportQuestions(List<Course> Courses){
		
		try{
			File file = new File("Questions.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] data;
			Question newQuestion;
			while((line = bufferedReader.readLine()) != null){
				data = line.split(";");
				for(Course course : Courses) {
					if(course.getName().equals(data[0])) {
						newQuestion = new Question(data[1],data[2]);
						course.addQuestion(newQuestion);
						break;
					}
				}
			}
			addBasicQuestion(Courses);
			fileReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static void addBasicQuestion(List<Course> Courses) {
		for(Course course : Courses) {
			if(course.hasNoQuestions()) {
				Question newQuestion = new Question("What is my favourite color?", "Blue");
				course.addQuestion(newQuestion);
			}
		}
	}
}
