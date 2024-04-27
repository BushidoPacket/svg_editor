package drawing;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    static DrawingPanel drawingPanel;

    public MainFrame()
    {
        this.setTitle("PRO1 zápočtový projekt");
        this.setVisible(true);
        this.setBackground(Color.white);
        this.setSize(1200, 800);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        ShapeDetailsTablePanel shapeDetailsTablePanel = new ShapeDetailsTablePanel();
        ShapesTablePanel shapesTablePanel = new ShapesTablePanel(shapeDetailsTablePanel);
        drawingPanel = new DrawingPanel(shapesTablePanel);

        drawingPanel.setBackground(Color.white);
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
        eastPanel.add(shapesTablePanel);
        eastPanel.add(shapeDetailsTablePanel);

        this.add(drawingPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);

    }

    public static void openSVG () {
        new SvgOutput(drawingPanel);
    }
}
