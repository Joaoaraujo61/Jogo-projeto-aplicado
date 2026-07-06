package telas;

import javax.swing.*;

public class FrameJanela extends JFrame {

    public FrameJanela(){
        setTitle("Rota de Fuga");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setContentPane(new MenuInicial(this));
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        }

    public void trocarTela(JPanel tela){
        setContentPane(tela);
        revalidate();
        repaint();
    }
}
