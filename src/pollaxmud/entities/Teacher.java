package pollaxmud.entities;

import java.util.Scanner;

import pollaxmud.Enums.CreatureType;
import pollaxmud.main.Pollaxmud;

public class Teacher extends Creature {
	private Course Course;
	
	/**
	 * Constructor for a Teacher.
	 * @param name The name of the teacher
	 * @param course The course the teacher is teaching.
	 */
	public Teacher(String name, Course course) {
		this.Name = name;
		this.Course = course;
		this.Type = CreatureType.TEACHER;
	}
	
	public Course getCourse() {
		return this.Course;
	}
	
	/**
	 * Get the course the teacher is teaching in.
	 * @return The course name as a String
	 */
	public String getCourseName() {
		return Course.getName();
	}
	
	// TODO check for answers and return true if correct?
	// Kan man öppna en ny scanner när en redan körs?
	public boolean askQuestion() {
		System.out.println("Hi student, I am teacher for " + getCourseName());
		System.out.println("I have a question for you:");
		Question askQuestion = this.Course.returnRandomQuestion();
		askQuestion.printQuestion();
		askQuestion.printOptions();
		String input = Pollaxmud.scanner.nextLine();
		while(!input.equals("1") && !input.equals("2") && !input.equals("3")){
			System.out.println("Wrong input!");
			input = Pollaxmud.scanner.nextLine();
		}
		return askQuestion.checkAnswer(Integer.parseInt(input));
	}
}
