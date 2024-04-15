// Triangle.java
package drawing;

import java.awt.*;

public class Triangle {
    private int[] xPoints;
    private int[] yPoints;

    public Triangle(int[] xPoints, int[] yPoints) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    public void setPoints(int[] xPoints, int[] yPoints) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    public int[] getXPoints() {
        return xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
}
