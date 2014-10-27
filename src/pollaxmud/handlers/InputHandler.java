package pollaxmud.handlers;

import pollaxmud.entities.Player;

public class InputHandler {

	public static void handleInput(String inputString, Player player){
		if(inputString.equalsIgnoreCase("north")){
			if(player.getCurrentLocation().getRoomNorth() != null){
				player.walkNorth();
				player.getCurrentLocation().printEntranceText();
			}else{
				System.out.println("Can not walk to the north!");
			}
		}else if(inputString.equalsIgnoreCase("east")){
			if(player.getCurrentLocation().getRoomEast() != null){
				player.walkEast();
				player.getCurrentLocation().printEntranceText();
			}else{
				System.out.println("Can not walk to the east!");
			}
		}else if(inputString.equalsIgnoreCase("south")){
			if(player.getCurrentLocation().getRoomSouth() != null){
				player.walkSouth();
				player.getCurrentLocation().printEntranceText();
			}else{
				System.out.println("Can not walk to the south!");
			}
		}else if(inputString.equalsIgnoreCase("west")){
			if(player.getCurrentLocation().getRoomWest() != null){
				player.walkWest();
				player.getCurrentLocation().printEntranceText();
			}else{
				System.out.println("Can not walk to the west!");
			}
		}else{
			System.out.println("Unknown command!");
		}
	}
	
}
