package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
			while((line = bufferedReader.readLine()) != null){
				data = line.split(";");
				roomToAdd = new Room(data[0],Boolean.parseBoolean(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7]));
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
