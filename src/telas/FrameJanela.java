package telas;

import componentes.GerenciadorSom;

import javax.swing.*;

public class FrameJanela extends JFrame {

    public FrameJanela(){
        setTitle("Rota de Fuga");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setContentPane(new MenuInicial(this));
        GerenciadorSom.tocarTrilha("/assets/sons/trilha-fundo.wav",0.35f);
        
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
