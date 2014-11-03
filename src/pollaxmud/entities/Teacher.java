package pollaxmud.entities;

import pollaxmud.enums.CreatureType;
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
	
	public boolean askQuestion() {
		Question askQuestion = this.Course.returnRandomQuestion();
		System.out.println("---------------------------------------");
		askQuestion.printQuestion();
		askQuestion.printOptions();
		System.out.println("---------------------------------------");
		String input = Pollaxmud.scanner.nextLine();
		while(!input.equals("1") && !input.equals("2") && !input.equals("3")){
			System.out.println("Wrong input!");
			input = Pollaxmud.scanner.nextLine();
		}
		return askQuestion.checkAnswer(Integer.parseInt(input));
	}
}
