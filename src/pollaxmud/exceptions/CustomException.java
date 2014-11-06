package pollaxmud.exceptions;

/**
 * A class used to create custom exceptions used in the game.
 * @author Daniel and Oscar
 *
 */
public class CustomException extends Exception{

	private String ErrorMessage = "";
	private String ErrorName = "CustomException";
	
	public CustomException(){
		
	}
	
	public CustomException(String message){
		ErrorMessage = message;
	}
	
	public CustomException(String message, String name){
		ErrorMessage = message;
		ErrorName = name;
	}
	
	@Override
	public String getMessage(){
		return ErrorMessage;
	}
	
	public void printMessage(){
		System.err.print(ErrorName + ":\n\t" + ErrorMessage + "\n");
	}
	
}
