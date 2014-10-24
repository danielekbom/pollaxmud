package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
				roomToAdd = new Room(data[0],Boolean.parseBoolean(data[5]));
				importedWorld.addRoom(roomToAdd);
			}
			fileReader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return importedWorld;
	}
	
}
