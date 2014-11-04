package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import pollaxmud.enums.Direction;
import pollaxmud.world.Room;
import pollaxmud.world.World;

public class WorldImporter {

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
		}
		
		return importedWorld;
	}
	
}
