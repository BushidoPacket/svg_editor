// Rectangle.java
package drawing;

import java.awt.*;

public class Rectangle {
    private int startX, startY, endX, endY;

    public Rectangle(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public void setEnd(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        int width = endX - startX;
        int height = endY - startY;
        g2d.fillRect(startX, startY, width, height);
    }
}
