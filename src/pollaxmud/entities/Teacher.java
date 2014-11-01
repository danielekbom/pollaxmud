package pollaxmud.entities;

import pollaxmud.Enums.CreatureType;

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
	
	/**
	 * Get the course the teacher is teaching in.
	 * @return The course name as a String
	 */
	public String getCourse() {
		return Course.getName();
	}
	
	// TODO check for answers and return true if correct?
	// Kan man öppna en ny scanner när en redan körs?
	public boolean askQuestion() {
		System.out.println("I have a question for you:");
		Question askQuestion = this.Course.returnRandomQuestion();
		askQuestion.printQuestion();
		askQuestion.printOptions();
		return true;
	}
}
