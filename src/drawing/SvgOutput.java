package drawing;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;

import static drawing.MainFrame.drawingPanel;

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
        this.setSize(800, 800);
        setLocationRelativeTo(null);

        textArea = new JTextArea(20,30);
        add(new JScrollPane(textArea));

        setVisible(true);
        if(!Objects.equals(output, "")){
            textArea.setText(output);
        } else {
            textArea.setText(initialText);
        }

        JButton update = new JButton("Load image from text");
        update.addActionListener(e -> drawingPanel.loadFromSvg(textArea.getText()));

        JButton loadButton = new JButton("Load text/XML file");
        loadButton.addActionListener(e -> TextFileHandler.loadTextFileToSvg(drawingPanel, new JFileChooser()));


        JButton saveButton = new JButton("Save text to text/XML file");
        saveButton.addActionListener(e -> TextFileHandler.saveTextFileFromSvg(new JFileChooser()));

        JButton saveButtonJSON = new JButton("Save text to JSON file");
        saveButtonJSON.addActionListener(e -> TextFileHandler.saveJSON(new JFileChooser()));


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(saveButtonJSON);
        buttonPanel.add(update);

        add(buttonPanel, BorderLayout.NORTH);

    }

    //<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    //<svg>
    //    <rect x="425" y="389" width="14" height="4" stroke="#000000" fill="none"/>
    //</svg>

    //Set text to textArea
    public static void setText(String text, String type) {
        //Decide whether to replace original string or append existing text
        switch(type) {
            case "inner":
                try {
                    original = textArea.getText();
                } catch(NullPointerException e) {
                    if(Objects.equals(original, "")){
                        original = initialText;
                    } else {
                        original = output;
                    }
                }
                break;
            case "file":
                original = initialText;
                break;
            case "edit":
                original = initialText;
                break;
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

    //Set text to textArea, usage for file loading to replace whatever is in textArea
    /*public static void setFileText(String text) {
        original = initialText;
        original = original.replace("</svg>", "");
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
    }*/

    public static String getOutput() {
        return output;
    }

    public static void updateShape(int shapeIndex, String newValues) {
        String svgText = output;
        Pattern pattern = Pattern.compile("<.*?/>");
        Matcher matcher = pattern.matcher(svgText);

        while (matcher.find()) {
            String svgObject = matcher.group();
            if (svgObject.contains("data-index=\"" + shapeIndex + "\"")) {
                //System.out.println(svgObject);
                //System.out.println("Vysledek: " + shapeUpdateHandler(svgObject, newValues));
                String updatedSvgObject = shapeUpdateHandler(svgObject, newValues);
                output = output.replace(svgObject, updatedSvgObject);
                setText(output, "edit");
                drawingPanel.loadFromSvg(output);
                break;
            }
        }
    }

    public static String shapeUpdateHandler(String inputSvg, String newValues){
        String result = "";
        String[] words = inputSvg.split(" ");
        String shapeType = words[0].replace("<", "");
        String[] values = newValues.split(", ");
        System.out.println(shapeType);

        switch(shapeType){
            case "circle":
                words[1] = "cx=\"" + values[3] + "\"";
                words[2] = "cy=\"" + values[4] + "\"";
                words[3] = "r=\"" + values[5] + "\"";
                words[4] = "stroke=\"" + values[2] + "\"";
                result = words[0] + " " + words[1] + " " + words[2] + " " + words[3] + " " + words[4] + " " + words[5] + " data-index=\"" + values[1] + "\"/>";
                break;
            case "rect":
                words[1] = "x=\"" + values[3] + "\"";
                words[2] = "y=\"" + values[4] + "\"";
                words[3] = "width=\"" + values[5] + "\"";
                words[4] = "height=\"" + values[6] + "\"";
                words[5] = "stroke=\"" + values[2] + "\"";
                result = words[0] + " " + words[1] + " " + words[2] + " " + words[3] + " " + words[4] + " " + words[5] + " " + words[6] + " data-index=\"" + values[1] + "\"/>";
                break;
            case "line":
                words[1] = "x1=\"" + values[3] + "\"";
                words[2] = "y1=\"" + values[4] + "\"";
                words[3] = "x2=\"" + values[5] + "\"";
                words[4] = "y2=\"" + values[6] + "\"";
                words[5] = "stroke=\"" + values[2] + "\"";
                result = words[0] + " " + words[1] + " " + words[2] + " " + words[3] + " " + words[4] + " " + words[5] + " data-index=\"" + values[1] + "\"/>";
                break;
            case "polygon":
                words[1] = "points=\"" + values[3] + "," + values[6] + " " + values[4] + "," + values[7] + " " + values[5] + "," + values[8] + "\"";
                words[2] = "stroke=\"" + values[2] + "\"";
                result = words[0] + " " + words[1] + " " + words[2] + " " + words[5] + " data-index=\"" + values[1] + "\"/>";
                break;
            case "ellipse":
                words[1] = "cx=\"" + values[3] + "\"";
                words[2] = "cy=\"" + values[4] + "\"";
                words[3] = "rx=\"" + values[5] + "\"";
                words[4] = "ry=\"" + values[6] + "\"";
                words[5] = "stroke=\"" + values[2] + "\"";
                result = words[0] + " " + words[1] + " " + words[2] + " " + words[3] + " " + words[4] + " " + words[5] + " " + words[6] + " data-index=\"" + values[1] + "\"/>";
                break;
        }
        return result;
    }

    public static String svgStringToJson(String svgString) {
        Gson gson = new Gson();
        Map<String, Object> svgMap = new HashMap<>();
        Map<String, List<Map<String, String>>> shapesMap = new HashMap<>();

        Pattern pattern = Pattern.compile("<(.*?) (.*?)/>");
        Matcher matcher = pattern.matcher(svgString);

        while (matcher.find()) {
            String shapeType = matcher.group(1).trim();
            String shapeAttributes = matcher.group(2);

            Map<String, String> shapeMap = new LinkedHashMap<>();
            Map<String, String> tempMap = new HashMap<>();

            Pattern attrPattern = Pattern.compile("(.*?)=\"(.*?)\"");
            Matcher attrMatcher = attrPattern.matcher(shapeAttributes);

            while (attrMatcher.find()) {
                String attrName = attrMatcher.group(1).trim();
                String attrValue = attrMatcher.group(2).trim();
                tempMap.put(attrName, attrValue);
            }

            for (Map.Entry<String, String> entry : tempMap.entrySet()) {
                if (!entry.getKey().equals("stroke") && !entry.getKey().equals("fill") && !entry.getKey().equals("data-index")) {
                    shapeMap.put(entry.getKey(), entry.getValue());
                }
            }

            if (tempMap.containsKey("stroke")) {
                shapeMap.put("stroke", tempMap.get("stroke"));
            }

            if (tempMap.containsKey("fill")) {
                shapeMap.put("fill", tempMap.get("fill"));
            }

            if (tempMap.containsKey("data-index")) {
                shapeMap.put("data-index", tempMap.get("data-index"));
            }

            shapesMap.putIfAbsent(shapeType, new ArrayList<>());
            shapesMap.get(shapeType).add(shapeMap);
        }

        svgMap.put("shapes", shapesMap);
        return gson.toJson(svgMap);
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