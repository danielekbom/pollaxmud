package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
					if(!data[1].equals("X"))roomToAdd.setRoomNorth(importedWorld.getRoomByName(data[1]));
					if(!data[2].equals("X"))roomToAdd.setRoomEast(importedWorld.getRoomByName(data[2]));
					if(!data[3].equals("X"))roomToAdd.setRoomSouth(importedWorld.getRoomByName(data[3]));
					if(!data[4].equals("X"))roomToAdd.setRoomWest(importedWorld.getRoomByName(data[4]));
				}
			}
			fileReader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return importedWorld;
	}
	
}
