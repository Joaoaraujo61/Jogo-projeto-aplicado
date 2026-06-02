package telas;

import javax.swing.*;

public class FrameJanela extends JFrame {

    public FrameJanela(){
        setTitle("Rota de Fuga");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // coloca o menu na janela
        setContentPane(new MenuInicial(this));
        pack(); // ← deixa o frame se ajustar ao tamanho do painel
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void trocarTela(JPanel tela){
        setContentPane(tela);
        revalidate();
        repaint();
    }
}
