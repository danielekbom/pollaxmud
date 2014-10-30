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
	
	public int getHP(){
		return HP;
	}
}
