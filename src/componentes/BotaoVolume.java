package componentes;

import javax.swing.*;

public class BotaoVolume {
    private Botao botaoSom;
    private ImageIcon somIcone;
    private ImageIcon iconeSomOn;
    private ImageIcon iconeSomOff;

    public BotaoVolume(){
        this.iconeSomOn = new ImageIcon(getClass().getResource("/assets/sound-on.png"));
        this.iconeSomOff = new ImageIcon(getClass().getResource("/assets/sound-off.png"));

        this.somIcone = iconeSomOn;
        this.botaoSom = new Botao(somIcone, 30, 40);

        botaoSom.getBotaoClicavel().addActionListener(e -> {
            JButton botao = botaoSom.getBotaoClicavel();

            if (GerenciadorSom.getVolumeAtual() > 0){
                GerenciadorSom.ajustarVolumeTrilhaAtual(0);
                somIcone = iconeSomOff;
            } else {
                GerenciadorSom.ajustarVolumeTrilhaAtual(0.35f);
                somIcone = iconeSomOn;
            }

            botao.setIcon(somIcone); // <-- isso é o que faltava
        });
    }

    public JButton getBotaoSom() {
        return botaoSom.getBotaoClicavel();
    }
}