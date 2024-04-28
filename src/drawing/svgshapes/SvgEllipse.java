// SvgEllipse.java
package drawing.svgshapes;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SvgEllipse {
    @XmlAttribute
    private int cx;

    @XmlAttribute
    private int cy;

    @XmlAttribute
    private int rx;

    @XmlAttribute
    private int ry;

    @XmlAttribute
    private String stroke;

    @XmlAttribute
    private String fill;
    @XmlAttribute(name = "data-index")
    private int dataIndex;

    public void setCx(int cx) {
        this.cx = cx;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public void setRx(int rx) {
        this.rx = rx;
    }

    public void setRy(int ry) {
        this.ry = ry;
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