package pollaxmud.entities;

import pollaxmud.enums.CreatureType;
import pollaxmud.main.Pollaxmud;

/**
 * This class represents a teacher in the game.
 * The teacher can ask questions to the player, which then have the chance to complete courses.
 * A teacher inherits from the Creature class. It has a Name, a CreatureType and a specific course bounded to itself.
 * @author Daniel and Oscar
 *
 */
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
	 * Getter for the teacher's course.
	 * @return The course that is bounded to the teacher.
	 */
	public Course getCourse() {
		return this.Course;
	}
	
	/**
	 * Get the Name of the course the teacher is teaching in.
	 * @return The name of the teacher's course.
	 */
	public String getCourseName() {
		return Course.getName();
	}
	
	/**
	 * Asks a question from the bounded course to the user.
	 * The question and options are printed and the player is then able to give an answer.
	 * @param haveBook Whether the player have to book for the bounden course.
	 * @return True if the player gives the correct answer, else false.
	 */
	public boolean askQuestion(Boolean haveBook) {
		Question askQuestion = this.Course.returnRandomQuestion();
		System.out.println("---------------------------------------");
		askQuestion.printQuestion();
		askQuestion.printOptions(haveBook);
		System.out.println("---------------------------------------");
		String input = Pollaxmud.scanner.nextLine();
		while(!input.equals("1") && !input.equals("2") && !input.equals("3")){
			System.out.println("Wrong input!");
			input = Pollaxmud.scanner.nextLine();
		}
		return askQuestion.checkAnswer(Integer.parseInt(input));
	}
}
