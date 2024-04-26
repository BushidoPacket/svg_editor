package drawing;

import java.awt.*;

public class Line implements Shape {
    private int startX, startY, endX, endY;
    private Color color;

    public Line(int startX, int startY, int endX, int endY, Color color) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.drawLine(startX, startY, endX, endY);
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

    @Override
    public void setRadius(int radius) {
        throw new UnsupportedOperationException("setRadius operation is not supported by Line");
    }

    @Override
    public void setPoints(int[] xPoints, int[] yPoints) {
        throw new UnsupportedOperationException("setPoints operation is not supported by Line");
    }

    @Override
    public int getCenterX() {
        return 0;
    }

    @Override
    public int getCenterY() {
        return 0;
    }

    @Override
    public int getRadius() {
        return 0;
    }

    @Override
    public int[] getXPoints() {
        return new int[0];
    }

    @Override
    public int[] getYPoints() {
        return new int[0];
    }

    @Override
    public void setFilled(boolean isFilled) {
        throw new UnsupportedOperationException("setFilled operation is not supported by Line");
    }

    @Override
    public boolean isFilled() {
        return false;
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

    public void setColor(Color newColor) {
        this.color = newColor;
    }
}