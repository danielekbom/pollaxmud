package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import pollaxmud.entities.Course;
import pollaxmud.entities.Question;
import pollaxmud.exceptions.CustomException;

/**
 * Imports Questions from questions.txt
 * The format of the file is one Question on each line.
 * The format of a line is "CourseName;QuestionString;Correct answer;Wrong answer;Another wrong answer".
 * The text file needs to be placed in the project root.
 * @author Daniel and Oscar
 */
public class QuestionImporter {
	
	/**
	 * Imports the questions and adds them to its corresponding course.
	 * @param Courses A list of courses which the questions should be added to.
	 */
	public static void ImportQuestions(List<Course> Courses){
		
		try{
			File file = new File("questions.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] data;
			Question newQuestion;
			while((line = bufferedReader.readLine()) != null){
				data = line.split(";");
				if(data.length != 5){
					throw new CustomException("Import file questions.txt has invalid format!","CorruptedImportFileException");
				}
				for(Course course : Courses) {
					if(course.getName().equals(data[0])) {
						newQuestion = new Question(data[1],data[2],data[3],data[4]);
						course.addQuestion(newQuestion);
						break;
					}
				}
			}
			addStandardQuestion(Courses);
			bufferedReader.close();
			fileReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(CustomException e){
			e.printMessage();
		}catch(Exception e){
			System.err.println(e.getMessage() + ":\n\tError while impoting Questions! You can not play like this.\n"
					+ "Your questions.txt may be corrupted.");
		}
	}
	
	/**
	 * Adds a standard question to the courses that did not had any corresponding questions in the questions.txt file.
	 * @param Courses List of courses.
	 */
	private static void addStandardQuestion(List<Course> Courses) {
		for(Course course : Courses) {
			if(course.hasNoQuestions()) {
				Question newQuestion = new Question("What is my favourite color?", "Blue", "Red", "Green");
				course.addQuestion(newQuestion);
			}
		}
	}
}
