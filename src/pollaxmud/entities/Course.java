package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Course {
	
	private String Name;
	private Book CourseBook;
	private int HP;
	private List<Question> Questions;
	
	public Course(String name, Book book, int HP) {
		this.Name = name;
		this.CourseBook = book;
		this.HP = HP;
		this.Questions  = new ArrayList<Question>();
	}
	
	public String getName() {
		return Name;
	}
	
	public String getAbbrName(){
		if(Name.length() > 25){
			return Name.substring(0, 25) + "...";
		}
		return Name;
	}
	
	public int getHP(){
		return HP;
	}
	
	public String getBookName(){
		if(CourseBook == null){
			return "No book";
		}
		return CourseBook.getName();
	}
	
	public void addQuestion(Question newQuestion) {
		this.Questions.add(newQuestion);
	}
	
	public Question returnRandomQuestion() {
		int length = this.Questions.size();
		Random random = new Random();
		return this.Questions.get(random.nextInt(length));
	}
	
	public boolean hasNoQuestions() {
		if(this.Questions.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	//For testing purposes: Remove in final version
	public void printAllQuestions() {
		for(Question question: this.Questions) {
			question.printQuestion();
			question.printOptions(false);
		}
	}
	
	public Book getBook(){
		return CourseBook;
	}
}
