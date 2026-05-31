package telas;

import componentes.Botao;
import componentes.CaixaDialogo;
import componentes.Personagem;
import componentes.Telefone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        telefone.getTelefoneBtn().getBotaoClicavel().addActionListener(e -> {
            if(etapa < 2){
                avancaCena(timer, telefone);
            }else {
                caixaPensamento.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        avancaCena(timer, telefone);
                    }
                });
            }
        });
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
                rosangela.pensar(labelFundo, caixaPensamento, "Preciso sair de casa, nem que seja pela primeira vez este mês,<br> preciso mesmo visitar a minha mãe, saber dessa oportunidade <br> de emprego, faz tanto tempo que eu não trabalho");
                labelFundo.add(rosangela.getSprite());
                etapa++;
                break;
            case 2:
                rosangela.pensar(labelFundo, caixaPensamento, "Mas como eu vou pedir isso para ele? Ele não vai deixar. <br>Ele não vai me autorizar ir.");
                etapa++;
                break;
            case 3:
                rosangela.pensar(labelFundo, caixaPensamento, "Posso começar pensando no que dizer para ele, quem sabe ...");
                etapa++;
                break;
        }
    }
}

