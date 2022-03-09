public class Point {

	public Point nNeighbor;
	public Point wNeighbor;
	public Point eNeighbor;
	public Point sNeighbor;
	public float nVel;
	public float eVel;
	public float wVel;
	public float sVel;
	public float pressure;

	public Point() {
		clear();
	}

	public void clicked() {
		pressure = 1;
	}
	
	public void clear() {
		// TODO: clear velocity and pressure
        nVel = 0;
        eVel = 0;
        wVel = 0;
        sVel = 0;
        pressure = 0;
	}

	public void updateVelocity() {
		// TODO: velocity update
        nVel = nVel - (nNeighbor.pressure - pressure);
        eVel = eVel - (eNeighbor.pressure - pressure);
        wVel = wVel - (wNeighbor.pressure - pressure);
        sVel = sVel - (sNeighbor.pressure - pressure);

	}

	public void updatePressure() {
		// TODO: pressure update
        pressure = pressure - 0.5f * (nVel + eVel + wVel + sVel);
	}

	public float getPressure() {
		return pressure;
	}
}