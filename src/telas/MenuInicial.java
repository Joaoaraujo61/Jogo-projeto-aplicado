package telas;

import javax.swing.*;

import componentes.Botao;

public class MenuInicial extends JPanel{
    private ImageIcon fundo;
    private ImageIcon iconeComecar;
    private ImageIcon iconeContinuar;
    private ImageIcon nomeJogo;
    private Janela frame;
    private JLabel labelFundo;
    private JLabel labelNomeJogo;
    private Botao botaoComecar;
    private Botao botaoContinuar;

    public MenuInicial(Janela frame){
        this.frame = frame;

        this.iconeComecar = new ImageIcon(MenuInicial.class.getResource("/assets/botaoComecar.png"));
        this.iconeContinuar = new ImageIcon(MenuInicial.class.getResource("/assets/botaoContinuar.png"));
        this.fundo = new ImageIcon(MenuInicial.class.getResource("/assets/fundoMenu.png"));
        this.nomeJogo = new ImageIcon(MenuInicial.class.getResource("/assets/RotaDeFuga.png"));

        //Fundo
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        //Nome Jogo
        this.labelNomeJogo = new JLabel(nomeJogo);
        labelNomeJogo.setBounds(260, 200, 759, 84);

        //Botoes
        this.botaoComecar = new Botao(iconeComecar,490,380);
        this.botaoContinuar = new Botao(iconeContinuar,472, 504);

        //
        //Adiciona elementos
        labelFundo.add(labelNomeJogo);
        labelFundo.add(botaoContinuar.getBotaoClicavel());
        labelFundo.add(botaoComecar.getBotaoClicavel());

        add(labelFundo);

        botaoComecar.getBotaoClicavel().addActionListener(e ->{
            frame.trocarTela(new Puzzle1(frame));
        });
    }
}
