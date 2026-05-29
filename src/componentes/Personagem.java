package componentes;

import telas.MenuInicial;

import javax.swing.*;

public class Personagem {

    public void pensar(JLabel labelFundo,CaixaDialogo caixaPensamento){
        labelFundo.add(caixaPensamento);
        labelFundo.setComponentZOrder(caixaPensamento, 0);
        labelFundo.revalidate();
        labelFundo.repaint();

        caixaPensamento.digitarTexto("Filha, encontrei uma vaga de emprego na sua área, <br>vem aqui para a minha casa para conversarmos sobre <br>isso, faz muito tempo que você não me visita...");
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
}
