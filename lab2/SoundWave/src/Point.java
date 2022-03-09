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

	public static Integer[] types = {0, 1, 2};
	public int type;

	public Point() {
	    this.type = 0;
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
        if (this.type == 0) {
            nVel = nVel - (nNeighbor.pressure - pressure);
            eVel = eVel - (eNeighbor.pressure - pressure);
            wVel = wVel - (wNeighbor.pressure - pressure);
            sVel = sVel - (sNeighbor.pressure - pressure);
        }

	}

	public void updatePressure() {
		// TODO: pressure update
        if (this.type == 0) pressure = pressure - 0.5f * (nVel + eVel + wVel + sVel);
	}

	public float getPressure() {
		return pressure;
	}
}