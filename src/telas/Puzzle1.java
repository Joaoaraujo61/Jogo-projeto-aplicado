package telas;

import componentes.Botao;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class Puzzle1 extends JPanel {
    private ImageIcon fundo;
    private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private Botao telefoneBtn;
    private ImageIcon caixaTefone;
    private JLabel labelCaixaTelefone;

    public Puzzle1(JFrame frame) {
        //Fundo
        this.fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        //Imagem Telefone
        this.imgTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/telefone.png"));
        this.telefoneBtn = new Botao(imgTelefone,970,200);
        this.imgTelefoneTocando = new ImageIcon(MenuInicial.class.getResource("/assets/telefoneTocando.png"));

        //Caixa Telefone
        this.caixaTefone = new ImageIcon(MenuInicial.class.getResource("/assets/DialogoTelefone.png"));
        this.labelCaixaTelefone = new JLabel(caixaTefone);
        labelCaixaTelefone.setBounds(20, 200, caixaTefone.getIconWidth(), caixaTefone.getIconHeight());

        labelFundo.add(telefoneBtn.getBotaoClicavel());

        add(labelFundo);add(labelFundo);

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
        telefoneBtn.getBotaoClicavel().addActionListener(e -> {
            timer.stop();
            telefoneBtn.getBotaoClicavel().setIcon(imgTelefone);

            labelFundo.add(labelCaixaTelefone);
            labelFundo.setComponentZOrder(labelCaixaTelefone, 0);
            revalidate();
            repaint();
        });

        timer.start();
    }
}
