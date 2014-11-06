package pollaxmud.handlers;

import pollaxmud.entities.Player;
import pollaxmud.entities.Teacher;
import pollaxmud.main.Pollaxmud;

/**
 * Class used to for conversations.
 * @author Daniel
 *
 */
public class ConversationHandler {

	/**
	 * Prints the introduction of a teacher.
	 * @param teacher The teacher which is talking.
	 */
	public static void teacherIntroduction(Teacher teacher){
		System.out.println("Hi student,\nMy name is " + teacher.getName());
		System.out.println("I am the teacher of \"" + teacher.getCourseName() + "\".");
	}
	
	/**
	 * Prints the conversation when the player is enrolling on a course.
	 * @param player The player to be enrolled.
	 * @param teacher The teacher who is talking.
	 */
	public static void enrollConversation(Player player, Teacher teacher){
		teacherIntroduction(teacher);
		System.out.println("You are not enrolled on my course, do you want to enroll? (yes/no)");
		String userAnswer = Pollaxmud.scanner.nextLine();
		while(!userAnswer.equalsIgnoreCase("yes") && !userAnswer.equalsIgnoreCase("no")){
			System.out.println("Excuse me?");
			userAnswer = Pollaxmud.scanner.nextLine();
		}
		if(userAnswer.equalsIgnoreCase("yes")){
			if(player.addNewCourseToUnfinished(teacher.getCourse())){
				System.out.println("Okey, you are now enrolled at \"" + teacher.getCourseName() + "\".");
				System.out.println("Good luck with the studies.\nCya!");
			}else{
				System.out.println("Error!");
			}
		}else if(userAnswer.equalsIgnoreCase("no")){
			System.out.println("Allright, good luck anyway!\nCome back if you change your mind.\nGood bye!");
		}
	}
	
	/**
	 * Prints the conversation if a player talks to a teacher that teaches an already finished course.
	 * @param player The player who is talking to the teacher.
	 * @param teacher The teacher who is talking.
	 */
	public static void finishedCourseConversation(Player player, Teacher teacher){
		boolean haveBook = player.haveBookForCourse(teacher.getCourse());
		teacherIntroduction(teacher);
		System.out.println("I can see that you have already finished my course.");
		System.out.println("Here is a question to see if you still have the required knowledge:");
		if(teacher.askQuestion(haveBook)){
			System.out.println("Im glad you still remember.");
			System.out.println("Good bye!");
		}else{
			if(player.moveCourse(teacher.getCourse(), false)){
				System.out.println("Wrong answer! You need to take this course again,");
				System.out.println("moving it to your list of unfinished courses.");
				System.out.println("Cya!");
			}else{
				System.out.println("Error!");
			}
		}
	}
	
	/**
	 * Prints the conversation if a player talks to a teacher that teaches a course that is not finished.
	 * @param player The player who is talking to the teacher.
	 * @param teacher The teacher who is talking.
	 */
	public static void unfinishedCourseConversation(Player player, Teacher teacher){
		boolean haveBook = player.haveBookForCourse(teacher.getCourse());
		teacherIntroduction(teacher);
		System.out.println("Give me the correct answer on the following question and you will pass the course.");
		if(haveBook){
			System.out.println("And since you are a proper student and have the coursebook with you I will make it a bit easier for you.");
		}
		if(teacher.askQuestion(haveBook)){
			if(player.moveCourse(teacher.getCourse(), true)){
				System.out.println("That is correct, congratulations!");
				System.out.println("You have passed this course.");
				System.out.println("Good bye!");
			}else{
				System.out.println("Error!");
			}
		}else{
			System.out.println("Wrong answer! Good luck next time.");
			if(!haveBook){
				System.out.println("A little tip, - bring the coursebook next time, it will make it a little bit easier for you.");
			}
			System.out.println("Cya!");
		}
	}
	
}
