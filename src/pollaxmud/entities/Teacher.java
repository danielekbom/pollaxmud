package pollaxmud.entities;

import pollaxmud.Enums.CreatureType;

public class Teacher extends Creature {
	private String Course;
	
	/**
	 * Constructor for a Teacher.
	 * @param name The name of the teacher
	 * @param course The course the teacher is teaching.
	 */
	public Teacher(String name, String course) {
		this.Name = name;
		this.Course = course;
		this.Type = CreatureType.TEACHER;
	}
	
	/**
	 * Get the course the teacher is teaching in.
	 * @return The course name as a String
	 */
	public String getCourse() {
		return Course;
	}
}
