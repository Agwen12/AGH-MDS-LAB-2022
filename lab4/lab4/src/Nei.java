public class Nei {
    int distance;
    Point point;

    public Nei(Point point, int distance) {
        this.distance = distance;
        this.point = point;
    }

    public static Nei getFirstAhead(Point point) {
        Point next = point;
        int distance = 0;

        while (distance <= Point.getMaxSpeed(3)) {
            distance++;
            next = next.next;
            if (next.isCar()) {
                break;
            }
        }
        return new Nei(next, distance);
    }

    public static Nei getFirstBehind(Point point) {
        Point prev = point;
        int distance = 0;

        while (distance <= Point.getMaxSpeed(3)) {
            distance ++;
            prev = prev.prev;
            if (prev.isCar()) {
                break;
            }
        }
        return new Nei(prev, distance);
    }
}
