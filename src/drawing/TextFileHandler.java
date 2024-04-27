package drawing;
import javax.swing.*;
import java.io.*;

public class TextFileHandler {

    public static String loadTextFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e);
        }
        return content.toString();
    }

    // Middle-ware function to save text to file
    public static void saveTextToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e);
        }
    }

    // Load text file to SVG and DrawingPanel, DrawingPanel will also write values into table
    public static void loadTextFileToSvg(DrawingPanel drawingPanel, JFileChooser fileChooser) {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String content = loadTextFile(selectedFile.getPath());
            SvgOutput.setText(content, "file");
            drawingPanel.loadFromSvg(content);
        }
    }

    // Save text from SVG to file
    public static void saveTextFileFromSvg(JFileChooser fileChooser) {
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String content = SvgOutput.getOutput();
            saveTextToFile(selectedFile.getPath(), content);
        }
    }
}