package drawing;

import java.awt.*;

public class Ellipse implements Shape {
    private int centerX, centerY, radiusX, radiusY;
    private Color color;
    private boolean isFilled;

    public Ellipse(int centerX, int centerY, int radiusX, int radiusY, Color color, boolean isFilled) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.color = color;
        this.isFilled = isFilled;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        if (isFilled) {
            g2d.fillOval(centerX - radiusX, centerY - radiusY, radiusX * 2, radiusY * 2);
        } else {
            g2d.drawOval(centerX - radiusX, centerY - radiusY, radiusX * 2, radiusY * 2);
        }
    }

    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public boolean isFilled() {
        return isFilled;
    }

    @Override
    public void setStart(int x, int y) {
        throw new UnsupportedOperationException("setStart operation is not supported by Triangle");
    }

    @Override
    public void setEnd(int x, int y) {
        throw new UnsupportedOperationException("setStart operation is not supported by Triangle");
    }

    @Override
    public void setRadius(int radius) {
        throw new UnsupportedOperationException("setStart operation is not supported by Triangle");
    }

    @Override
    public void setPoints(int[] xPoints, int[] yPoints) {
        throw new UnsupportedOperationException("setStart operation is not supported by Triangle");
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
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

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(int radiusX) {
        this.radiusX = radiusX;
    }

    public int getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(int radiusY) {
        this.radiusY = radiusY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}