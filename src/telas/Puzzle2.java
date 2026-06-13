package telas;

import componentes.Botao;
import componentes.CaixaDialogo;
import componentes.Personagem;
import componentes.Telefone;
import telas.Puzzle1.Puzzle1;

import javax.swing.*;
import java.awt.*;

public class Puzzle2 extends JPanel {
    private JLabel labelFundo;
    private FrameJanela frame;

    public Puzzle2(FrameJanela frame) {
        this.frame = frame;
        //Fundo
        ImageIcon fundo = new ImageIcon(Puzzle2.class.getResource("/assets/QuartRosalngelaSemVestido.png"));
        ImageIcon vestidoRasgado = new ImageIcon(Puzzle2.class.getResource("/assets/VestidoRasgadoNoCabide.png"));
        Botao botaoVestidoRasgado = new Botao(vestidoRasgado,1070, 300);
        botaoVestidoRasgado.getBotaoClicavel().setBorderPainted(false);
        botaoVestidoRasgado.getBotaoClicavel().setContentAreaFilled(false);
        botaoVestidoRasgado.getBotaoClicavel().setFocusPainted(false);

        this.labelFundo = new JLabel(fundo);
        labelFundo.add(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        add(labelFundo);
    }
}
