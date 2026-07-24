package telas.Puzzle2;

import componentes.*;
import telas.FrameJanela;
import telas.Puzzle3;
import telas.Puzzle3Jogavel;
import telas.Puzzle5;
import telas.Puzzle1.Puzzle1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Puzzle2Final extends JPanel implements Puzzle2Interface {
    private JLabel labelFundo;
    private FrameJanela frame;
    private Botao botaoCalendario;
    private Botao botaoCalendarioZoom;
    private Botao botaoSeta;
    private Botao botaoSetaPuzzle5;
    private JLabel labelBarraAvanco;
    private JLabel rosangelaVestidoCinza;
    private CaixaDialogo caixaPensamento;
    private int indiceDaFala;

    private boolean calendarioJaAberto = false;

    private boolean veioDoPuzzle5;

    public Puzzle2Final(FrameJanela frame) {
        this(frame, false);
    }

    
    public Puzzle2Final(FrameJanela frame, boolean veioDoPuzzle5) {
        indiceDaFala = 0;
        this.frame = frame;
        this.veioDoPuzzle5 = veioDoPuzzle5;
        ImageCon imageCon = new ImageCon();
        setLayout(null);
        // barra 2-6
        ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/2-6.png"));
        this.labelBarraAvanco = new JLabel(imgBarra);
        labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());

        ImageIcon fundo = new ImageIcon(getClass().getResource("/assets/QuartRosalngelaSemVestido.png"));


        this.botaoCalendario = new Botao(imageCon.calendario, 150, 270);
        botaoCalendario.configuracoesPadrao();

        this.botaoCalendarioZoom = new Botao(imageCon.calendarioZoom, 0, 0);
        botaoCalendarioZoom.configuracoesPadrao();

        this.botaoSeta = new Botao(imageCon.seta, 1130, 600);
        botaoSeta.configuracoesPadrao();
        botaoSeta.getBotaoClicavel().addActionListener(e -> frame.trocarTela(new Puzzle3(frame)));

        this.botaoSetaPuzzle5 = new Botao(imageCon.seta, 30, 600);
        botaoSetaPuzzle5.configuracoesPadrao();
        botaoSetaPuzzle5.getBotaoClicavel().addActionListener(e -> frame.trocarTela(new Puzzle5(frame, true)));

        this.rosangelaVestidoCinza = new JLabel(imageCon.rosangelaVestidoNovo);
        rosangelaVestidoCinza.setBounds(-70, 250, imageCon.rosangelaVestidoNovo.getIconWidth(), imageCon.rosangelaVestidoNovo.getIconHeight());

        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);
        labelFundo.add(labelBarraAvanco);
        labelFundo.add(botaoCalendario.getBotaoClicavel());

        if (veioDoPuzzle5) {
            labelFundo.add(botaoSetaPuzzle5.getBotaoClicavel());
        }

        ImageIcon imgCaixaPensamento = new ImageIcon(Puzzle1.class.getResource("/assets/caixaDePensamento.png"));
        this.caixaPensamento = new CaixaDialogo(imgCaixaPensamento, 70, 500, Color.BLACK);
        caixaPensamento.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (!veioDoPuzzle5) {
            labelFundo.add(caixaPensamento);
            labelFundo.add(rosangelaVestidoCinza);
        }



        botaoCalendario.getBotaoClicavel().addActionListener(e -> darZoomCalendario());
        botaoCalendarioZoom.getBotaoClicavel().addActionListener(e -> tirarZoomCalendario());

        add(labelFundo);
        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1);

        MouseAdapter acaoDeClique = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passarCena();
            }
        };

        this.addMouseListener(acaoDeClique);
        caixaPensamento.addMouseListener(acaoDeClique);

        if (!veioDoPuzzle5) {
            passarCena();
        }

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("SPACE"), "avancarHistoria"
        );

        this.getActionMap().put("avancarHistoria", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passarCena();
            }
        });


    }

    @Override
    public void darZoomCalendario() {
        calendarioJaAberto = true; 

        labelFundo.add(botaoCalendarioZoom.getBotaoClicavel());
        labelFundo.remove(botaoCalendario.getBotaoClicavel());
        labelFundo.remove(labelBarraAvanco);
        labelFundo.remove(botaoSeta.getBotaoClicavel());
        labelFundo.remove(botaoSetaPuzzle5.getBotaoClicavel());
        labelFundo.revalidate();
        labelFundo.repaint();
    }

    @Override
    public void tirarZoomCalendario() {
        labelFundo.remove(botaoCalendarioZoom.getBotaoClicavel());
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        labelFundo.add(labelBarraAvanco);

        if (calendarioJaAberto && !veioDoPuzzle5) {
            labelFundo.add(botaoSeta.getBotaoClicavel());
        }

        if (veioDoPuzzle5) {
            labelFundo.add(botaoSetaPuzzle5.getBotaoClicavel());
        }

        labelFundo.revalidate();
        labelFundo.repaint();
    }

    @Override
    public void darZoomVestido() { }

    @Override
    public void tirarZoomVestido() { }

    public void passarCena() {
        if (veioDoPuzzle5) {
            return;
        }

        String[] textoCaixa = {
                "Ficou bem melhor assim!",
                "Agora só falta ligar para minha mãe para saber o endereço!",
        };

        caixaPensamento.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (indiceDaFala < textoCaixa.length) {
            caixaPensamento.digitarTexto(textoCaixa[indiceDaFala]);
            indiceDaFala++;
        } else {
            frame.trocarTela(new Puzzle3(frame));
        }

    }
}