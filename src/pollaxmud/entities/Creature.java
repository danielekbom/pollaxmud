package pollaxmud.entities;
import pollaxmud.enums.CreatureType;

/**
 * The creature class. For all types of creatures.
 * A Creature consists of a Name and a CreatureType.
 * @author Oscar and Daniel
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
	 * Get the Type of the creature.
	 * @return The Type of the creature.
	 */
	public CreatureType getType() {
		return Type;
	}
}
