package pollaxmud.entities;
import pollaxmud.enums.CreatureType;

/**
 * The creature class. For all types of creatures.
 * @author oscar
 *
 */
public class Creature {
	protected String Name;
	protected CreatureType Type;
	
	/**
	 * Get the name of a creature.
	 * @return The name of the creature as a String
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * Get the type of the creature (Teacher, sphinx, etc).
	 * @return The type of the creature as a String.
	 */
	public CreatureType getType() {
		return Type;
	}
}
