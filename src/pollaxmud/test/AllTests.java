package pollaxmud.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	// Entities
	BookTest.class,
	KeyTest.class,
	CourseTest.class,
	QuestionTest.class,
	RoomTest.class , 
	PlayerTest.class, 
	BackpackTest.class, 
	TeacherTest.class, 
	SphinxTest.class,
	WorldTest.class,
	InputHandlerTest.class,
	// Handlers
	ConversationHandlerTest.class})
public class AllTests {

}
