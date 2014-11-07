package pollaxmud.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Question;
import pollaxmud.entities.Teacher;

public class TeacherTest {
	private Book testItem1 = new Book("Test book 1", "Test author 1", "1999", 3);
	private Course testCourse = new Course("TestCourse", testItem1, 10);
	private Teacher testTeacher = new Teacher("TestTeacher", testCourse);
	private Question testQuestion = new Question("What is my favourite color?", "Blue", "Red", "Green");
	
	@Test
	public void testTeacherGetters() {
		assertEquals(testTeacher.getCourse(), testCourse);
		assertEquals(testTeacher.getCourseName(), "TestCourse");
	}

	@Test
	public void testTeacherAskQuestion() {

	}
}
