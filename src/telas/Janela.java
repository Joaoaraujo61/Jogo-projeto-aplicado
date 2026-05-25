package telas;

import javax.swing.*;

public class Janela extends JFrame {

    public Janela(){
        setTitle("Rota de Fuga");
        setSize(1280, 720);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // coloca o menu na janela
        setContentPane(new MenuInicial(this));

        setVisible(true);
    }

    public void trocarTela(JPanel tela){
        setContentPane(tela);
        revalidate();
        repaint();
    }
}
