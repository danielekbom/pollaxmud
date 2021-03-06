package pollaxmud.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pollaxmud.entities.Course;
import pollaxmud.entities.Teacher;
import pollaxmud.exceptions.CustomException;

/**
 * Will import Teachers from "teachers.txt" and if the courses
 * they teach in exists they will be added to a List of teachers
 * that will be returned.
 * The format of the file is one Teacher on each line.
 * The format of a line is "TeacherName;CourseName".
 * The text file needs to be placed in the project root.
 * @author Daniel and Oscar
 */
public class TeacherImporter {


	/**
	 * Imports the Teachers.
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
				if(data.length != 2){
					throw new CustomException("Import file teachers.txt has invalid format!","CorruptedImportFileException");
				}
				if(courseExists(data[1], courses)) {
					Course teachersCourse = getCourse(data[1], courses);
					newTeacher = new Teacher(data[0], teachersCourse);
					teachers.add(newTeacher);
				} else {
					System.out.println("No course named" + data [1] + ". Not adding teacher " + data[0]);
				}
			}
			bufferedReader.close();
			fileReader.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(CustomException e){
			e.printMessage();
		}catch(Exception e){
			System.err.println(e.getMessage() + ":\n\tError while impoting Teachers! You can not play like this.\n"
					+ "Your teachers.txt may be corrupted.");
		}
		teachers = addMissingTeachers(teachers, courses);
		return teachers;
	}
	
	/**
	 * Will get a course out of a list or courses by its name.
	 * @param courseName The name of the course wanted.
	 * @param courses The list of courses.
	 * @return The course returned.
	 */
	private static Course getCourse(String courseName, List<Course> courses) {
		for(Course course : courses) {
			if(course.getName().equals(courseName)) {
				return course;
			}
		}
		return null;
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
				if(course.getName().equals(teacher.getCourseName())) {
					found = true;
					continue;
				}				
			}
			if(!found) {
				Teacher newTeacher = new Teacher(course.getName(), course);
				newTeachersList.add(newTeacher);
			}
			found = false;
		}
		teachers.addAll(newTeachersList);
		return teachers;
	}
}
