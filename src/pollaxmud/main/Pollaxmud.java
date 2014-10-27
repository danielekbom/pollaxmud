/**
 * @author      Daniel Ekbom, Oscar Sandén <address @ example.com>
 * @version     1.0
 * @since       2014-10-27
 */

package pollaxmud.main;

import java.util.Scanner;

import pollaxmud.entities.Player;
import pollaxmud.handlers.InputHandler;
import pollaxmud.utilities.WorldImporter;
import pollaxmud.world.World;

public class Pollaxmud {

	private static World StartingWorld;
	private static Player PlayerOne;
	
	public static void main(String[] args) {
		
		StartingWorld = WorldImporter.ImportWorld();
		
		PlayerOne = new Player(StartingWorld.getRoomAtIndex(0));
		PlayerOne.getCurrentLocation().printEntranceText();
		
		Scanner scanner = new Scanner(System.in);
		String inputString = "";
		while(!inputString.equalsIgnoreCase("quit")){
			inputString = scanner.next();
			InputHandler.handleInput(inputString, PlayerOne);
		}
		scanner.close();
		
		System.out.println("Exits game");
		
	}

}
