// SvgCircle.java
package drawing.svgshapes;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SvgCircle {
    @XmlAttribute
    private int cx;

    @XmlAttribute
    private int cy;

    @XmlAttribute
    private int r;

    @XmlAttribute
    private String stroke;

    @XmlAttribute
    private String fill;

    @XmlAttribute(name = "data-index")
    private int dataIndex;

    // getters and setters

    public void setCx(int cx) {
        this.cx = cx;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public void setR(int r) {
        this.r = r;
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