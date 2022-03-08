import java.util.ArrayList;
import java.util.Arrays;

public class Point {
	private ArrayList<Point> neighbors;
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

	public void calculateNewState() {
		//TODO: insert logic which updates according to currentState and 
		//number of active neighbors
        int activeNeighbors = countNeighbors();
//		System.out.println(activeNeighbors);
        if (getState() == 1) {
            if (activeNeighbors == 3 || activeNeighbors == 2) {
                nextState = 1;
            } else {
                nextState = 6;
            }
        } else {
            if (activeNeighbors == 3) {
                nextState = 1;
            } else {
                nextState = 6;
            }
        }
//		System.out.println(nextState);
//		System.out.println("\n");
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point
    private int countNeighbors() {
		if ((int) neighbors.stream()
				.filter(neighbor -> neighbor.getState() == 1)
				.count() != 0) System.out.println(Arrays.toString(neighbors.stream().mapToInt(Point::getState).toArray()));
	    return (int) neighbors.stream()
                .filter(neighbor -> neighbor.getState() == 1)
                .count();
    }
}
