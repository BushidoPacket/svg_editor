// SvgTriangle.java
package drawing.svgshapes;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SvgTriangle {
    @XmlAttribute
    private String points;

    @XmlAttribute
    private String stroke;

    @XmlAttribute
    private String fill;

    @XmlAttribute(name = "data-index")
    private int dataIndex;

    public void setPoints(String points) {
        this.points = points;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public void setDataIndex(int dataIndex) {
        this.dataIndex = dataIndex;
    }
}