package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Course;
import pollaxmud.entities.Teacher;

public class TeacherImporter {

	/**
	 * Will import Teachers from "teachers.txt" and if the courses
	 * they teach in exists they will be added to a List of teachers
	 * that will be returned.
	 * @param courses The list of courses for the game.
	 * @return A list of the teachers to add in game.
	 */
	public static List<Teacher> ImportTeachers(List<Course> courses){
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
				if(courseExists(data[1], courses)) {
					newTeacher = new Teacher(data[0], data[1]);
					teachers.add(newTeacher);
				} else {
					System.out.println("No course named" + data [1] + ". Not adding teacher " + data[0]);
				}
			}
			fileReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		if(teachers.size() < courses.size()) {
			teachers = addMissingTeachers(teachers, courses);
		} 
		if (teachers.size() > courses.size()) {
			// This should never happen. 
			AssertionError.class.getClass(); // Crash?
		}
		return teachers;
	}
	
	/**
	 * Looks for a Course in a Course List and return true if it exists.
	 * @param courseName The name of the Course you are looking for as a String
	 * @param courses The List of Courses you want to look in.
	 * @return True if the Course exists. Else False.
	 */
	private static boolean courseExists(String courseName, List<Course> courses) {
		for(Course course : courses) {
			if(course.getName().equals(courseName)) {
				return true;
			}
		}
		return false;
	}
	
/**
 * Takes a list of teachers and a list of courses. If there are courses without
 * teachers they get a new teacher assigned to them.
 * @param teachers List of teachers.
 * @param courses List of courses.
 * @return A list of teachers with the new teachers added to the old ones.
 */
	private static List<Teacher> addMissingTeachers(List<Teacher> teachers, List<Course> courses) {
		boolean found = false;
		List<Teacher> newTeachersList = new ArrayList<Teacher>();
		
		for(Course course : courses) {
			for(Teacher teacher : teachers) {
				if(course.getName().equals(teacher.getCourse())) {
					found = true;
					continue;
				}				
			}
			if(!found) {
				Teacher newTeacher = new Teacher(course.getName() + " teacher.", course.getName());
				newTeachersList.add(newTeacher);
			}
			found = false;
		}
		teachers.addAll(newTeachersList); // Merging the lists?
		return teachers;
	}
}
