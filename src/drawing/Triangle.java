package drawing;

import java.awt.*;

public class Triangle implements Shape {
    private int[] xPoints, yPoints;
    private Color color;
    private boolean isFilled;

    public Triangle(int[] xPoints, int[] yPoints, Color color, boolean isFilled) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
        this.isFilled = isFilled;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        if (isFilled) {
            g2d.fillPolygon(xPoints, yPoints, 3);
        } else {
            g2d.drawPolygon(xPoints, yPoints, 3);
        }
    }

    public void setPoints(int[] xPoints, int[] yPoints) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    public Color getColor() {
        return color;
    }

    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public int[] getXPoints() {
        return xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
    }

    public void setStart(int x, int y) {
        throw new UnsupportedOperationException("setStart operation is not supported by Triangle");
    }

    public void setEnd(int x, int y) {
        throw new UnsupportedOperationException("setEnd operation is not supported by Triangle");
    }

    public void setRadius(int radius) {
        throw new UnsupportedOperationException("setRadius operation is not supported by Triangle");
    }

    public int getRadius() {
        throw new UnsupportedOperationException("getRadius operation is not supported by Triangle");
    }

    public int getCenterX() {
        throw new UnsupportedOperationException("getCenterX operation is not supported by Triangle");
    }

    public int getCenterY() {
        throw new UnsupportedOperationException("getCenterY operation is not supported by Triangle");
    }

    public void setColor(Color color) {
        this.color = color;
    }
}