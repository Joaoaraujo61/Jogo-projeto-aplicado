package telas;

import componentes.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Puzzle2 extends JPanel {
    private JLabel labelFundo;
    private FrameJanela frame;
    private Botao botaoVestidoRasgado;
    private Botao botaoVestidoRasgadoZoom;
    private Botao botaoCalendario;
    private Botao botaoCalendarioZoom;
    private JLabel labelBarraAvanco;

    public Puzzle2(FrameJanela frame){
        this.frame = frame;
        ImageCon imageCon = new ImageCon();

        ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/1-6.png"));
        this.labelBarraAvanco = new JLabel(imgBarra);
        labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());
        labelBarraAvanco.setLayout(null);
        //Fundo
        ImageIcon fundo = new ImageIcon(Puzzle2.class.getResource("/assets/QuartRosalngelaSemVestido.png"));

        this.botaoVestidoRasgado = new Botao(imageCon.vestidoRasgadoNoCabide, 1070, 220);
        botaoVestidoRasgado.configuracoesPadrao();

        this.botaoVestidoRasgadoZoom = new Botao(imageCon.vestidoRasgado, 0, 0);
        botaoVestidoRasgadoZoom.configuracoesPadrao();

        this.botaoCalendario = new Botao(imageCon.calendario, 150, 270);
        botaoCalendario.configuracoesPadrao();

        this.botaoCalendarioZoom = new Botao(imageCon.calendarioZoom, 0, 0);
        botaoCalendarioZoom.configuracoesPadrao();

        botaoCalendario.getBotaoClicavel().addActionListener(e -> darZoomCalendario());

        botaoCalendarioZoom.getBotaoClicavel().addActionListener(e -> tirarZoomCalendario());


        botaoVestidoRasgado.getBotaoClicavel().addActionListener(e -> {
            darZoomVestido();
            if (imageCon.agulha != null) {
                Cursor cursorAgulha = Toolkit.getDefaultToolkit()
                        .createCustomCursor(imageCon.agulha, new Point(0, 0), "agulha");

                setCursor(cursorAgulha);
                labelFundo.setCursor(cursorAgulha);
            }
        });

        //botaoVestidoRasgadoZoom.getBotaoClicavel().addActionListener(e -> tirarZoomVestido());

        this.labelFundo = new JLabel(fundo);
        labelFundo.add(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.add(labelBarraAvanco);
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        add(labelFundo);
    }

    public void darZoomCalendario(){
        labelFundo.add(botaoCalendarioZoom.getBotaoClicavel());
        labelFundo.remove(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.remove(botaoCalendario.getBotaoClicavel());
        labelFundo.remove(labelBarraAvanco);

        labelFundo.revalidate();
        labelFundo.repaint();
    }

    public void tirarZoomCalendario(){
        labelFundo.remove(botaoCalendarioZoom.getBotaoClicavel());
        labelFundo.add(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        labelFundo.add(labelBarraAvanco);

        labelFundo.revalidate();
        labelFundo.repaint();
    }

    public void darZoomVestido(){
        labelFundo.remove(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.remove(botaoCalendario.getBotaoClicavel());
        labelFundo.add(botaoVestidoRasgadoZoom.getBotaoClicavel());
        labelFundo.remove(labelBarraAvanco);

        labelFundo.revalidate();
        labelFundo.repaint();
    }

    public void tirarZoomVestido(){
        labelFundo.remove(botaoVestidoRasgadoZoom.getBotaoClicavel());
        labelFundo.add(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        labelFundo.add(labelBarraAvanco);

        labelFundo.revalidate();
        labelFundo.repaint();
    }
}
