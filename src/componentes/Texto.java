package componentes;

import javax.swing.*;
import java.awt.*;


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

    
  
}
