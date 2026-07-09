package componentes;

import telas.MenuInicial;

import javax.swing.*;

public class Personagem {
    private JLabel sprite;

    public Personagem(ImageIcon imgPersonagem, int x, int y){
        sprite = new JLabel(imgPersonagem);

        sprite.setBounds(
                x, y,
                imgPersonagem.getIconWidth(),
                imgPersonagem.getIconHeight()
        );
    }

    public void escreverDialogo(JLabel labelFundo, CaixaDialogo caixa, String texto){
        labelFundo.add(caixa);
        labelFundo.setComponentZOrder(caixa, 0);
        labelFundo.revalidate();
        labelFundo.repaint();

        caixa.digitarTexto(texto);

    }
    public void atenderTelefone(Timer timer, Botao telefoneBtn, ImageIcon imgTelefone, JLabel labelFundo,CaixaDialogo caixaDialogo) {
        timer.stop();
        telefoneBtn.getBotaoClicavel().setIcon(imgTelefone);

        labelFundo.add(caixaDialogo);
        labelFundo.setComponentZOrder(caixaDialogo, 0);
        labelFundo.revalidate();
        labelFundo.repaint();

        caixaDialogo.digitarTexto("Filha, encontrei uma vaga de emprego na sua área, <br>vem aqui para a minha casa para conversarmos sobre <br>isso, faz muito tempo que você não me visita...");
       // labelFundo.add(odete.getSprite());
    }

    public void desligarTelefone(JLabel labelFundo,CaixaDialogo caixaDialogo, Personagem odete){
        labelFundo.remove(odete.getSprite());
        labelFundo.remove(caixaDialogo);
        labelFundo.revalidate();
        labelFundo.repaint();
    }

    public JLabel getSprite() {
        return sprite;
    }
}
