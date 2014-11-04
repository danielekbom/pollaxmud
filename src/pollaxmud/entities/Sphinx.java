package pollaxmud.entities;

import pollaxmud.enums.CreatureType;

public class Sphinx extends Creature{

	public Sphinx () {
		this.Name = "Sphinx of Pollax";
		this.Type = CreatureType.SPHINX;
	}
	
	/**
	 * The Sphinx will give you some useless information.
	 */
	public void printUselessInfo() {
		System.out.println("PI is equal to 3.1415...");
	}
	
	/**
	 * Try to graduate and complete the game.
	 * @return True if you graduate else false.
	 */
	public boolean tryToGraduate(Player player) {
		System.out.println("So you wish to graduate!");
		if(player.getFinishedCredits() >= 180) {
			System.out.println("Congratulations! You have completed Pollax. Well done!");
			return true;
		}
		System.out.println("And what sort of degree are you expecting to get with " + Integer.toString(player.getFinishedCredits()) + " credits!\nGo back to school!");
		return false;
	}
}
