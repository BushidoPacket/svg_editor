package drawing.svgshapes;

import drawing.svgshapes.SvgCircle;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SvgFreeDraw {
    @XmlElement(name = "path")
    private SvgCircle[] circles;

    // getters and setters

    public void setCircles(SvgCircle[] circles) {
        this.circles = circles;
    }

    public SvgCircle[] getCircles() {
        return circles;
    }
}