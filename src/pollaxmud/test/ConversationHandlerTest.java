package pollaxmud.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pollaxmud.entities.Course;
import pollaxmud.entities.Player;
import pollaxmud.entities.Teacher;
import pollaxmud.handlers.ConversationHandler;
import pollaxmud.world.Room;

public class ConversationHandlerTest {
	private Course testCourse1 = new Course("Test course", null, 5);
	private Teacher testTeacher1 = new Teacher("Teacher name", testCourse1);
	// Test Player
	private Room testRoom1 = new Room("Test room 1", true);
	private List<Course> FinishedCourses = new ArrayList<Course>();
	private Player testPlayer = new Player(testRoom1, FinishedCourses);

	@Test
	public void testTeacherIntroduction() {
		try {
			// Test with null 
			ConversationHandler.teacherIntroduction(null);
		} catch(Exception e) {
			fail("Failed with null input");
		}
		try {
			// Test with teacher
			ConversationHandler.teacherIntroduction(testTeacher1);
		} catch(Exception e) {
			fail("Failed");
		}
	}
	
	

}
