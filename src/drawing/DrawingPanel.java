// DrawingPanel.java
package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Rectangle> rectangles = new ArrayList<>();
    private List<Circle> circles = new ArrayList<>();
    private List<Triangle> triangles = new ArrayList<>();
    private String shapeType = "none";

    public DrawingPanel() {
        setLayout(new BorderLayout());

        JPanel toolbox = new JPanel();

        JButton btnRect = new JButton("Rectangle");
        btnRect.setBackground(Color.BLACK);
        btnRect.setForeground(Color.WHITE);
        btnRect.setFocusable(false);
        toolbox.add(btnRect);

        JButton btnCircle = new JButton("Circle");
        btnCircle.setBackground(Color.BLACK);
        btnCircle.setForeground(Color.WHITE);
        btnCircle.setFocusable(false);
        toolbox.add(btnCircle);

        JButton btnTri = new JButton("Triangle");
        btnTri.setBackground(Color.BLACK);
        btnTri.setForeground(Color.WHITE);
        btnTri.setFocusable(false);
        toolbox.add(btnTri);

        add(toolbox, BorderLayout.NORTH);

        btnRect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeType = "Rectangle";
            }
        });

        btnCircle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeType = "Circle";
            }
        });

        btnTri.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeType = "Triangle";
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (shapeType.equals("Rectangle")) {
                    rectangles.add(new Rectangle(e.getX(), e.getY(), e.getX(), e.getY()));
                }
                if (shapeType.equals("Circle")) {
                    int radius = (int) Math.sqrt(Math.pow(e.getX() - e.getX(), 2) + Math.pow(e.getY() - e.getY(), 2));
                    circles.add(new Circle(e.getX(), e.getY(), radius));
                }
                if (shapeType.equals("Triangle")) {
                    // Create arrays for the vertices of the triangle
                    int[] xPoints = {e.getX(), e.getX() + 50, e.getX() - 50}; // Example: x-coordinate of the mouse click and two arbitrary x-coordinates for vertices
                    int[] yPoints = {e.getY(), e.getY() + 50, e.getY() + 50}; // Example: y-coordinate of the mouse click and two arbitrary y-coordinates for vertices
                    triangles.add(new Triangle(xPoints, yPoints));
                }
                repaint();
            }
        });

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (shapeType.equals("Rectangle")) {
                    rectangles.getLast().setEnd(e.getX(), e.getY());
                }
                if (shapeType.equals("Circle")) {
                    int radius = (int) Math.sqrt(Math.pow(e.getX() - circles.getLast().getCenterX(), 2) + Math.pow(e.getY() - circles.getLast().getCenterY(), 2));
                    circles.getLast().setRadius(radius);
                }
                if (shapeType.equals("Triangle")) {
                    // Calculate the changes in x and y coordinates for all vertices
                    int dx = e.getX() - triangles.getLast().getXPoints()[0];
                    int dy = e.getY() - triangles.getLast().getYPoints()[0];

                    // Update all vertices based on the changes
                    int[] xPoints = {e.getX(), e.getX() + 50 + dx, e.getX() - 50 + dx};
                    int[] yPoints = {e.getY(), e.getY() + 50 + dy, e.getY() + 50 + dy};
                    triangles.getLast().setPoints(xPoints, yPoints);
                }
                repaint();
            }
        });

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e){
                shapeType = "none";
            }
        });


    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Rectangle rectangle : rectangles) {
            rectangle.draw(g2d);
        }
        for (Circle circle : circles) {
            circle.draw(g2d);
        }
        for (Triangle triangle : triangles) {
            triangle.draw(g2d);
        }
    }
}
