public class Point {

    // TODO
    public static Integer[] types = {0, 1};
    int type;
    Point next;
    boolean moved;
    int speed;

    public void move() {
        // TODO
        if (this.type == 1 && !moved && next.type == 0 ) {
            this.type = 0;
            next.type = 1;
            this.moved = true;
            next.moved = true;
        }
    }

    public void clicked() {
        this.type = 1;
    }

    public void clear() {
        this.type = 0;
    }
}

