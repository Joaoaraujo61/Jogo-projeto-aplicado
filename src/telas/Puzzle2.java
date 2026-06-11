package telas;

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
        ImageIcon fundo = new ImageIcon(Puzzle1.class.getResource("/assets/QuartRosalngela.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        add(labelFundo);
    }
}
