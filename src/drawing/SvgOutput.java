package drawing;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class SvgOutput extends JFrame {
    private static JTextArea textArea;
    private static String original = "";
    private static String output = "";
    //private DrawingPanel drawingPanel;
    private static final String initialText = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <svg>
            </svg>""";
    public SvgOutput(DrawingPanel drawingPanel) {
        super("SVG editor");
        //this.drawingPanel = drawingPanel;
        this.setSize(600, 800);
        setLocationRelativeTo(null);

        textArea = new JTextArea(20,30);
        add(new JScrollPane(textArea));

        setVisible(true);
        if(!Objects.equals(output, "")){
            textArea.setText(output);
        } else {
            textArea.setText(initialText);
        }

        JButton update = new JButton("Load image from SVG");
        update.addActionListener(e -> drawingPanel.loadFromSvg(textArea.getText()));

        JButton loadButton = new JButton("Load text file");
        loadButton.addActionListener(e -> TextFileHandler.loadTextFileToSvg(drawingPanel, new JFileChooser()));


        JButton saveButton = new JButton("Save text to file");
        saveButton.addActionListener(e -> TextFileHandler.saveTextFileFromSvg(new JFileChooser()));


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(update);

        add(buttonPanel, BorderLayout.NORTH);

    }

    //<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    //<svg>
    //    <rect x="425" y="389" width="14" height="4" stroke="#000000" fill="none"/>
    //</svg>

    public static void setText(String text) {
        try {
            original = textArea.getText();
        } catch(NullPointerException e) {
            if(Objects.equals(original, "")){
                original = initialText;
            } else {
                original = output;
            }
        }
        original = original.replace("</svg>", "");
        //original = original.replaceAll("(?m)^[ \t]*\r?\n", "");
        text = text.replace("<svg>", "");
        text = text.replace("</svg>", "");
        text = text.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        text = text.trim();
        output = original + "\t" + text + "\n" + "</svg>";
        try {
            textArea.setText(original + "\t" + text + "\n" + "</svg>");
        } catch(NullPointerException e) {
            // Do nothing
        }
    }

    public static String getOutput() {
        return output;
    }

    public static void clear() {
        output = initialText;
        try {
            textArea.setText(initialText);
        } catch(NullPointerException e) {
            // Do nothing
        }
    }

}