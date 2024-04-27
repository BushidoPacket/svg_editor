package drawing;

import java.awt.*;

public class Circle implements Shape {
    private int centerX, centerY, radius;
    private Color color;
    private boolean isFilled;


    public Circle(int centerX, int centerY, int radius, Color color, boolean isFilled) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;
        this.isFilled = isFilled;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        int diameter = radius * 2;
        if (isFilled) {
            g2d.fillOval(centerX - radius, centerY - radius, diameter, diameter);
        } else {
            g2d.drawOval(centerX - radius, centerY - radius, diameter, diameter);
        }
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

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setStart(int x, int y) {
        throw new UnsupportedOperationException("setStart operation is not supported by Circle");
    }

    public void setEnd(int x, int y) {
        throw new UnsupportedOperationException("setEnd operation is not supported by Circle");
    }

    public void setPoints(int[] xPoints, int[] yPoints) {
        throw new UnsupportedOperationException("setPoints operation is not supported by Circle");
    }

    public int[] getXPoints() {
        throw new UnsupportedOperationException("getXPoints operation is not supported by Circle");
    }

    public int[] getYPoints() {
        throw new UnsupportedOperationException("getYPoints operation is not supported by Circle");
    }

    public void setColor(Color color) {
        this.color = color;
    }
}