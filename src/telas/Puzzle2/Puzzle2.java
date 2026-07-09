package telas.Puzzle2;

import componentes.*;
import telas.FrameJanela;

import javax.swing.*;
import java.awt.*;

public class Puzzle2 extends JPanel{
    private JLabel labelFundo;
    private FrameJanela frame;
    private Botao botaoVestidoRasgado;
    private Botao botaoVestidoRasgadoZoom;
    private Botao botaoCalendario;
    private Botao botaoCalendarioZoom;
    private JLabel labelBarraAvanco;

    public Puzzle2(FrameJanela frame) {
        this.frame = frame;
        ImageCon imageCon = new ImageCon();

        ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/1-6.png"));
        this.labelBarraAvanco = new JLabel(imgBarra);
        labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());

        ImageIcon fundo = new ImageIcon(Puzzle2.class.getResource("/assets/QuartRosalngelaSemVestido.png"));

        this.botaoVestidoRasgado = new Botao(imageCon.vestidoRasgadoNoCabide, 1070, 220);
        botaoVestidoRasgado.configuracoesPadrao();

        this.botaoVestidoRasgadoZoom = new Botao(imageCon.vestidoRasgado, 0, 0);
        botaoVestidoRasgadoZoom.configuracoesPadrao();

        this.botaoCalendario = new Botao(imageCon.calendario, 150, 270);
        botaoCalendario.configuracoesPadrao();

        this.botaoCalendarioZoom = new Botao(imageCon.calendarioZoom, 0, 0);
        botaoCalendarioZoom.configuracoesPadrao();

        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);
        labelFundo.add(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.add(labelBarraAvanco);
        labelFundo.add(botaoCalendario.getBotaoClicavel());

        botaoVestidoRasgado.getBotaoClicavel().addActionListener(e -> {
            frame.trocarTela(new PuzzleCostura(frame));
        });
        botaoCalendario.getBotaoClicavel().addActionListener(e->{
            JOptionPane.showMessageDialog(
                    frame,
                    "Conclua o puzzle 2 para olhar o calendário!",
                    "Instrução",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        add(labelFundo);
    }
}