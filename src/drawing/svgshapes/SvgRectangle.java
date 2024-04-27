// SvgRectangle.java
package drawing.svgshapes;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SvgRectangle {
    @XmlAttribute
    private int x;

    @XmlAttribute
    private int y;

    @XmlAttribute
    private int width;

    @XmlAttribute
    private int height;

    @XmlAttribute
    private String stroke;

    @XmlAttribute
    private String fill;

    @XmlAttribute(name = "data-index")
    private int dataIndex;


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
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