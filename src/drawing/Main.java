package drawing;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            try {
                new drawing.MainFrame();
            } catch (Exception e) {
                System.out.println("GUI ERROR: "+e);
            }
        });
    }

}