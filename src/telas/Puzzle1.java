package telas;

import componentes.Botao;
import componentes.CaixaDialogo;

import javax.swing.*;
import java.awt.*;

public class Puzzle1 extends JPanel {
    private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private Botao telefoneBtn;
    private JLabel labelCaixaTelefone;
    private CaixaDialogo caixaDialogo;

    public Puzzle1(JFrame frame) {
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        //Fundo
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        //Imagem Telefone
        this.imgTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/telefone.png"));
        this.telefoneBtn = new Botao(imgTelefone,1070,200);
        JButton botao = telefoneBtn.getBotaoClicavel();
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        this.imgTelefoneTocando = new ImageIcon(MenuInicial.class.getResource("/assets/telefoneTocando.png"));

        //Caixa Telefone
        ImageIcon caixaTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/DialogoTelefone.png"));
        this.labelCaixaTelefone = new JLabel(caixaTelefone);
        this.caixaDialogo = new CaixaDialogo(caixaTelefone, 80, 500);

        labelFundo.add(telefoneBtn.getBotaoClicavel());

        add(labelFundo);

        tocarTelefone();
    }

    public void tocarTelefone(){
        Timer timer = new Timer(500, e ->{
                JButton botao = telefoneBtn.getBotaoClicavel();
                Icon iconeAtual = botao.getIcon();
                if (iconeAtual == imgTelefone) {
                    botao.setIcon(imgTelefoneTocando);
                } else {
                    botao.setIcon(imgTelefone);
                }
        });

        telefoneBtn.getBotaoClicavel().addActionListener(e -> atenderTelefone(timer));
        timer.start();
    }

    public void atenderTelefone(Timer timer) {
        timer.stop();
        telefoneBtn.getBotaoClicavel().setIcon(imgTelefone);

        labelFundo.add(caixaDialogo);
        labelFundo.setComponentZOrder(caixaDialogo, 0);
        labelFundo.revalidate();
        labelFundo.repaint();

        caixaDialogo.digitarTexto("Filha, encontrei uma vaga de emprego na sua área, <br>vem aqui para a minha casa para conversarmos sobre <br>isso, faz muito tempo que você não me visita..."); // ← seu texto
    }
}

