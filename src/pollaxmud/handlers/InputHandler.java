package pollaxmud.handlers;

public class InputHandler {

	public static void handleInput(String inputString){
		if(inputString.equalsIgnoreCase("north")){
			System.out.println("walk to the north");
		}
		if(inputString.equalsIgnoreCase("east")){
			System.out.println("walk to the south");
		}
	}
	
}
