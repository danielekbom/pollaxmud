package pollaxmud.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pollaxmud.entities.Course;
import pollaxmud.entities.Player;
import pollaxmud.entities.Sphinx;
import pollaxmud.world.Room;

public class SphinxTest {
	// Courses
	private Course testCourse1 = new Course("Test Course 1", null, 150);
	private Course testCourse2 = new Course("Test Course 2", null, 30);
	private Course testCourse3 = new Course("Test Course 3", null, 50);
	// A room
	private Room testRoom1 = new Room("Test room 1", true);
	// List of finished courses
	private List<Course> FinishedCourses = new ArrayList<Course>();
	// The player
	private Player testPlayer = new Player(testRoom1, FinishedCourses);
	// The Sphinx
	private Sphinx testSphinx = new Sphinx();

	@Test
	public void testGraduate() {
		// Add courses to players unfinished courses list.
		// Should have 150 + 30 + 50 = 230 HP in unfinished.
		testPlayer.addNewCourseToUnfinished(testCourse1);
		testPlayer.addNewCourseToUnfinished(testCourse2);
		testPlayer.addNewCourseToUnfinished(testCourse3);
		if(testPlayer.moveCourse(testCourse1, true)) {
			// Should now have 150 HP in finished so should be False.
			assertFalse(testSphinx.tryToGraduate(testPlayer));
			if(testPlayer.moveCourse(testCourse2, true)) {
				// Should now have exactly 180 HP so should be true.
				assertTrue(testSphinx.tryToGraduate(testPlayer));
				if(testPlayer.moveCourse(testCourse3, true)) {
					// Should now have over 180 HP so should be true.
					assertTrue(testSphinx.tryToGraduate(testPlayer));
				} else {
					fail("Error on moveCourse");
				}
			} else {
				fail("Error on moveCourse");

			}
		} else {
			fail("Error on moveCourse");
		}
	}

}
