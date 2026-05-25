package telas;

import componentes.Botao;

import javax.swing.*;

public class Puzzle1 extends JPanel {
    private ImageIcon fundo;
    private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private Botao telefoneBtn;
    private Botao telefoneTocandoBtn;

    public Puzzle1(JFrame frame) {
        //Fundo
        this.fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        this.imgTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/telefone.png"));
        this.telefoneBtn = new Botao(imgTelefone,970,200);

        this.imgTelefoneTocando = new ImageIcon(MenuInicial.class.getResource("/assets/telefoneTocando.png"));
        this.telefoneTocandoBtn = new Botao(imgTelefone,970,200);

        labelFundo.add(telefoneBtn.getBotaoClicavel());
        add(labelFundo);
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
        timer.start();
    }
}
