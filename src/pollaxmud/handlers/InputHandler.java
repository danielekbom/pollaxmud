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
import pollaxmud.main.Pollaxmud;
import pollaxmud.world.Room;

/**
 * Class for handling user input.
 * @author Daniel and Oscar
 *
 */
public class InputHandler {

	/**
	 * Chooses the correct action depending on the user input.
	 * Prints "Unknown command!" if the input is not a valid command.
	 * @param inputString The user input.
	 * @param player The Player that the player who plays the game is controlling.
	 * @param canvas The canvas where that map is drawn.
	 */
	public static void handleInput(String inputString, Player player, Canvas canvas){
		switch(inputString){
		case "help":
		case "h":
			handleInputHelp();
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
			}else if(inputString.startsWith("drop")){
				handleInputDrop(player, inputString);
			}else if(inputString.startsWith("use key with")){
				handleInputUseKey(player, inputString);
			}else if(inputString.startsWith("talk to")){
				handleInputTalkTo(player, inputString);
			}else{
				System.out.println("Unknown command!");
			}
		}
	}

	/**
	 * Prints the help text that shows all the existing commands.
	 */
	private static void handleInputHelp() {
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
	}

	/**
	 * Moving the player in a given direction.
	 * The canvas is also repainted.
	 * @param player The player to move.
	 * @param canvas The canvas to repaint.
	 * @param direction The direction to move the player in.
	 */
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
	
	/**
	 * Trying to graduate the player using the Sphinx class.
	 * @param player The player to graduate.
	 */
	private static void handleInputGraduate(Player player) {
		Creature creature = player.getCurrentLocation().getSphinx();
		if(creature != null) {
			Sphinx sphinx = (Sphinx)creature;
			if(sphinx.tryToGraduate(player)) {
				Pollaxmud.setGameFinished(true);
				System.out.println("Press enter to exit game!");
				Pollaxmud.scanner.nextLine();
			}
		} else {
			System.out.println("There is no Sphinx here to graduate you.");
		}
	}
	
	/**
	 * Prints a list of the player's finished courses.
	 * @param player The player to print courses for.
	 */
	private static void handleInputFinished(Player player){
		System.out.println("Finished courses: ");
		player.printCourses(true);
	}
	
	/**
	 * Prints a list of the player's unfinished courses.
	 * @param player The player to print courses for.
	 */
	private static void handleInputUnfinished(Player player){
		System.out.println("Unfinished courses: ");
		player.printCourses(false);
	}
	
	/**
	 * Method that moves an item with a given name from the player's current location to the player's backpack.
	 * @param player The player that the user is controlling.
	 * @param inputString The input that contains the name of the item to move to the backpack.
	 */
	private static void handleInputPickUp(Player player, String inputString) {
		if(inputString.length() <= 8){
			System.out.println("No item specified!");
			return;
		}
		String itemName = inputString.substring(8);
		Item itemToPickUp = player.getCurrentLocation().getItemByName(itemName);
		if(itemToPickUp != null){
			if(player.addItemToBackpack(itemToPickUp)){
				player.getCurrentLocation().deleteItemByName(itemName);
				System.out.println("You picked up: " + itemName);
			}
		}else{
			System.out.println("Item does not exists!");
		}
	}
	
	/**
	 * Method that moves an item with a given name from the player's backpack to the player's current location.
	 * @param player The player that the user is controlling.
	 * @param inputString The input that contains the name of the item to move to the current location.
	 */
	private static void handleInputDrop(Player player, String inputString) {
		if(inputString.length() <= 5){
			System.out.println("No item specified!");
			return;
		}
		String itemName = inputString.substring(5);
		if(player.dropItem(itemName)){
			System.out.println("You droped \"" + itemName + "\".");
		}else{
			System.out.println("You dont have any item with that name!");
		}
	}
	
	/**
	 * Unlocks a locked room(if it exists) in the direction given in the inputString.
	 * Unlocks only if the player have a key in its backpack.
	 * @param player The player which is trying to unlock a room.
	 * @param inputString The input from the user.
	 */
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
			player.removeItemFromBackpack("Keycard");
			System.out.println("Door unlocked!");
		}else{
			System.out.println("No locked door in that direction!");
		}
	}
	
	/**
	 * Method that starts a conversation with a creature with a name given in the inputString, if such creature exists.
	 * @param player The player that the user is controlling.
	 * @param inputString The user input string.
	 */
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
