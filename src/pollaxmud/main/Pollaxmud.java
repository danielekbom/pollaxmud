package pollaxmud.main;

import pollaxmud.utilities.WorldImporter;
import pollaxmud.world.Room;
import pollaxmud.world.World;

public class Pollaxmud {

	private static World StartingWorld;
	
	public static void main(String[] args) {
		
		StartingWorld = WorldImporter.ImportWorld();
		Room testRoom = new Room("Daniels sovrum");
		StartingWorld.addRoom(testRoom);
		System.out.println(StartingWorld.getFirstRoom().getName());
	}

}
