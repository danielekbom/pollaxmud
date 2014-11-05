package pollaxmud.enums;

/**
 * An enum representing directions.
 * @author Daniel and Oscar
 *
 */
public enum Direction {
	NORTH, EAST, SOUTH, WEST;
	
	/**
	 * Returns a Direction in String format.
	 * @return The Direction in string format.
	 */
    public String toString(){
        switch(this){
        case NORTH :
            return "NORTH";
        case EAST :
            return "EAST";
        case SOUTH :
        	return "SOUTH";
        case WEST :
            return "WEST";
        }
        return null;
    }
    
    /**
     * Converts a given string to a Direction.
     * @param stringDirection String to convert.
     * @return The Direction that the given string matches. null if the string does not match a Direction.
     */
    public static Direction stringToDirection(String stringDirection){
    	stringDirection = stringDirection.toUpperCase();
    	switch(stringDirection){
    	case "NORTH" :
    		return Direction.NORTH;
    	case "EAST" :
    		return Direction.EAST;
    	case "SOUTH" :
    		return Direction.SOUTH;
    	case "WEST" :
    		return Direction.WEST;
    	}
    	return null;
    }
}
