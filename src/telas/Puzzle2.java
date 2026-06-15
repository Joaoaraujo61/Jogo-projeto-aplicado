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
        Botao botaoVestidoRasgado = new Botao(vestidoRasgado,1070, 220);
        botaoVestidoRasgado.getBotaoClicavel().setBorderPainted(false);
        botaoVestidoRasgado.getBotaoClicavel().setContentAreaFilled(false);
        botaoVestidoRasgado.getBotaoClicavel().setFocusPainted(false);

        ImageIcon calendario = new ImageIcon(Puzzle2.class.getResource("/assets/calendario.png"));
        Botao botaoCalendario = new Botao(calendario,150, 270);
        botaoCalendario.getBotaoClicavel().setBorderPainted(false);
        botaoCalendario.getBotaoClicavel().setContentAreaFilled(false);
        botaoCalendario.getBotaoClicavel().setFocusPainted(false);

        ImageIcon calendarioZoom = new ImageIcon(Puzzle2.class.getResource("/assets/CalendarioZoom.png"));
        Botao botaoCalendarioZoom = new Botao(calendarioZoom,0, 0);
        botaoCalendarioZoom.getBotaoClicavel().setBorderPainted(false);
        botaoCalendarioZoom.getBotaoClicavel().setContentAreaFilled(false);
        botaoCalendarioZoom.getBotaoClicavel().setFocusPainted(false);

        botaoCalendario.getBotaoClicavel().addActionListener(e -> {
            labelFundo.add(botaoCalendarioZoom.getBotaoClicavel());
            labelFundo.remove(botaoVestidoRasgado.getBotaoClicavel());
            labelFundo.remove(botaoCalendario.getBotaoClicavel());
            labelFundo.setBounds(0, 0, 1280, 720);
            labelFundo.setLayout(null);
        });

        botaoCalendarioZoom.getBotaoClicavel().addActionListener(e -> {
            labelFundo.remove(botaoCalendarioZoom.getBotaoClicavel());
            labelFundo.add(botaoVestidoRasgado.getBotaoClicavel());
            labelFundo.add(botaoCalendario.getBotaoClicavel());
            labelFundo.setBounds(0, 0, 1280, 720);
            labelFundo.setLayout(null);
        });

        this.labelFundo = new JLabel(fundo);
        labelFundo.add(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        add(labelFundo);
    }
}
