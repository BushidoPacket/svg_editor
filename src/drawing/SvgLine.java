package drawing;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class SvgLine {
    @XmlAttribute
    private int x1;

    @XmlAttribute
    private int y1;

    @XmlAttribute
    private int x2;

    @XmlAttribute
    private int y2;

    @XmlAttribute
    private String stroke;

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public String getStroke() {
        return stroke;
    }
}