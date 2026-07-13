package telas;

import componentes.BotaoVolume;
import componentes.GerenciadorSom;

import javax.swing.*;

public class FrameJanela extends JFrame {

    // Guardamos a referência para não precisar recriar o botão a cada troca de tela
    private BotaoVolume botaoVolume;

    public FrameJanela(){
        setTitle("Rota de Fuga");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(new MenuInicial(this));
        GerenciadorSom.tocarTrilha("/assets/sons/trilha-fundo.wav",0.35f);

        pack();

        // Botão de volume no layeredPane: fica acima de qualquer tela e
        // NÃO é removido quando trocarTela() troca o contentPane.
        this.botaoVolume = new BotaoVolume();
        botaoVolume.getBotaoSom().setFocusable(false);
        getLayeredPane().add(botaoVolume.getBotaoSom(), JLayeredPane.PALETTE_LAYER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void trocarTela(JPanel tela){
        setContentPane(tela);
        revalidate();
        repaint();
    }
}