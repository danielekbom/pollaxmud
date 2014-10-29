package pollaxmud.entities;

public class Teacher extends Creature {
	private String Course;
	
	public Teacher(String name, String course) {
		this.Name = name;
		this.Course = course;
		this.Type = CreatureType.TEACHER;
	}
}
