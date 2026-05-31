package componentes;

import telas.MenuInicial;

import javax.swing.*;

public class Personagem {
    private JLabel sprite;

    public Personagem(){
        ImageIcon imgRosangela = new ImageIcon(Personagem.class.getResource("/assets/RosangelaNormal.png"));
        sprite = new JLabel(imgRosangela);

        sprite.setBounds(
                -70,
                250,
                imgRosangela.getIconWidth(),
                imgRosangela.getIconHeight()
        );
    }

    public void pensar(JLabel labelFundo,CaixaDialogo caixaPensamento, String textoPensamento){
        labelFundo.add(caixaPensamento);
        labelFundo.setComponentZOrder(caixaPensamento, 0);
        labelFundo.revalidate();
        labelFundo.repaint();

        caixaPensamento.digitarTexto(textoPensamento);

    }
    public void pararPensamento(JLabel labelFundo,CaixaDialogo caixaPensamento){
        labelFundo.remove(caixaPensamento);
        labelFundo.revalidate();
        labelFundo.repaint();
    }
    public void atenderTelefone(Timer timer, Botao telefoneBtn, ImageIcon imgTelefone, JLabel labelFundo,CaixaDialogo caixaDialogo) {
        timer.stop();
        telefoneBtn.getBotaoClicavel().setIcon(imgTelefone);

        labelFundo.add(caixaDialogo);
        labelFundo.setComponentZOrder(caixaDialogo, 0);
        labelFundo.revalidate();
        labelFundo.repaint();

        caixaDialogo.digitarTexto("Filha, encontrei uma vaga de emprego na sua área, <br>vem aqui para a minha casa para conversarmos sobre <br>isso, faz muito tempo que você não me visita...");
    }

    public void desligarTelefone(JLabel labelFundo,CaixaDialogo caixaDialogo){
        labelFundo.remove(caixaDialogo);
        labelFundo.revalidate();
        labelFundo.repaint();
    }

    public JLabel getSprite() {
        return sprite;
    }
}
