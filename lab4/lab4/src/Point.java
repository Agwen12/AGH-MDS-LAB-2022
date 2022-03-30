import java.util.Random;

public class Point {

    // TODO
    public static Integer[] types = {0, 1, 2, 3, 4, 5};
    private static Random r = new Random();
    private final static int PROBABILITY = 33; //%
    int type;
    Point next;
    Point prev;
    boolean moved;
    int speed;

    public Point() {
        init();
    }

    public void morphInto(Point point) {
        type = point.type;
        moved = point.moved;
        speed = point.speed;
    }

    public static int getMaxSpeed(int type) {
        if (type == 1) {
            return 3;
        } else if (type == 2){
            return 5;
        } else if (type == 3) {
            return 7;
        }
        return 0;
    }

    public boolean isCar() {
        return type == 1 || type == 2 || type == 3;
    }

    private void init() {
        if (this.isCar()) speed = getMaxSpeed(type);
    }


    public void move() {

        Point nei = this;
        for (int i = 0; i < speed; i++) {
            nei = nei.next;
        }


        if (isCar() && !moved && nei.type == 0) {
            nei.type = this.type;
            nei.moved = true;
            nei.speed = this.speed;
            this.type = 0;
            this.speed = 0;
        }
    }

    public void clicked() {
        this.type = 0;
    }

    public void clear() {
        this.type = 0;
    }

//    public int getDist() {
//        Point nei = this;
//        int dis = 0;
//        int i = 0;
//        while (i < this.speed && nei.next.type == 0) {
//            dis++;
//            nei = nei.next;
//            i++;
//        }
//        return dis;
//    }

    public void acceleration() {
        if (this.speed < getMaxSpeed(type)) {
            this.speed++;
        }
    }
    public void slowDown() {
        Nei nextNei = Nei.getFirstAhead(this);
        if (nextNei.distance <= speed) {
            speed = nextNei.distance - 1;

        }
    }

    public void randomize() {
        if (r.nextInt(100) < PROBABILITY) {
            speed--;
        }
    }

    public boolean overtakePossible(Point leftPoint) {
        Nei prevNeiLeft = Nei.getFirstBehind(leftPoint);
        Nei nextNeiLeft = Nei.getFirstAhead(leftPoint);
        Nei prevNeiRight = Nei.getFirstBehind(this);

        return (speed < getMaxSpeed(type) &&
        prevNeiRight.distance >= Point.getMaxSpeed(prevNeiRight.point.type) &&
        prevNeiLeft.distance >= Point.getMaxSpeed(prevNeiLeft.point.type) &&
        nextNeiLeft.distance >= speed);
    }

    public boolean returnPossible(Point rightPoint) {
        Nei prevNeiLeft = Nei.getFirstBehind(this);
        Nei nextNeiRight = Nei.getFirstAhead(rightPoint);
        Nei prevNeiRight = Nei.getFirstBehind(rightPoint);

        return (prevNeiRight.distance >= prevNeiRight.point.speed &&
                prevNeiLeft.distance <= Point.getMaxSpeed(prevNeiLeft.point.type) &&
                nextNeiRight.distance >= speed);
    }


}

