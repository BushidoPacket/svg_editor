package drawing;

import java.awt.*;

public class FreeDraw implements Shape {
    private java.util.List<Point> points;
    private Color color;

    private int radius = 10;

    public FreeDraw(Color color) {
        this.color = color;
        this.points = new java.util.ArrayList<>();
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        for (Point point : points) {
            g2d.fillOval(point.x, point.y, radius, radius);
        }
    }

    @Override
    public void setStart(int x, int y) {
        throw new UnsupportedOperationException("FreeDraw does not support setStart");
    }

    @Override
    public void setEnd(int x, int y) {
        throw new UnsupportedOperationException("FreeDraw does not support setEnd");
    }

    @Override
    public void setRadius(int radius) {
        throw new UnsupportedOperationException("FreeDraw does not support setRadius");
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void setPoints(int[] xPoints, int[] yPoints) {
        throw new UnsupportedOperationException("FreeDraw does not support setPoints");
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
    public int[] getXPoints() {
        return new int[0];
    }

    @Override
    public int[] getYPoints() {
        return new int[0];
    }

    @Override
    public void setFilled(boolean isFilled) {
        throw new UnsupportedOperationException("FreeDraw does not support setFilled");
    }

    @Override
    public boolean isFilled() {
        throw new UnsupportedOperationException("FreeDraw does not support isFilled");
    }

    @Override
    public Color getColor() {
        return color;
    }

    public Point[] getPoints() {
        return points.toArray(new Point[0]);
    }

}