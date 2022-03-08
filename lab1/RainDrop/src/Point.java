import java.util.ArrayList;
import java.util.Random;

public class Point {
	public ArrayList<Point> neighbors; //private
	private int currentState;
	private int nextState;
	private int numStates = 6;
	private final static int DROP_RATE = 3;
	
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
		if (currentState > 0) {
			nextState = currentState - 1;
		}
		else if (!neighbors.isEmpty() && neighbors.get(0).getState() > 0) {
			nextState = 6;
		}
	}

	public void drop() {
		Random random = new Random();
		if (random.nextInt(101) < DROP_RATE) {
			nextState = 6;
		} else if (currentState > 0) {
			nextState = currentState - 1;
		}
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}

}
