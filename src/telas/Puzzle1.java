package telas;

import componentes.Botao;
import componentes.CaixaDialogo;
import componentes.Personagem;
import componentes.Telefone;

import javax.swing.*;
import java.awt.*;

public class Puzzle1 extends JPanel {
    private JLabel labelFundo;
    private CaixaDialogo caixaDialogo;
    private CaixaDialogo caixaPensamento;
    private  Personagem rosangela;

    private int etapa = 0;

    public Puzzle1(JFrame frame) {
        this.rosangela = new Personagem();
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        //Fundo
        ImageIcon fundo = new ImageIcon(Puzzle1.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        //Imagem Telefone
        Telefone telefone = new Telefone();

        //Caixa Telefone
        ImageIcon imgCaixaTelefone = new ImageIcon(Puzzle1.class.getResource("/assets/DialogoTelefone.png"));
        this.caixaDialogo = new CaixaDialogo(imgCaixaTelefone, 80, 500, Color.white);

        //Caiaxa Pensamento
        ImageIcon imgCaixaPensamento = new ImageIcon(Puzzle1.class.getResource("/assets/caixaDePensamento.png"));
        this.caixaPensamento = new CaixaDialogo(imgCaixaPensamento, 70, 500, Color.BLACK);


        labelFundo.add(telefone.getTelefoneBtn().getBotaoClicavel());

        add(labelFundo);

        tocarTelefone(telefone);
    }

    public void tocarTelefone(Telefone telefone){
        Timer timer = new Timer(500, e ->{
            JButton botao = telefone.getTelefoneBtn().getBotaoClicavel();
            Icon iconeAtual = botao.getIcon();
            if (iconeAtual == telefone.getImgTelefone()) {
                botao.setIcon(telefone.getImgTelefoneTocando());
            } else {
                botao.setIcon(telefone.getImgTelefone());
            }
        });

        telefone.getTelefoneBtn().getBotaoClicavel().addActionListener(e -> avancaCena(timer, telefone));
        timer.start();
    }


    public void avancaCena(Timer timer,Telefone telefone){
        switch (etapa){
            case 0:
                rosangela.atenderTelefone(timer,telefone.getTelefoneBtn(), telefone.getImgTelefone(), labelFundo,caixaDialogo);
                etapa++;
                break;
            case 1:
                rosangela.desligarTelefone(labelFundo,caixaDialogo);
                rosangela.pensar(labelFundo, caixaPensamento);
                labelFundo.add(rosangela.getSprite());
                etapa++;
                break;
        }
    }
}

