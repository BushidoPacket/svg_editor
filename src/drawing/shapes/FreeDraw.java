package drawing.shapes;

import drawing.Shape;

import java.awt.*;

public class FreeDraw implements Shape {
    private java.util.List<Point> points;
    private Color color;

    private static int radius = 5;

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
        g2d.setStroke(new BasicStroke(radius));
        Point lastPoint = null;
        for (Point point : points) {
            g2d.fillOval(point.x, point.y, radius, radius);
            if (lastPoint != null) {
                g2d.drawLine(lastPoint.x + radius / 2, lastPoint.y + radius / 2, point.x + radius / 2, point.y + radius / 2);
            }
            lastPoint = point;
        }
        g2d.setStroke(new BasicStroke(1));
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

    public static int getRd() {
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

    public void setColor(Color color) {
        this.color = color;
    }

}