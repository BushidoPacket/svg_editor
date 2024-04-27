package drawing;

import java.awt.*;

public interface Shape {
    void draw(Graphics2D g2d);
    void setStart(int x, int y);
    void setEnd(int x, int y);
    void setRadius(int radius);
    void setPoints(int[] xPoints, int[] yPoints);
    int getCenterX();
    int getCenterY();
    int getRadius();
    int[] getXPoints();
    int[] getYPoints();
    void setFilled(boolean isFilled);
    boolean isFilled();
    Color getColor();
    void setColor(Color color);
}