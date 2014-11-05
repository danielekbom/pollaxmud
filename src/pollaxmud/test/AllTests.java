package pollaxmud.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	CourseTest.class,
	RoomTest.class , 
	PlayerTest.class, 
	BackpackTest.class, 
	TeacherTest.class, 
	SphinxTest.class,
	WorldTest.class})
public class AllTests {

}
