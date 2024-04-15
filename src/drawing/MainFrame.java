package drawing;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{

    public MainFrame()
    {
        this.setTitle("PRO1 zápočtový projekt");
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setSize(600, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        JPanel drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.white);
        this.add(drawingPanel);
    }
}
