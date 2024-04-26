// SvgTriangle.java
package drawing;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SvgTriangle {
    @XmlAttribute
    private String points;

    @XmlAttribute
    private String stroke;

    @XmlAttribute
    private String fill;

    public void setPoints(String points) {
        this.points = points;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }
}