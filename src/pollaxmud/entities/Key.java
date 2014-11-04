package pollaxmud.entities;
import pollaxmud.enums.ItemType;

/**
 * A class which inherits from Item and represents a Key.
 * @author Daniel and Oscar
 *
 */
public class Key extends Item{
	
	/**
	 * The constructor of a key.
	 */
	public Key() {
		this.Weight = 1;
		this.Type = ItemType.KEY;
		this.Name = "Keycard";
	}
	
}
