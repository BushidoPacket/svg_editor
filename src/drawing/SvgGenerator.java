package drawing;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

// Will generate SVG code based on patterns of shapes
public class SvgGenerator {
    public static void generateSvg(List<Shape> shapes, int shapeIndex) {
        try {
            JAXBContext context = JAXBContext.newInstance(Svg.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String svgString = "";

            for (Shape shape : shapes) {
                Svg svg = new Svg();
                String colorHex = String.format("#%02x%02x%02x", shape.getColor().getRed(), shape.getColor().getGreen(), shape.getColor().getBlue());

                switch (shape) {
                    case Rectangle rectangle -> {
                        SvgRectangle svgRectangle = new SvgRectangle();
                        svgRectangle.setX(rectangle.getStartX());
                        svgRectangle.setY(rectangle.getStartY());
                        svgRectangle.setWidth(rectangle.getEndX() - rectangle.getStartX());
                        svgRectangle.setHeight(rectangle.getEndY() - rectangle.getStartY());
                        svgRectangle.setStroke(colorHex);
                        svgRectangle.setFill(shape.isFilled() ? colorHex : "none");
                        svgRectangle.setDataIndex(shapeIndex);
                        svg.setRectangle(svgRectangle);
                    }
                    case Ellipse ellipse -> {
                        SvgEllipse svgEllipse = new SvgEllipse();
                        svgEllipse.setCx(ellipse.getCenterX());
                        svgEllipse.setCy(ellipse.getCenterY());
                        svgEllipse.setRx(ellipse.getRadiusX());
                        svgEllipse.setRy(ellipse.getRadiusY());
                        svgEllipse.setStroke(colorHex);
                        svgEllipse.setFill(shape.isFilled() ? colorHex : "none");
                        svgEllipse.setDataIndex(shapeIndex);
                        svg.setEllipse(svgEllipse);
                    }
                    case Circle circle -> {
                        SvgCircle svgCircle = new SvgCircle();
                        svgCircle.setCx(circle.getCenterX());
                        svgCircle.setCy(circle.getCenterY());
                        svgCircle.setR(circle.getRadius());
                        svgCircle.setStroke(colorHex);
                        svgCircle.setFill(shape.isFilled() ? colorHex : "none");
                        svgCircle.setDataIndex(shapeIndex);
                        svg.setCircle(svgCircle);
                    }
                    case Triangle triangle -> {
                        SvgTriangle svgTriangle = new SvgTriangle();
                        svgTriangle.setPoints(triangle.getXPoints()[0] + "," + triangle.getYPoints()[0] + " " +
                                triangle.getXPoints()[1] + "," + triangle.getYPoints()[1] + " " +
                                triangle.getXPoints()[2] + "," + triangle.getYPoints()[2]);
                        svgTriangle.setStroke(colorHex);
                        svgTriangle.setFill(shape.isFilled() ? colorHex : "none");
                        svgTriangle.setDataIndex(shapeIndex);
                        svg.setTriangle(svgTriangle);
                    }
                    case Line line -> {
                        SvgLine svgLine = new SvgLine();
                        svgLine.setX1(line.getStartX());
                        svgLine.setY1(line.getStartY());
                        svgLine.setX2(line.getEndX());
                        svgLine.setY2(line.getEndY());
                        svgLine.setStroke(colorHex);
                        svgLine.setDataIndex(shapeIndex);
                        svg.setLine(svgLine);
                    }
                    case FreeDraw freeDraw -> {
                        List<SvgCircle> svgCircles = new ArrayList<>();
                        for (Point point : freeDraw.getPoints()) {
                            SvgCircle svgCircle = new SvgCircle();
                            svgCircle.setCx(point.x);
                            svgCircle.setCy(point.y);
                            svgCircle.setR(freeDraw.getRadius()/2);
                            svgCircle.setStroke(colorHex);
                            svgCircle.setFill(colorHex);
                            svgCircles.add(svgCircle);
                        }
                        SvgFreeDraw svgFreeDraw = new SvgFreeDraw();
                        svgFreeDraw.setCircles(svgCircles.toArray(new SvgCircle[0]));
                        svg.setFreeDraw(svgFreeDraw);
                    }
                    default -> {
                    }
                }

                StringWriter sw = new StringWriter();
                marshaller.marshal(svg, sw);
                svgString = sw.toString();

            }
            SvgOutput.setText(svgString, "inner");
        } catch (Exception e) {
            System.out.println("Svg Generator error: " + e);
        }
    }

    public static void clear() {
        SvgOutput.clear();
    }
}