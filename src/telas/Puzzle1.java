package telas;

import javax.swing.*;

public class Puzzle1 extends JPanel {
    public Puzzle1(JFrame frame) {
        setLayout(null);

        JLabel texto = new JLabel("FASE 1");

        texto.setBounds(500, 300, 200, 50);

        add(texto);
    }
}
