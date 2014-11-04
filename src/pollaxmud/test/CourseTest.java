package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.entities.Book;
import pollaxmud.entities.Course;
import pollaxmud.entities.Question;

public class CourseTest {
	// Book
	private Book testItem1 = new Book("Test book 1", "Test author 1", "1999", 3);
	// Courses
	private Course testCourse1 = new Course("TestCourse with a fairly long name", testItem1, 10);
	private Course testCourse2 = new Course("TestCourse2", null, 5);
	// Questions
	private Question Question1 = new Question("Question1?", "Answer", "Option1", "Option2");
	private Question Question2 = new Question("Question2?", "Answer", "Option1", "Option2");
	private Question Question3 = new Question("Question3?", "Answer", "Option1", "Option2");
	
	@Test
	public void testCourseGetters() {
		//Tests course 1 (Long name, with book)
		assertEquals(testCourse1.getName(), "TestCourse with a fairly long name");
		assertEquals(testCourse1.getAbbrName(), "TestCourse with a fairly ...");
		assertEquals(testCourse1.getHP(), 10);
		assertEquals(testCourse1.getBookName(), "Test book 1");
		//Tests course 2 (Short name, no book)
		assertEquals(testCourse2.getName(), "TestCourse2");
		assertEquals(testCourse2.getAbbrName(), "TestCourse2");
		assertEquals(testCourse2.getHP(), 5);
		assertEquals(testCourse2.getBookName(), "No book");
	}
	
	@Test
	public void testCourseQuestions() {
		assertTrue(testCourse1.hasNoQuestions());
		assertTrue(testCourse2.hasNoQuestions());
		
		//Add questions to course 1
		testCourse1.addQuestion(Question1);
		assertFalse(testCourse1.hasNoQuestions());
		assertEquals(testCourse1.returnRandomQuestion(), Question1);
		
		//Add questions to course 2
		testCourse2.addQuestion(Question2);
		assertFalse(testCourse2.hasNoQuestions());
		assertEquals(testCourse2.returnRandomQuestion(), Question2);

		// How to test for randomness?
		
	}

}
