// Circle.java
package drawing;

import java.awt.*;

public class Circle {
    private int centerX, centerY, radius;

    public Circle(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        int diameter = radius * 2;
        g2d.fillOval(centerX - radius, centerY - radius, diameter, diameter);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }
}
