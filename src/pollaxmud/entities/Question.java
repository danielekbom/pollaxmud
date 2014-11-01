package pollaxmud.entities;

/**
 * A question to be asked by teachers.
 * @author oscar
 *
 */
public class Question {
	private String Question;
	private String Answer;
	
	public Question(String question, String answer) {
		this.Question = question;
		this.Answer = answer;
	}
	
	public String printQuestion() {
		return this.Question;
	}
}
