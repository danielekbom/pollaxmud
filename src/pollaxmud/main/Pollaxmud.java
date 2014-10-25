package pollaxmud.main;

import pollaxmud.entities.Player;
import pollaxmud.utilities.WorldImporter;
import pollaxmud.world.World;

public class Pollaxmud {

	private static World StartingWorld;
	private static Player PlayerOne;
	
	public static void main(String[] args) {
		
		StartingWorld = WorldImporter.ImportWorld();
		
		PlayerOne = new Player(StartingWorld.getRoomAtIndex(0));
		PlayerOne.getCurrentLocation().printEntranceText();
	}

}
