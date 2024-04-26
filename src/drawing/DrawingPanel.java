// DrawingPanel.java
package drawing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DrawingPanel extends JPanel {
    private ShapesTablePanel shapesTablePanel;
    private ArrayList<Shape> shapes = new ArrayList<>();
    private String shapeType = "none";
    private int initialX, initialY;
    private Color currentColor = Color.BLACK;

    private boolean isFilled = false;

    public DrawingPanel(ShapesTablePanel shapesTablePanel) {
        this.shapesTablePanel = shapesTablePanel;
        setLayout(new BorderLayout());

        Toolbox toolbox = new Toolbox(
                e -> shapeType = e.getActionCommand(),
                e -> {
                    currentColor = JColorChooser.showDialog(null, "Choose a color", currentColor);
                    Toolbox.updateColorS(currentColor);
                },
                e -> isFilled = true,
                e -> isFilled = false,
                e -> MainFrame.openSVG(),
                e -> clear()
        );
        add(toolbox.createToolbox(), BorderLayout.NORTH);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
                initialY = e.getY();
                String colorHex = String.format("#%02x%02x%02x", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());

                if (shapeType.equals("Free Draw")) {
                    shapes.add(new FreeDraw(currentColor));
                }
                if (shapeType.equals("Rectangle")) {
                    shapes.add(new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor, isFilled));
                    shapesTablePanel.addShape("Rectangle", "x: " + e.getX() + ", y: " + e.getY(), colorHex);
                }
                if (shapeType.equals("Circle")) {
                    int radius = (int) Math.sqrt(Math.pow(e.getX() - e.getX(), 2) + Math.pow(e.getY() - e.getY(), 2));
                    shapes.add(new Circle(e.getX(), e.getY(), radius, currentColor, isFilled));
                    shapesTablePanel.addShape("Circle", "centerX: " + e.getX() + ", centerY: " + e.getY() + ", radius: " + radius, colorHex);
                }
                if (shapeType.equals("Triangle")) {
                    int[] xPoints = {e.getX(), e.getX() + 50, e.getX() - 50};
                    int[] yPoints = {e.getY(), e.getY() + 50, e.getY() + 50};
                    shapes.add(new Triangle(xPoints, yPoints, currentColor, isFilled));
                    shapesTablePanel.addShape("Triangle", "X: " + Arrays.toString(xPoints) + ", Y: " + Arrays.toString(yPoints), colorHex);
                }
                if (shapeType.equals("Ellipse")) {
                    shapes.add(new Ellipse(e.getX(), e.getY(), 0, 0, currentColor, isFilled));
                    shapesTablePanel.addShape("Ellipse", "centerX: " + e.getX() + ", centerY: " + e.getY() + ", radiusX: 0, radiusY: 0", colorHex);
                }
                if (shapeType.equals("Line")) {
                    shapes.add(new Line(e.getX(), e.getY(), e.getX(), e.getY(), currentColor));
                    shapesTablePanel.addShape("Line", "startX: " + e.getX() + ", startY: " + e.getY(), colorHex);
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int lastIndex = shapesTablePanel.getTable().getRowCount() - 1;
                String colorHex = String.format("#%02x%02x%02x", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());

                if (shapeType.equals("Free Draw")) {
                    if (shapes.get(shapes.size() - 1) instanceof FreeDraw) {
                        FreeDraw lastFreeDraw = (FreeDraw) shapes.get(shapes.size() - 1);
                        lastFreeDraw.addPoint(e.getX(), e.getY());
                    }
                }
                if (shapeType.equals("Rectangle")) {
                    shapes.get(shapes.size() - 1).setEnd(e.getX(), e.getY());
                    if (shapes.get(shapes.size() - 1) instanceof Rectangle) {
                        Rectangle lastRectangle = (Rectangle) shapes.get(shapes.size() - 1);
                        int width = lastRectangle.getEndX() - lastRectangle.getStartX();
                        int height = lastRectangle.getEndY() - lastRectangle.getStartY();
                        shapesTablePanel.updateShape(lastIndex, "Rectangle", "x: " + lastRectangle.getStartX() + ", y: " + lastRectangle.getStartY() + ", width: " + width + ", height: " + height, colorHex);                    }
                }
                if (shapeType.equals("Circle")) {
                    int radius = (int) Math.sqrt(Math.pow(e.getX() - shapes.get(shapes.size() - 1).getCenterX(), 2) + Math.pow(e.getY() - shapes.get(shapes.size() - 1).getCenterY(), 2));
                    shapes.get(shapes.size() - 1).setRadius(radius);
                    if (shapes.get(shapes.size() - 1) instanceof Circle) {
                        Circle lastCircle = (Circle) shapes.get(shapes.size() - 1);
                        shapesTablePanel.updateShape(lastIndex, "Circle", "centerX: " + lastCircle.getCenterX() + ", centerY: " + lastCircle.getCenterY() + ", rad: " + lastCircle.getRadius(), colorHex);
                    }
                }
                if (shapeType.equals("Triangle")) {
                    int dx = e.getX() - initialX;
                    int dy = e.getY() - initialY;
                    int[] xPoints = {initialX, initialX + dx, initialX - dx};
                    int[] yPoints = {initialY - dy, initialY + dy, initialY + dy};
                    shapes.get(shapes.size() - 1).setPoints(xPoints, yPoints);
                    if (shapes.get(shapes.size() - 1) instanceof Triangle) {
                        Triangle lastTriangle = (Triangle) shapes.get(shapes.size() - 1);
                        shapesTablePanel.updateShape(lastIndex, "Triangle", "X: " + Arrays.toString(lastTriangle.getXPoints()) + ", Y: " + Arrays.toString(lastTriangle.getYPoints()), colorHex);
                    }
                }
                if (shapeType.equals("Ellipse")) {
                    Ellipse lastEllipse = (Ellipse) shapes.get(shapes.size() - 1);
                    lastEllipse.setRadiusX(Math.abs(e.getX() - lastEllipse.getCenterX()));
                    lastEllipse.setRadiusY(Math.abs(e.getY() - lastEllipse.getCenterY()));
                    if (shapes.get(shapes.size() - 1) instanceof Ellipse) {
                        shapesTablePanel.updateShape(lastIndex, "Ellipse", "centerX: " + lastEllipse.getCenterX() + ", centerY: " + lastEllipse.getCenterY() + ", radiusX: " + lastEllipse.getRadiusX() + ", radiusY: " + lastEllipse.getRadiusY(), colorHex);
                    }
                }
                if (shapeType.equals("Line")) {
                    shapes.get(shapes.size() - 1).setEnd(e.getX(), e.getY());
                    if (shapes.get(shapes.size() - 1) instanceof Line) {
                        Line lastLine = (Line) shapes.get(shapes.size() - 1);
                        shapesTablePanel.updateShape(lastIndex, "Line", "startX: " + lastLine.getStartX() + ", startY: " + lastLine.getStartY() + ", endX: " + lastLine.getEndX() + ", endY: " + lastLine.getEndY(), colorHex);
                    }
                }
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e){
                int lastIndex = shapesTablePanel.getTable().getRowCount() - 1;
                String colorHex = String.format("#%02x%02x%02x", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());

                if (shapeType.equals("Free Draw")) {
                    if (shapes.get(shapes.size() - 1) instanceof FreeDraw) {
                        FreeDraw lastFreeDraw = (FreeDraw) shapes.get(shapes.size() - 1);
                        lastFreeDraw.addPoint(e.getX(), e.getY());
                    }
                }
                if (shapeType.equals("Rectangle")) {
                    if (shapes.get(shapes.size() - 1) instanceof Rectangle) {
                        Rectangle lastRectangle = (Rectangle) shapes.get(shapes.size() - 1);
                        int width = lastRectangle.getEndX() - lastRectangle.getStartX();
                        int height = lastRectangle.getEndY() - lastRectangle.getStartY();
                        shapesTablePanel.updateShape(lastIndex, "Rectangle", "x: " + lastRectangle.getStartX() + ", y: " + lastRectangle.getStartY() + ", width: " + width + ", height: " + height, colorHex);                    }
                }
                if (shapeType.equals("Circle")) {
                    if (shapes.get(shapes.size() - 1) instanceof Circle) {
                        Circle lastCircle = (Circle) shapes.get(shapes.size() - 1);
                        shapesTablePanel.updateShape(lastIndex, "Circle", "centerX: " + lastCircle.getCenterX() + ", centerY: " + lastCircle.getCenterY() + ", rad: " + lastCircle.getRadius(), colorHex);
                    }
                }
                if (shapeType.equals("Triangle")) {
                    if (shapes.get(shapes.size() - 1) instanceof Triangle) {
                        Triangle lastTriangle = (Triangle) shapes.get(shapes.size() - 1);
                        shapesTablePanel.updateShape(lastIndex, "Triangle", "X: " + Arrays.toString(lastTriangle.getXPoints()) + ", Y: " + Arrays.toString(lastTriangle.getYPoints()), colorHex);
                    }
                }
                if (shapeType.equals("Ellipse")) {
                    Ellipse lastEllipse = (Ellipse) shapes.get(shapes.size() - 1);
                    if(shapes.get(shapes.size() - 1) instanceof Ellipse) {
                        shapesTablePanel.updateShape(lastIndex, "Ellipse", "centerX: " + lastEllipse.getCenterX() + ", centerY: " + lastEllipse.getCenterY() + ", radiusX: " + lastEllipse.getRadiusX() + ", radiusY: " + lastEllipse.getRadiusY(), colorHex);
                    }
                }
                if (shapeType.equals("Line")) {
                    shapes.get(shapes.size() - 1).setEnd(e.getX(), e.getY());
                    if (shapes.get(shapes.size() - 1) instanceof Line) {
                        Line lastLine = (Line) shapes.get(shapes.size() - 1);
                        shapesTablePanel.updateShape(lastIndex, "Line", "startX: " + lastLine.getStartX() + ", startY: " + lastLine.getStartY() + ", endX: " + lastLine.getEndX() + ", endY: " + lastLine.getEndY(), colorHex);
                    }
                }
                //shapeType = "none";
                repaint();
                SvgGenerator.generateSvg(shapes);
            }
        });
    }

    public void loadFromSvg(String svgCode) {
        shapes.clear();
        shapesTablePanel.clear();

        // Parse the SVG code
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(svgCode)));
            NodeList elements = doc.getElementsByTagName("*");
            for (int i = 0; i < elements.getLength(); i++) {
                Element element = (Element) elements.item(i);
                String tagName = element.getTagName();

                switch (tagName) {
                    case "rect":
                        int x = Integer.parseInt(element.getAttribute("x"));
                        int y = Integer.parseInt(element.getAttribute("y"));
                        int width = Integer.parseInt(element.getAttribute("width"));
                        int height = Integer.parseInt(element.getAttribute("height"));
                        Color color = Color.decode(element.getAttribute("stroke"));
                        boolean isFilled = !element.getAttribute("fill").equals("none");
                        shapes.add(new Rectangle(x, y, x + width, y + height, color, isFilled));
                        shapesTablePanel.addShape("Rectangle", "x: " + x + ", y: " + y + ", width: " + width + ", height: " + height, "#" + Integer.toHexString(color.getRGB()).substring(2));
                        break;
                    case "circle":
                        int centerX = Integer.parseInt(element.getAttribute("cx"));
                        int centerY = Integer.parseInt(element.getAttribute("cy"));
                        int radius = Integer.parseInt(element.getAttribute("r"));
                        Color circleColor = Color.decode(element.getAttribute("stroke"));
                        boolean circleIsFilled = !element.getAttribute("fill").equals("none");
                        shapes.add(new Circle(centerX, centerY, radius, circleColor, circleIsFilled));
                        shapesTablePanel.addShape("Circle", "centerX: " + centerX + ", centerY: " + centerY + ", rad: " + radius, "#" + Integer.toHexString(circleColor.getRGB()).substring(2));
                        break;
                    case "polygon":
                        String points = element.getAttribute("points");
                        String[] pointsArray = points.split(" ");
                        int[] xPoints = new int[3];
                        int[] yPoints = new int[3];
                        for (int j = 0; j < 3; j++) {
                            String[] xy = pointsArray[j].split(",");
                            xPoints[j] = Integer.parseInt(xy[0]);
                            yPoints[j] = Integer.parseInt(xy[1]);
                        }
                        Color triangleColor = Color.decode(element.getAttribute("stroke"));
                        boolean triangleIsFilled = !element.getAttribute("fill").equals("none");
                        shapes.add(new Triangle(xPoints, yPoints, triangleColor, triangleIsFilled));
                        shapesTablePanel.addShape("Triangle", "X: " + Arrays.toString(xPoints) + ", Y: " + Arrays.toString(yPoints), "#" + Integer.toHexString(triangleColor.getRGB()).substring(2));
                        break;
                    case "ellipse":
                        int ellipseCenterX = Integer.parseInt(element.getAttribute("cx"));
                        int ellipseCenterY = Integer.parseInt(element.getAttribute("cy"));
                        int radiusX = Integer.parseInt(element.getAttribute("rx"));
                        int radiusY = Integer.parseInt(element.getAttribute("ry"));
                        Color ellipseColor = Color.decode(element.getAttribute("stroke"));
                        boolean ellipseIsFilled = !element.getAttribute("fill").equals("none");
                        shapes.add(new Ellipse(ellipseCenterX, ellipseCenterY, radiusX, radiusY, ellipseColor, ellipseIsFilled));
                        shapesTablePanel.addShape("Ellipse", "centerX: " + ellipseCenterX + ", centerY: " + ellipseCenterY + ", radX: " + radiusX + ", radY: " + radiusY, "#" + Integer.toHexString(ellipseColor.getRGB()).substring(2));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading SVG: " + e);
        }

        repaint();
    }

    public void clear() {
        shapes.clear();
        shapesTablePanel.clear();
        SvgGenerator.clear();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }
}