import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

public class Board extends JComponent implements MouseInputListener, ComponentListener {
    private static final long serialVersionUID = 1L;
    private Point[][] points;
    private int size = 25;
    public int editType = 0;

    public Board(int length, int height) {
        addMouseListener(this);
        addComponentListener(this);
        addMouseMotionListener(this);
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    private void initialize(int length, int height) {
        points = new Point[length][height];

        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y] = new Point();

                if (y < 2 || y >= points[x].length - 2) {
                    points[x][y].type = 5;
                }
            }
        }

        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y].next = points[(x + 1) % points.length][y];
                points[x][y].prev = points[(points.length + x - 1) % points.length][y];
            }
        }
    }


    private void swap(Point p1, Point p2) {
        Point pTemp = new Point();
        pTemp.morphInto(p2);
        p2.morphInto(p1);
        p1.morphInto(pTemp);
    }


    public void iteration() {

        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y].moved = false;
            }
        }

        for (int x = 0; x < points.length; ++x) {
            for (int y = 0; y < points[x].length; ++y) {
                boolean overtake = false;

                if (y == 2) { // left Line
                    if (!points[x][y + 1].isCar()) {
                        if (points[x][y].returnPossible(points[x][y + 1])) {
                            swap(points[x][y], points[x][y + 1]);
                            points[x][y + 1].moved = true;
                            overtake = true;
                        }
                    }
                } else if (y == 3) { //right line
                    if (!points[x][y - 1].isCar()) {
                        if (points[x][y].overtakePossible(points[x][y - 1])) {
                            swap(points[x][y], points[x][y - 1]);
                            points[x][y - 1].moved = true;
                            overtake = true;
                        }
                    }
                }

                if (!overtake) {
                    points[x][y].slowDown();
                    points[x][y].acceleration();
                    points[x][y].move();
                }
            }
        }
        this.repaint();
    }


    public void clear() {
        for (int x = 0; x < points.length; ++x)
            for (int y = 0; y < points[x].length; ++y) {
                points[x][y].clear();
            }
        this.repaint();
    }


    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        g.setColor(Color.GRAY);
        drawNetting(g, size);
    }

    private void drawNetting(Graphics g, int gridSpace) {
        Insets insets = getInsets();
        int firstX = insets.left;
        int firstY = insets.top;
        int lastX = this.getWidth() - insets.right;
        int lastY = this.getHeight() - insets.bottom;

        int x = firstX;
        while (x < lastX) {
            g.drawLine(x, firstY, x, lastY);
            x += gridSpace;
        }

        int y = firstY;
        while (y < lastY) {
            g.drawLine(firstX, y, lastX, y);
            y += gridSpace;
        }

        for (x = 0; x < points.length; ++x) {
            for (y = 0; y < points[x].length; ++y) {
                float a = 1.0F;

                // TODO
                //g.setColor(new Color(R, G, B, 0.7f));
                if (points[x][y].type == 1) {
                    g.setColor(new Color(246, 210, 37));
                } else if (points[x][y].type == 5) {
                    g.setColor(new Color(100,200, 40));
                } else if (points[x][y].type == 2) {
                    g.setColor(new Color(26, 94, 217));
                } else if (points[x][y].type == 3) {
                    g.setColor(new Color(224, 46, 46));
                } else {
                    g.setColor(new Color(255,255, 255));
                }

                g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
            }
        }

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
            if(editType==0){
                points[x][y].clicked();
            }
            else {
                points[x][y].type= editType;
            } this.repaint();
        }
    }

    public void componentResized(ComponentEvent e) {
        int dlugosc = (this.getWidth() / size) + 1;
        int wysokosc = (this.getHeight() / size) + 1;
        initialize(dlugosc, wysokosc);
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX() / size;
        int y = e.getY() / size;
        if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
            if(editType==0){
                points[x][y].clicked();
            }
            else {
                points[x][y].type= editType;
            } this.repaint();
        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

}
