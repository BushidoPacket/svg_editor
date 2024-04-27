// Svg.java
package drawing;
import drawing.svgshapes.*;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "svg")
@XmlAccessorType(XmlAccessType.FIELD)
public class Svg {
    @XmlElement(name = "rect")
    private SvgRectangle rectangle;

    @XmlElement(name = "ellipse")
    private SvgEllipse ellipse;

    @XmlElement(name = "circle")
    private SvgCircle circle;

    @XmlElement(name = "polygon")
    private SvgTriangle triangle;

    @XmlElement(name = "line")
    private SvgLine line;

    @XmlElement(name = "g")
    private SvgFreeDraw freeDraw;

    public void setRectangle(SvgRectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setEllipse(SvgEllipse ellipse) {
        this.ellipse = ellipse;
    }

    public void setCircle(SvgCircle circle) {
        this.circle = circle;
    }

    public void setTriangle(SvgTriangle triangle) {
        this.triangle = triangle;
    }

    public void setLine(SvgLine line) {
        this.line = line;
    }

    public void setFreeDraw(SvgFreeDraw freeDraw) {
        this.freeDraw = freeDraw;
    }
}