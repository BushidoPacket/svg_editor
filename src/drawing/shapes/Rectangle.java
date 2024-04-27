package drawing.shapes;

import drawing.Shape;

import java.awt.*;

public class Rectangle implements Shape {
    private int startX, startY, endX, endY;
    private Color color;
    private boolean isFilled;

    public Rectangle(int startX, int startY, int endX, int endY, Color color, boolean isFilled) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
        this.isFilled = isFilled;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);
        int width = Math.abs(startX - endX);
        int height = Math.abs(startY - endY);

        g2d.setColor(color);
        if (isFilled) {
            g2d.fillRect(x, y, width, height);
        } else {
            g2d.drawRect(x, y, width, height);
        }
    }

    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public boolean isFilled() {
        return isFilled;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setStart(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    public void setEnd(int x, int y) {
        this.endX = x;
        this.endY = y;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setRadius(int radius) {
        throw new UnsupportedOperationException("setRadius operation is not supported by Rectangle");
    }

    public int getRadius() {
        throw new UnsupportedOperationException("getRadius operation is not supported by Rectangle");
    }

    public int getCenterX() {
        throw new UnsupportedOperationException("getCenterX operation is not supported by Rectangle");
    }

    public int getCenterY() {
        throw new UnsupportedOperationException("getCenterY operation is not supported by Rectangle");
    }

    public void setPoints(int[] xPoints, int[] yPoints) {
        throw new UnsupportedOperationException("setPoints operation is not supported by Rectangle");
    }

    public int[] getXPoints() {
        throw new UnsupportedOperationException("getXPoints operation is not supported by Rectangle");
    }

    public int[] getYPoints() {
        throw new UnsupportedOperationException("getYPoints operation is not supported by Rectangle");
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }
}