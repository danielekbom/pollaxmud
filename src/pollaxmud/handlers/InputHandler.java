package pollaxmud.handlers;

import java.awt.Canvas;
import java.util.Random;

import pollaxmud.entities.Creature;
import pollaxmud.entities.Item;
import pollaxmud.entities.Player;
import pollaxmud.entities.Sphinx;
import pollaxmud.entities.Teacher;
import pollaxmud.enums.CreatureType;
import pollaxmud.enums.Direction;
import pollaxmud.world.Room;

public class InputHandler {

	public static void handleInput(String inputString, Player player, Canvas canvas){
		switch(inputString){
		case "help":
		case "h":
			System.out.println("Commands:\t\t(Directions are north, south, east and west)");
			System.out.println("go <direction>\t\t- To move the player. (Or simply use 'n' for north 's' for south etc.)");
			System.out.println("inventory\t\t- Shows your inventory (Quick 'i')");
			System.out.println("finished\t\t- Show finished courses (Quick 'f')");
			System.out.println("unfinished\t\t- Show unfinished courses (Quick 'u')");
			System.out.println("pick up <item name>\t- Pick up an item.");
			System.out.println("use key with <direction>- Use key to unlock room in a direction.");
			System.out.println("talk to <name>\t\t- Talk to someone.");
			System.out.println("graduate\t\t- If the sphinx is in the same room you can try to graduate if you have completed atleast 180 credits of courses.");
			System.out.println("quit\t\t\t- Quit the game.");
			break;
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
		case "graduate":
			handleInputGraduate(player);
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
	
	private static void handleInputGraduate(Player player) {
		Creature creature = player.getCurrentLocation().getSphinx();
		if(creature != null) {
			Sphinx sphinx = (Sphinx)creature;
			if(sphinx.tryToGraduate(player)) {
				// TODO: Skriv ut diplom enligt uppgift!
				// TODO: Roll the end credits!
			}
		} else {
			System.out.println("There is no Sphinx here to graduate you.");
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
		if(talkingTo.getType() == CreatureType.SPHINX) {
			Sphinx sphinx = (Sphinx)talkingTo;
			sphinx.printUselessInfo();
		}
	}
}
