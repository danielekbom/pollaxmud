package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Teacher;

public class TeacherImporter {

	public static List<Teacher> ImportTeachers(){
		List<Teacher> teachers = new ArrayList<Teacher>();
		
		try{
			File file = new File("teachers.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			String[] data;
			Teacher newTeacher;
			while((line = bufferedReader.readLine()) != null){
				data = line.split(";");
				newTeacher = new Teacher(data[0], data[1]);
				teachers.add(newTeacher);
			}
			fileReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return teachers;
	}
}
