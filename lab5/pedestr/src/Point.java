import java.util.ArrayList;
import java.util.Comparator;

public class Point {

    public ArrayList<Point> neighbors;
    public static Integer []types ={0,1,2,3};
    public int type;
    public int staticField;
    public boolean isPedestrian;
    public boolean blocked = false;

    public Point() {
        type = 0;
        staticField = 100000;
        neighbors= new ArrayList<Point>();
    }

    public void clear() {
        staticField = 100000;
    }

    public int getStaticField() {
        return staticField;
    }

    public boolean calcStaticField() {
        int fieldValue = 1 + neighbors.stream()
                .mapToInt(Point::getStaticField)
                .min()
                .orElse(10000);
        if ( staticField >  fieldValue) {
            staticField = fieldValue;
            return true;
        }
        return false;
    }

    public void move() {
        if (isPedestrian && !blocked) {
            Point smallest = neighbors.stream()
                    .filter(Point::isOccupied)
                    .min(Comparator.comparing(Point::getStaticField))
                    .orElse(null);
            if (smallest != null) {
                type = 0;
                isPedestrian = false;
                if (smallest.type == 0) {
                    smallest.type = 3;
                    smallest.isPedestrian = true;
                    smallest.blocked = true;

                }
            }
        }
    }

    public boolean isOccupied() {
        return this.type == 0 || this.type == 2;
    }

    public void addNeighbor(Point nei) {
        neighbors.add(nei);
    }
}