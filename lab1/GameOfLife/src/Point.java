import java.util.ArrayList;

public class Point {
	public ArrayList<Point> neighbors; //private
	private int currentState;
	private int nextState;
	private int numStates = 6;
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%(numStates + 1);
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState(RuleSet ruleSet) {
		//TODO: insert logic which updates according to currentState and 
		//number of active neighbors
        int activeNeighbors = countNeighbors();
        if (currentState == 1 && ruleSet.getRuleForSurvival().contains(activeNeighbors)) {
        	nextState = 1;
        } else if (currentState == 0 && ruleSet.getRuleForBirth().contains(activeNeighbors)) {
        	nextState = 1;
		} else {
			nextState = 0;
		}

	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
    private int countNeighbors() {
	    return (int) neighbors.stream()
                .filter(neighbor -> neighbor.currentState == 1)
                .count();
    }
}
