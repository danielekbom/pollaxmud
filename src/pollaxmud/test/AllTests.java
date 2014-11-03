package pollaxmud.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	RoomTest.class , 
	PlayerTest.class, 
	BackpackTest.class, 
	TeacherTest.class, 
	CourseTest.class})
public class AllTests {

}
