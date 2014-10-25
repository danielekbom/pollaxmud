package pollaxmud.handlers;

import pollaxmud.entities.Player;

public class InputHandler {

	public static void handleInput(String inputString, Player player){
		if(inputString.equalsIgnoreCase("north")){
			player.walkNorth();
			player.getCurrentLocation().printEntranceText();
		}
		if(inputString.equalsIgnoreCase("east")){
			player.walkEast();
			player.getCurrentLocation().printEntranceText();
		}
		if(inputString.equalsIgnoreCase("south")){
			player.walkSouth();
			player.getCurrentLocation().printEntranceText();
		}
		if(inputString.equalsIgnoreCase("west")){
			player.walkWest();
			player.getCurrentLocation().printEntranceText();
		}
	}
	
}
