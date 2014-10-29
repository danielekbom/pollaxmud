package pollaxmud.handlers;

import java.awt.Canvas;

import pollaxmud.entities.Item;
import pollaxmud.entities.Player;

public class InputHandler {

	public static void handleInput(String inputString, Player player, Canvas canvas){
		switch(inputString){
		case "go north":
		case "n":
			if(player.getCurrentLocation().getRoomNorth() != null){
				player.walkNorth();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Can not walk to the north!");
			}
			break;
		case "go east":
		case "e":
			if(player.getCurrentLocation().getRoomEast() != null){
				player.walkEast();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Can not walk to the east!");
			}
			break;
		case "go south":
		case "s":
			if(player.getCurrentLocation().getRoomSouth() != null){
				player.walkSouth();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Can not walk to the south!");
			}
			break;
		case "go west":
		case "w":
			if(player.getCurrentLocation().getRoomWest() != null){
				player.walkWest();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Can not walk to the west!");
			}
			break;
		case "inventory":
		case "i":
			player.printBackpackInventory();
			break;
		default:
			if(inputString.startsWith("pick up")){
				String itemName = inputString.substring(8);
				Item itemToPickUp = player.getCurrentLocation().getItemByName(itemName);
				if(itemToPickUp != null){
					player.addItemToBackpack(itemToPickUp);
					player.getCurrentLocation().deleteItemByName(itemName);
					System.out.println("You picked up: " + itemName);
				}else{
					System.out.println("Item does not exists!");
				}
			}else{
				System.out.println("Unknown command!");
			}
		}
	}
}
