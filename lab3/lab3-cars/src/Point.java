import java.util.Random;

public class Point {

    // TODO
    public static Integer[] types = {0, 1};
    private static Random r = new Random();
    private final static int PROBABILITY = 33;
    int type;
    Point next;
    boolean moved;
    int speed;
    int dist;


    public void move() {
        // TODO
        Point nei = this;
        for (int i = 0; i < speed; i++) {
            nei = nei.next;
        }


        if (this.type == 1 && !moved && nei.type == 0) {
            nei.type = 1;
            nei.moved = true;
            nei.speed = this.speed;
            this.type = 0;
            this.speed = 0;
        }
//        if (this.type == 1 && !moved && next.type == 0 ) {
//            this.type = 0;
//            next.type = 1;
//
//            this.moved = true;
//            next.moved = true;
//
//            this.speed = 0;
//            next.speed = this.speed;
//
////        }
//        }
    }

    public void clicked() {
        this.type = 1;
    }

    public void clear() {
        this.type = 0;
    }

    private int getDist() {
        Point nei = this;
        int dis = 0;
        int i = 0;
        while (i < this.speed && nei.next.type == 0) {
            dis++;
            nei = nei.next;
            i++;
        }
        return dis;
    }

    public void acceleration() {
        if (this.speed < 5) {
            this.speed++;
        }
    }
    public void slowDown() {
        if (getDist() < speed) {
            speed = getDist();
            ;
        }
    }

    public void randomize() {
        if (r.nextInt(100) < PROBABILITY) {
            speed--;
        }
    }
}

