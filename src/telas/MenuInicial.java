package telas;

import javax.swing.*;
import componentes.Botao;
import java.awt.*;

public class MenuInicial extends JPanel{
    private ImageIcon fundo;

    public MenuInicial(FrameJanela frame){
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        ImageIcon iconeComecar = new ImageIcon(MenuInicial.class.getResource("/assets/botaoComecar.png"));
        this.fundo = new ImageIcon(MenuInicial.class.getResource("/assets/fundoMenu.png"));
        ImageIcon nomeJogo = new ImageIcon(MenuInicial.class.getResource("/assets/RotaDeFuga.png"));

        //Fundo
        JLabel labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        //Nome Jogo
        JLabel labelNomeJogo = new JLabel(nomeJogo);
        labelNomeJogo.setBounds(260, 200, 759, 84);

        //Botoes
        Botao botaoComecar = new Botao(iconeComecar, 490, 380);

        //
        //Adiciona elementos
        labelFundo.add(labelNomeJogo);
        labelFundo.add(botaoComecar.getBotaoClicavel());

        add(labelFundo);

        botaoComecar.getBotaoClicavel().addActionListener(e ->{
        	frame.trocarTela(new Puzzle6(frame));
            //frame.trocarTela(new CenaInicial(frame));
        });

    }
}
