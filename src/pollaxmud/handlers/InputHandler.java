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
					ConversationHandler.finishedCourseConversation(player, talkingTeacher);
				}else if(player.isUnfinishedCourse(talkingTeacher.getCourse()) && (randQuest != 3)){
					ConversationHandler.unfinishedCourseConversation(player, talkingTeacher);
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
			if(!player.isPassedCourse(teacher.getCourse()) && !player.isUnfinishedCourse(teacher.getCourse())){
				ConversationHandler.enrollConversation(player, teacher);
			}else if(player.isPassedCourse(teacher.getCourse())){
				ConversationHandler.finishedCourseConversation(player, teacher);
			}else if(player.isUnfinishedCourse(teacher.getCourse())){
				ConversationHandler.unfinishedCourseConversation(player, teacher);
			}
		}
	}
}
