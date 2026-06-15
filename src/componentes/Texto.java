package componentes;

import javax.swing.*;
import java.awt.*;

/*public class Texto {
    private JLabel label;

    //public Texto(JLabel frame, String texto, int fontsize, Color cor){
    public Texto(JLabel frame, String texto, int fontsize, Color cor){
    	//this.fontsize = 12;
        this.label = new JLabel(texto);
       // label.setBounds(500, 20, 1000, 300);
        label.setBounds(400, -20, 3500, 100);
        frame.add(label);
       // label.setFont(new Font("Arial", Font.PLAIN, fontsize));
        label.setFont(new Font("Arial", Font.PLAIN, 2));
        label.setForeground(cor);
        label.setVerticalAlignment(JLabel.TOP);
    }

    public JLabel getLabel() {
        return label;
    }

}*/

public class Texto {
    private JLabel label;

    public Texto(JLabel frame, String texto, int fontsize, Color cor){
        this.label = new JLabel(texto);
        
        // Aumentamos a altura para 300 e descemos o Y para 20 para dar espaço e não cortar!
        label.setBounds(400, 20, 1500, 300);
        
        // Usamos a variável 'fontsize' real que vem lá do jogo
        label.setFont(new Font("Arial", Font.PLAIN, fontsize));
        label.setForeground(cor);
        label.setVerticalAlignment(JLabel.TOP);
        
        frame.add(label);
    }

    public JLabel getLabel() {
        return label;
    }
}
