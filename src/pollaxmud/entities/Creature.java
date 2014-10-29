package pollaxmud.entities;

public class Creature {
	protected String Name;
	protected CreatureType Type;
	
	public String getName() {
		return Name;
	}
	
	public CreatureType getType() {
		return Type;
	}
	
	public enum CreatureType{
		TEACHER, SPHINX, PLAYER; // Player?
	}
}
