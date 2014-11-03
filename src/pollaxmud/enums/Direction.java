package pollaxmud.enums;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;
	
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
