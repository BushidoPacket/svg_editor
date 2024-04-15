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

    public static void console(String input){
        System.out.println(input);
    }
}