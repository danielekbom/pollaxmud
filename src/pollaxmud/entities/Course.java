package pollaxmud.entities;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private String Name;
	private Book CourseBook;
	private int HP;
	private List<Question> Questions;
	
	public Course(String name, Book book, int HP) {
		this.Name = name;
		this.CourseBook = book;
		this.HP = HP;
		Questions  = new ArrayList<Question>();
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
}
