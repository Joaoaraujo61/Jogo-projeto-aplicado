package telas;

import javax.swing.*;

import componentes.Botao;

public class MenuInicial extends Jpanel{
    private ImageIcon fundo;
    private ImageIcon iconeComecar;
    private ImageIcon iconeContinuar;
    private ImageIcon nomeJogo;
    private JFrame frame;
    private JLabel labelFundo;
    private JLabel labelNomeJogo;
    private Botao botaoComecar;
    private Botao botaoContinuar;

    public MenuInicial(){
        this.frame = new JFrame("Menu");

        this.iconeComecar = new ImageIcon(MenuInicial.class.getResource("/assets/botaoComecar.png"));
        this.iconeContinuar = new ImageIcon(MenuInicial.class.getResource("/assets/botaoContinuar.png"));
        this.fundo = new ImageIcon(MenuInicial.class.getResource("/assets/fundoMenu.png"));
        this.nomeJogo = new ImageIcon(MenuInicial.class.getResource("/assets/RotaDeFuga.png"));

        this.labelFundo = new JLabel(fundo);
        labelFundo.setLayout(null);

        this.labelNomeJogo = new JLabel(nomeJogo);
        labelNomeJogo.setBounds(260, 200, 759, 84);

        this.botaoComecar = new Botao(iconeComecar,490,380);
        this.botaoContinuar = new Botao(iconeContinuar,472, 504);

        labelFundo.add(labelNomeJogo);
        labelFundo.add(botaoContinuar.getBotaoClicavel());
        labelFundo.add(botaoComecar.getBotaoClicavel());

        frame.setContentPane(labelFundo);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        botaoComecar.getBotaoClicavel().addActionListener(e ->{
            System.out.println("aaaaaaaaaaaaa");
        });
    }
}
