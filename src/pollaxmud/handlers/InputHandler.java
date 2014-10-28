package pollaxmud.handlers;

import java.awt.Canvas;

import pollaxmud.entities.Item;
import pollaxmud.entities.Player;

public class InputHandler {

	public static void handleInput(String inputString, Player player, Canvas canvas){
		if(inputString.equalsIgnoreCase("go north")){
			if(player.getCurrentLocation().getRoomNorth() != null){
				player.walkNorth();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Can not walk to the north!");
			}
		}else if(inputString.equalsIgnoreCase("go east")){
			if(player.getCurrentLocation().getRoomEast() != null){
				player.walkEast();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Can not walk to the east!");
			}
		}else if(inputString.equalsIgnoreCase("go south")){
			if(player.getCurrentLocation().getRoomSouth() != null){
				player.walkSouth();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Can not walk to the south!");
			}
		}else if(inputString.equalsIgnoreCase("go west")){
			if(player.getCurrentLocation().getRoomWest() != null){
				player.walkWest();
				player.getCurrentLocation().printEntranceText();
				canvas.repaint();
			}else{
				System.out.println("Can not walk to the west!");
			}
		}else if(inputString.equalsIgnoreCase("inventory")){
			player.printBackpackInventory();
		}else if(inputString.startsWith("pick up")){
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
