package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static drawing.MainFrame.drawingPanel;

public class Toolbox {
    private ActionListener shapeButtonListener;
    private ActionListener colorButtonListener;
    private ActionListener fillButtonListener;
    private ActionListener outlineButtonListener;
    private ActionListener svgButtonListener;
    private ActionListener clearButtonListener;
    private static JLabel settingsLabel;


    public Toolbox(ActionListener shapeButtonListener,
                   ActionListener colorButtonListener,
                   ActionListener fillButtonListener,
                   ActionListener outlineButtonListener,
                   ActionListener svgButtonListener,
                   ActionListener clearButtonListener) {
        this.shapeButtonListener = shapeButtonListener;
        this.colorButtonListener = colorButtonListener;
        this.fillButtonListener = fillButtonListener;
        this.outlineButtonListener = outlineButtonListener;
        this.svgButtonListener = svgButtonListener;
        this.clearButtonListener = clearButtonListener;
        this.settingsLabel = new JLabel();

    }

    public JMenuBar createToolbox() {
        JMenuBar toolbox = new JMenuBar();
        toolbox.setBackground(Color.BLACK);

        //Shapes menu
        JMenu shapeMenu = new JMenu("Shapes") ;
        shapeMenu.setForeground(Color.yellow);

        JMenuItem freeDraw = new JMenuItem("Free Draw");
        freeDraw.addActionListener(shapeButtonListener);
        freeDraw.addActionListener(e -> {
            shapeS = "Free Draw";
            updateSettingsText();
        });
        shapeMenu.add(freeDraw);

        JMenuItem btnLine = new JMenuItem("Line");
        btnLine.addActionListener(shapeButtonListener);
        btnLine.addActionListener(e -> {
            shapeS = "Line";
            updateSettingsText();
        });
        shapeMenu.add(btnLine);

        JMenuItem btnRect = new JMenuItem("Rectangle");
        btnRect.addActionListener(shapeButtonListener);
        btnRect.addActionListener(e -> {
            shapeS = "Rectangle";
            updateSettingsText();
        });
        shapeMenu.add(btnRect);

        JMenuItem btnCircle = new JMenuItem("Circle");
        btnCircle.addActionListener(shapeButtonListener);
        btnCircle.addActionListener(e -> {
            shapeS = "Circle";
            updateSettingsText();
        });
        shapeMenu.add(btnCircle);

        JMenuItem btnTriangle = new JMenuItem("Triangle");
        btnTriangle.addActionListener(shapeButtonListener);
        btnTriangle.addActionListener(e -> {
            shapeS = "Triangle";
            updateSettingsText();
        });
        shapeMenu.add(btnTriangle);

        JMenuItem btnEllipse = new JMenuItem("Ellipse");
        btnEllipse.addActionListener(shapeButtonListener);
        btnEllipse.addActionListener(e -> {
            shapeS = "Ellipse";
            updateSettingsText();
        });
        shapeMenu.add(btnEllipse);


        //Draw settings menu
        JMenu drawSettings = new JMenu("Draw Settings");
        drawSettings.setForeground(Color.yellow);

        JMenuItem btnColor = new JMenuItem("Color");
        btnColor.addActionListener(colorButtonListener);
        drawSettings.add(btnColor);

        JMenuItem btnFill = new JMenuItem("Fill");
        btnFill.addActionListener(fillButtonListener);
        btnFill.addActionListener(e -> {
            fillS = "Fill";
            updateSettingsText();
        });
        drawSettings.add(btnFill);

        JMenuItem btnOutline = new JMenuItem("Outline");
        btnOutline.addActionListener(outlineButtonListener);
        btnOutline.addActionListener(e -> {
            fillS = "Outline";
            updateSettingsText();
        });
        drawSettings.add(btnOutline);

        JMenuItem btnClear = new JMenuItem("Clear");
        btnClear.addActionListener(clearButtonListener);
        drawSettings.add(btnClear);



        //File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.yellow);

        JMenuItem openF = new JMenuItem("Open file...");
        openF.addActionListener(e -> TextFileHandler.loadTextFileToSvg(drawingPanel, new JFileChooser()));
        fileMenu.add(openF);

        JMenuItem saveF = new JMenuItem("Save file...");
        saveF.addActionListener(e -> TextFileHandler.saveTextFileFromSvg(new JFileChooser()));
        fileMenu.add(saveF);

        JMenuItem btnSVG = new JMenuItem("SVG editor");
        btnSVG.addActionListener(svgButtonListener);
        fileMenu.add(btnSVG);

        //Order of menus
        toolbox.add(fileMenu);
        toolbox.add(shapeMenu);
        toolbox.add(drawSettings);
        toolbox.add(Box.createHorizontalGlue());
        toolbox.add(settingsLabel);
        updateSettingsText();

        settingsLabel.setBackground(Color.black);
        settingsLabel.setForeground(Color.white);

        return toolbox;
    }

    static String shapeS = "none";
    static String colorS = "#000000";
    static String fillS = "Outline";
    public static void updateSettingsText() {
        settingsLabel.setText("Shape: " + shapeS + " | Color: " + colorS + " | Fill: " + fillS + "    ");
    }

    public static void updateColorS(Color color) {
        colorS = "#" + Integer.toHexString(color.getRGB()).substring(2);
        updateSettingsText();
    }
}