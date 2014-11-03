package pollaxmud.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A question to be asked by teachers.
 * @author oscar
 *
 */
public class Question {
	private String Question;
	private String Answer;
	private List<String> Options = new ArrayList<String>();
	
	public Question(String question, String answer, String option1, String option2) {
		this.Question = question;
		this.Answer = answer;
		this.Options.add(answer);
		this.Options.add(option1);
		this.Options.add(option2);
		this.shuffleOptions();
	}
	
	public void printQuestion() {
		System.out.println(this.Question);
	}
	
	public void printOptions() {
		int i = 1;
		for(String option: Options) {
			System.out.println(i+": " + option);
			i++;
		}
	}
	
	public void shuffleOptions() {
		Collections.shuffle(this.Options);
		Collections.shuffle(this.Options);
	}
	
	public boolean checkAnswer(int answer){
		if(Options.get(answer-1).equals(Answer)){
			return true;
		}
		return false;
	}
}
