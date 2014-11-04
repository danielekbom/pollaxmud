package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a course.
 * A course consists of a Name(title), a CourseBook, an amount of high school points it is worth and a list of Questions.
 * @author Daniel and Oscar
 *
 */
public class Course {
	
	private String Name;
	private Book CourseBook;
	private int HP;
	private List<Question> Questions;
	
	/**
	 * The constructor for a course.
	 * @param name The name of the course.
	 * @param book The book that corresponds to the course.
	 * @param HP The amount of high school points the course is worth.
	 */
	public Course(String name, Book book, int HP) {
		this.Name = name;
		this.CourseBook = book;
		this.HP = HP;
		this.Questions  = new ArrayList<Question>();
	}
	
	/**
	 * Method to get the course name.
	 * @return The name of the course.
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * Abbreviates the course name.
	 * @return If the course name is greater than 25 characters, then the first 26 characters of the course name + "...", else the course name.
	 */
	public String getAbbrName(){
		if(Name.length() > 25){
			return Name.substring(0, 25) + "...";
		}
		return Name;
	}
	
	/**
	 * Getter for the course HP.
	 * @return The course HP.
	 */
	public int getHP(){
		return HP;
	}
	
	/**
	 * Method for getting the name of the book that corresponds to the course.
	 * @return If the course have a corresponding book (i.e. Book is not null), then the name of the book, else "No book".
	 */
	public String getBookName(){
		if(CourseBook == null){
			return "No book";
		}
		return CourseBook.getName();
	}
	
	/**
	 * Adds the question newQuestion to the course Question list.
	 * @param newQuestion The question to add.
	 */
	public void addQuestion(Question newQuestion) {
		this.Questions.add(newQuestion);
	}
	
	/**
	 * Get a random question from the course Question list.
	 * @return A random question from Question list.
	 */
	public Question returnRandomQuestion() {
		int length = this.Questions.size();
		Random random = new Random();
		return this.Questions.get(random.nextInt(length));
	}
	
	/**
	 * Check if the course have any questions in its Question list or not.
	 * @return True if the Question list is empty, else false.
	 */
	public boolean hasNoQuestions() {
		if(this.Questions.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method that prints all the questions in the course Question list.
	 */
	public void printAllQuestions() {
		for(Question question: this.Questions) {
			question.printQuestion();
			question.printOptions(false);
		}
	}
	
	/**
	 * Method for getting the Book that corresponds to the course.
	 * @return The Book corresponding to the course.
	 */
	public Book getBook(){
		return CourseBook;
	}
}
