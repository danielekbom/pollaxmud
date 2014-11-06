package pollaxmud.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pollaxmud.entities.Question;

public class QuestionTest {
	private Question testQuestion1 = new Question("Svaret är 42", "42", "1", "3");
	private Question testQuestion2 = new Question("Svaret är 42", "42", "42", "42");

	@Test
	public void testPrinting() {
		try {
			testQuestion1.printQuestion();
			testQuestion1.printOptions(false);
			testQuestion1.printOptions(true);
		} catch(Exception e){ 
			fail("Couldn't print questions.");
		}
	}
	
	@Test
	public void testAnswer() {
		assertTrue(testQuestion2.checkAnswer(1));
		assertTrue(testQuestion2.checkAnswer(2));
		assertTrue(testQuestion2.checkAnswer(3));		
	}
	
	

}
