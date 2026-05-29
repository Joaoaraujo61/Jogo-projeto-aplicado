package telas;

import componentes.Botao;
import componentes.CaixaDialogo;
import componentes.Personagem;

import javax.swing.*;
import java.awt.*;

public class Puzzle1 extends JPanel {
    private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private Botao telefoneBtn;
    private CaixaDialogo caixaDialogo;
    private CaixaDialogo caixaPensamento;

    private int etapa = 0;

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
        this.caixaDialogo = new CaixaDialogo(caixaTelefone, 80, 500);

        //Caiaxa Pensamento
        ImageIcon caixaPensamento = new ImageIcon(MenuInicial.class.getResource("/assets/caixaDePensamento.png"));
        this.caixaPensamento = new CaixaDialogo(caixaPensamento, 80, 500);

        labelFundo.add(telefoneBtn.getBotaoClicavel());

        add(labelFundo);

        tocarTelefone();
    }

    public void avancaCena(Timer timer){
        Personagem rosangela = new Personagem();
        switch (etapa){
            case 0:
                rosangela.atenderTelefone(timer,telefoneBtn, imgTelefone, labelFundo,caixaDialogo);
                etapa++;
                break;
            case 1:
                rosangela.desligarTelefone(labelFundo,caixaDialogo);
                rosangela.pensar(labelFundo, caixaPensamento);
                etapa++;
                break;
        }
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

        telefoneBtn.getBotaoClicavel().addActionListener(e -> avancaCena(timer));
        timer.start();
    }
}

