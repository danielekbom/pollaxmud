package pollaxmud.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pollaxmud.entities.Course;
import pollaxmud.entities.Player;
import pollaxmud.entities.Sphinx;
import pollaxmud.enums.CreatureType;
import pollaxmud.world.Room;

public class SphinxTest {
	// Courses
	private Course testCourse1 = new Course("Test Course 1", null, 150);
	private Course testCourse2 = new Course("Test Course 2", null, 30);
	private Course testCourse3 = new Course("Test Course 3", null, 50);
	// Test room
	private Room testRoom1 = new Room("Test room 1", true);
	
	// List of finished courses
	private List<Course> FinishedCourses = new ArrayList<Course>();
	// The player
	private Player testPlayer = new Player(testRoom1, FinishedCourses);
	// The Sphinx
	private Sphinx testSphinx = new Sphinx();
	
	@Test
	public void testGetters() {
		assertEquals(testSphinx.getName(), "Sphinx of Pollax");
		assertEquals(testSphinx.getType(), CreatureType.SPHINX);
	}
	
	@Test
	public void testGraduate() {
		// Add courses to players unfinished courses list.
		assertTrue(testPlayer.addNewCourseToUnfinished(testCourse1));
		assertTrue(testPlayer.addNewCourseToUnfinished(testCourse2));
		// Move to finished
		assertTrue(testPlayer.moveCourse(testCourse1, true));
		// Should now have 150 HP in finished so should be False.
		assertEquals(testPlayer.getFinishedCredits(), 150);
		assertFalse(testSphinx.tryToGraduate(testPlayer));
		assertTrue(testPlayer.moveCourse(testCourse2, true));
		// Should now have exactly 180 HP and no unfinished courses.
		assertEquals(testPlayer.getFinishedCredits(), 180);
		assertTrue(testSphinx.tryToGraduate(testPlayer));
		// Add a new course. Still 180 hp but 1 unfinished course.
		assertTrue(testPlayer.addNewCourseToUnfinished(testCourse3));
		assertEquals(testPlayer.getFinishedCredits(), 180);
		assertFalse(testSphinx.tryToGraduate(testPlayer));
		assertTrue(testPlayer.moveCourse(testCourse3, true));
		// Should now have over 180 HP and no unfinished courses.
		assertEquals(testPlayer.getFinishedCredits(), 230);
		assertTrue(testSphinx.tryToGraduate(testPlayer));
		}

}
