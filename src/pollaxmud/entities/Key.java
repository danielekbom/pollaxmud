package pollaxmud.entities;
import pollaxmud.Enums.ItemType;

public class Key extends Item{
	
	public Key() {
		this.Weight = 1;
		this.Type = ItemType.KEY;
		this.Name = "Keycard";
	}
	
}
