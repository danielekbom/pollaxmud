package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import pollaxmud.enums.Direction;
import pollaxmud.world.Room;
import pollaxmud.world.World;

/**
 * Imports Rooms from world.txt and adds them to a World.
 * The format of the file is one Room on each line.
 * The format of a line is "RoomName;North RoomName;East RoomName;South RoomName;West RoomName;X-coordinate;Y-coordinate".
 * The x and y coordinates are used to draw the player on the map canvas when its walking around in the world.
 * The text file needs to be placed in the project root.
 * @author Daniel and Oscar
 */
public class WorldImporter {

	/**
	 * Imports the Rooms and adds them to a world.
	 * @return A world with the imported Rooms.
	 */
	public static World ImportWorld(){
		World importedWorld = new World();
		
		try{
			File file = new File("world.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] data;
			Room roomToAdd;
			Random random = new Random();
			boolean unlocked;
			while((line = bufferedReader.readLine()) != null){
				data = line.split(";");
				unlocked = random.nextBoolean();
				if(unlocked == false && (data[0].startsWith("Entrance") || data[0].startsWith("Hallway") || data[0].startsWith("Skybridge"))){
					unlocked = true;
				}
				roomToAdd = new Room(data[0],unlocked,Integer.parseInt(data[5]),Integer.parseInt(data[6]));
				importedWorld.addRoom(roomToAdd);
			}
			file = new File("world.txt");
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null){
				data = line.split(";");
				roomToAdd = importedWorld.getRoomByName(data[0]);
				if(roomToAdd != null){
					if(!data[1].equals("X"))roomToAdd.setRoomInDirection(importedWorld.getRoomByName(data[1]), Direction.NORTH);
					if(!data[2].equals("X"))roomToAdd.setRoomInDirection(importedWorld.getRoomByName(data[2]), Direction.EAST);
					if(!data[3].equals("X"))roomToAdd.setRoomInDirection(importedWorld.getRoomByName(data[3]), Direction.SOUTH);
					if(!data[4].equals("X"))roomToAdd.setRoomInDirection(importedWorld.getRoomByName(data[4]), Direction.WEST);
				}
			}
			fileReader.close();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			System.err.println(e.getMessage() + ":\n\tError while impoting the world! You can not play like this.\n"
					+ "Your world.txt may be corrupted.");
		}
		
		return importedWorld;
	}
	
}
