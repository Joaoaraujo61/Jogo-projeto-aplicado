package componentes;

import javax.swing.*;
import java.awt.*;

public class Texto {
    private JLabel label;

    public Texto(JLabel frame, String texto, int fontsize, Color cor){
        this.label = new JLabel(texto);
        label.setBounds(500, 20, 1000, 300);
        frame.add(label);
        label.setFont(new Font("Arial", Font.PLAIN, fontsize));
        label.setForeground(cor);
        label.setVerticalAlignment(JLabel.TOP);
    }

    public JLabel getLabel() {
        return label;
    }

}
