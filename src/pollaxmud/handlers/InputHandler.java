package pollaxmud.handlers;

import java.awt.Canvas;
import java.util.Random;

import pollaxmud.Enums.CreatureType;
import pollaxmud.Enums.Direction;
import pollaxmud.entities.Course;
import pollaxmud.entities.Creature;
import pollaxmud.entities.Item;
import pollaxmud.entities.Player;
import pollaxmud.entities.Teacher;
import pollaxmud.main.Pollaxmud;
import pollaxmud.world.Room;

public class InputHandler {

	public static void handleInput(String inputString, Player player, Canvas canvas){
		switch(inputString){
		case "go north":
		case "n":
			handleInputGo(player, canvas, Direction.NORTH);
			break;
		case "go east":
		case "e":
			handleInputGo(player, canvas, Direction.EAST);
			break;
		case "go south":
		case "s":
			handleInputGo(player, canvas, Direction.SOUTH);
			break;
		case "go west":
		case "w":
			handleInputGo(player, canvas, Direction.WEST);
			break;
		case "inventory":
		case "i":
			player.printBackpackInventory();
			break;
		case "finished":
		case "f":
			handleInputFinished(player);
			break;
		case "unfinished":
		case "u":
			handleInputUnfinished(player);
			break;
		case "quit":
			break;
		default:
			if(inputString.startsWith("pick up")){
				handleInputPickUp(player, inputString);
			}else if(inputString.startsWith("use key with")){
				handleInputUseKey(player, inputString);
			}else if(inputString.startsWith("talk to")){
				handleInputTalkTo(player, inputString);
			}else{
				System.out.println("Unknown command!");
			}
		}
	}
	
	private static void handleInputGo(Player player, Canvas canvas, Direction direction) {
		if(player.walk(direction)) {
			canvas.repaint();
			player.getCurrentLocation().printEntranceText();
			if(player.getCurrentLocation().containsTeacher()){
				Teacher talkingTeacher = player.getCurrentLocation().getRandomTeacher();
				int randQuest = new Random().nextInt(4);
				if(player.isPassedCourse(talkingTeacher.getCourse()) && (randQuest == 0 || randQuest == 1)){
					if(talkingTeacher.askQuestion()){
						System.out.println("Im glad you still remember.");
						System.out.println("Good bye!");
					}else{
						if(player.moveCourse(talkingTeacher.getCourse(), false)){
							System.out.println("Wrong answer! You need to take this course again,");
							System.out.println("moving it to your list of unfinished courses.");
							System.out.println("Cya!");
						}else{
							System.out.println("Error!");
						}
					}
				}
				if(player.isUnfinishedCourse(talkingTeacher.getCourse()) && (randQuest != 3)){
					if(talkingTeacher.askQuestion()){
						if(player.moveCourse(talkingTeacher.getCourse(), true)){
							System.out.println("That is correct, congratulations!");
							System.out.println("You have passed this course.");
							System.out.println("Good bye!");
						}else{
							System.out.println("Error!");
						}
					}else{
						System.out.println("Wrong answer! Good luck next time.");
						System.out.println("Cya!");
					}
				}
			}
		}
	}
	
	private static void handleInputFinished(Player player){
		System.out.println("Finished courses: ");
		player.printCourses(true);
	}
	
	private static void handleInputUnfinished(Player player){
		System.out.println("Unfinished courses: ");
		player.printCourses(false);
	}
	
	private static void handleInputPickUp(Player player, String inputString) {
		if(inputString.length() <= 8){
			System.out.println("No item specified!");
			return;
		}
		String itemName = inputString.substring(8);
		Item itemToPickUp = player.getCurrentLocation().getItemByName(itemName);
		if(itemToPickUp != null){
			player.addItemToBackpack(itemToPickUp);
			player.getCurrentLocation().deleteItemByName(itemName);
			System.out.println("You picked up: " + itemName);
		}else{
			System.out.println("Item does not exists!");
		}
	}
	
	private static void handleInputUseKey(Player player, String inputString){
		if(!player.hasKey()){
			System.out.println("You dont have any key!");
			return;
		}
		if(inputString.length() <= 13){
			System.out.println("Invalid direction!");
			return;
		}
		Direction direction = Direction.stringToDirection(inputString.substring(13));
		if(direction == null){
			System.out.println("Not a valid direction!");
			return;
		}
		Room roomToUnlock = player.getCurrentLocation().getRoomInDirection(direction);
		if(roomToUnlock != null && !roomToUnlock.getUnlocked()){
			roomToUnlock.unlock();
			player.removeKeyFromBackpack();
			System.out.println("Door unlocked!");
		}else{
			System.out.println("No locked door in that direction!");
		}
	}
	
	private static void handleInputTalkTo(Player player, String inputString){
		if(inputString.length() <= 8){
			System.out.println("Invalid talk command!");
			return;
		}
		String creatureName = inputString.substring(8);
		Creature talkingTo = player.getCurrentLocation().getCreatureByName(creatureName);
		if(talkingTo == null){
			System.out.println("Can not find anyone with that name!");
			return;
		}
		if(talkingTo.getType() == CreatureType.TEACHER){
			Teacher teacher = (Teacher)talkingTo;
			System.out.println("Hi,\nmy name is " + teacher.getName());
			System.out.println("I am the teacher for \"" + teacher.getCourseName() + "\".");
			if(!player.isPassedCourse(teacher.getCourse()) && !player.isUnfinishedCourse(teacher.getCourse())){
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
		}
	}
}
