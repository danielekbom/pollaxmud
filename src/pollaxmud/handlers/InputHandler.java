package pollaxmud.handlers;

import java.awt.Canvas;

import pollaxmud.Enums.Direction;
import pollaxmud.entities.Item;
import pollaxmud.entities.Player;

public class InputHandler {

	public static void handleInput(String inputString, Player player, Canvas canvas){
		switch(inputString){
		case "go north":
		case "n":
			handleInputGoNorth(player, canvas);
			break;
		case "go east":
		case "e":
			handleInputGoEast(player, canvas);
			break;
		case "go south":
		case "s":
			handleInputGoSouth(player, canvas);
			break;
		case "go west":
		case "w":
			handleInputGoWest(player, canvas);
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
			}else{
				System.out.println("Unknown command!");
			}
		}
	}

	private static void handleInputGoNorth(Player player, Canvas canvas) {
		if(player.getCurrentLocation().getRoomInDirection(Direction.NORTH) != null){
			if(player.getCurrentLocation().getRoomInDirection(Direction.NORTH).getUnlocked()){
				player.walkNorth();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Door is locked!");
			}
		}else{
			System.out.println("Can not walk to the north!");
		}
	}
	
	private static void handleInputGoEast(Player player, Canvas canvas) {
		if(player.getCurrentLocation().getRoomInDirection(Direction.EAST) != null){
			if(player.getCurrentLocation().getRoomInDirection(Direction.EAST).getUnlocked()){
				player.walkEast();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Door is locked!");
			}
		}else{
			System.out.println("Can not walk to the east!");
		}
	}
	
	private static void handleInputGoSouth(Player player, Canvas canvas) {
		if(player.getCurrentLocation().getRoomInDirection(Direction.SOUTH) != null){
			if(player.getCurrentLocation().getRoomInDirection(Direction.SOUTH).getUnlocked()){
				player.walkSouth();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Door is locked!");
			}
		}else{
			System.out.println("Can not walk to the south!");
		}
	}
	
	private static void handleInputGoWest(Player player, Canvas canvas) {
		if(player.getCurrentLocation().getRoomInDirection(Direction.WEST) != null){
			if(player.getCurrentLocation().getRoomInDirection(Direction.WEST).getUnlocked()){
				player.walkWest();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Door is locked!");
			}
		}else{
			System.out.println("Can not walk to the west!");
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
}
