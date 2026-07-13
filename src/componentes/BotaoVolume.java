package componentes;

import javax.swing.*;

public class BotaoVolume {
    private Botao botaoSom;

    public BotaoVolume(){
        ImageIcon somIcone = new ImageIcon(getClass().getResource("/assets/mute.png"));
        this.botaoSom = new Botao(somIcone, 30, 40);

        botaoSom.getBotaoClicavel().addActionListener(e->{
            if (GerenciadorSom.getVolumeAtual() > 0){
                GerenciadorSom.ajustarVolumeTrilhaAtual(0);
            }else{
                GerenciadorSom.ajustarVolumeTrilhaAtual(0.35f);
            }

        });
    }
    public JButton getBotaoSom() {
        return botaoSom.getBotaoClicavel();
    }

}
