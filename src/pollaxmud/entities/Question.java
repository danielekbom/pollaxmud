package pollaxmud.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A question to be asked by teachers.
 * A question consists of a Question, an Answer and a list of Options.
 * @author Oscar and Daniel
 *
 */
public class Question {
	private String Question;
	private String Answer;
	private List<String> Options = new ArrayList<String>();
	
	/**
	 * The constructor of a question.
	 * @param question A string representing the actual question.
	 * @param answer The correct answer of a question.
	 * @param option1 One of the answer options.
	 * @param option2 One of the answer options.
	 */
	public Question(String question, String answer, String option1, String option2) {
		this.Question = question;
		this.Answer = answer;
		this.Options.add(answer);
		this.Options.add(option1);
		this.Options.add(option2);
		this.shuffleOptions();
	}
	
	/**
	 * Method to print the question.
	 */
	public void printQuestion() {
		System.out.println(this.Question);
	}
	
	/**
	 * Method to print the question's options.
	 * Three options are printed if the parameter haveBook is false, else two options.
	 * @param haveBook Whether two or three options should be printed.
	 */
	public void printOptions(boolean haveBook) {
		int i = 1;
		for(String option: Options) {
			if(haveBook && !option.equals(Answer)){
				haveBook = false;
				i++;
				continue;
			}
			System.out.println(i+": " + option);
			i++;
		}
	}
	
	/**
	 * The elements in the options list are shuffled.
	 */
	public void shuffleOptions() {
		Collections.shuffle(this.Options);
		Collections.shuffle(this.Options);
	}
	
	/**
	 * Checks whether a given answer is correct or not.
	 * @param answer The given answer.
	 * @return True if the answer is correct, else false.
	 */
	public boolean checkAnswer(int answer){
		if(Options.get(answer-1).equals(Answer)){
			return true;
		}
		return false;
	}
}
