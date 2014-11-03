package pollaxmud.entities;
import pollaxmud.enums.ItemType;

public class Key extends Item{
	
	public Key() {
		this.Weight = 1;
		this.Type = ItemType.KEY;
		this.Name = "Keycard";
	}
	
}
