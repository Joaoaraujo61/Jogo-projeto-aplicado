
import telas.FrameJanela;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 20));
        SwingUtilities.invokeLater(() -> {
            new FrameJanela();
        });
    }
}