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
			}else{
				System.out.println("Unknown command!");
			}
		}
	}
	
	private static void handleInputGo(Player player, Canvas canvas, Direction direction) {
		if(player.getCurrentLocation().getRoomInDirection(direction) != null){
			if(player.getCurrentLocation().getRoomInDirection(direction).getUnlocked()){
				player.walk(direction);
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Door is locked!");
			}
		}else{
			System.out.println("Can not walk to that direction!");
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
