package pollaxmud.entities;

import pollaxmud.enums.CreatureType;

/**
 * A class representing a Sphinx, which inherits from the Creature class.
 * The Sphinx is used in the game as a creature who graduates the player at the end of the game.
 * A Sphinx has a Name and a CreatureType.
 * @author Daniel and Oscar
 *
 */
public class Sphinx extends Creature{

	/**
	 * The constructor of a Sphinx.
	 */
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
	 * If the player meets all the requirements for graduation, then he will be graduated.
	 * @param player The Player object who the player of the game is controlling. 
	 * @return True if the player successfully graduated, else false.
	 */
	public boolean tryToGraduate(Player player) {
		System.out.println("So you wish to graduate!");
		if(player.getFinishedCredits() >= 180 && player.getUnfinishedCourses().isEmpty()) {
			System.out.println("Congratulations! You have completed Pollax. Well done!");
			System.out.println("Here is your graduation certificate:");
			printGraduationCert(player);
			return true;
		}
		if(player.getFinishedCredits() < 180){
			System.out.println("And what sort of degree are you expecting to get with " + Integer.toString(player.getFinishedCredits()) + " credits!");
		}else{
			System.out.println("You need to complete all your unfinished courses before you can get your exam!");
		}
		System.out.println("Go back to school!");
		return false;
	}
	
	/**
	 * Prints the graduation certificate when the player graduates.
	 * @param player The Player object who the player of the game is controlling. 
	 */
	private void printGraduationCert(Player player){
		System.out.println("#######################################");
		System.out.println("####### Graduation Certificate ########");
		System.out.println("#######################################");
		for(Course course : player.getFinishedCourses()){
			System.out.println("# " + course.getName());
		}
		System.out.println("#######################################");
		System.out.println("############### Signature: The Sphinx #");
		System.out.println("#######################################");
	}
}
