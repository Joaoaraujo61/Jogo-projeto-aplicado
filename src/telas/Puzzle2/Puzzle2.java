package telas.Puzzle2;

import componentes.*;
import telas.FrameJanela;
import telas.Puzzle1.Puzzle1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Puzzle2 extends JPanel{
    private boolean emDialogo;
    private Personagem rosangela;
    private JLabel labelFundo;
    private FrameJanela frame;
    private Botao botaoVestidoRasgado;
    private Botao botaoVestidoRasgadoZoom;
    private Botao botaoCalendario;
    private Botao botaoCalendarioZoom;
    private JLabel labelBarraAvanco;
    private int indiceDaFala;
    private CaixaDialogo caixaPensamento;

    public Puzzle2(FrameJanela frame) {
        this.frame = frame;
        ImageCon imageCon = new ImageCon();
        indiceDaFala = 0;
        this.emDialogo = true;

        ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/1-6.png"));
        this.labelBarraAvanco = new JLabel(imgBarra);
        labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());

        ImageIcon imgCaixaPensamento = new ImageIcon(Puzzle1.class.getResource("/assets/caixaDePensamento.png"));
        this.caixaPensamento = new CaixaDialogo(imgCaixaPensamento, 70, 500, Color.BLACK);
        caixaPensamento.setCursor(new Cursor(Cursor.HAND_CURSOR));


        ImageIcon fundo = new ImageIcon(Puzzle2.class.getResource("/assets/QuartRosalngelaSemVestido.png"));

        this.botaoVestidoRasgado = new Botao(imageCon.vestidoRasgadoNoCabide, 1070, 220);
        botaoVestidoRasgado.configuracoesPadrao();
        botaoVestidoRasgado.getBotaoClicavel().setFocusable(false);

        this.botaoVestidoRasgadoZoom = new Botao(imageCon.vestidoRasgado, 0, 0);
        botaoVestidoRasgadoZoom.configuracoesPadrao();

        this.botaoCalendario = new Botao(imageCon.calendario, 150, 270);
        botaoCalendario.configuracoesPadrao();
        botaoCalendario.getBotaoClicavel().setFocusable(false);

        this.botaoCalendarioZoom = new Botao(imageCon.calendarioZoom, 0, 0);
        botaoCalendarioZoom.configuracoesPadrao();

        this.rosangela = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/RosangelaNormal.png")), -70, 250);


        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);
        labelFundo.add(botaoVestidoRasgado.getBotaoClicavel());
        labelFundo.add(labelBarraAvanco);
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        labelFundo.add(caixaPensamento);

        botaoVestidoRasgado.getBotaoClicavel().setEnabled(false); // bloqueado até o diálogo acabar
        botaoCalendario.getBotaoClicavel().setEnabled(false);

        botaoVestidoRasgado.getBotaoClicavel().addActionListener(e -> {
            frame.trocarTela(new PuzzleCostura(frame));
        });
        botaoCalendario.getBotaoClicavel().addActionListener(e->{
            componentes.GerenciadorMensagem.mostrarMensagem(
                    frame,
                    "Conclua o puzzle 2 para olhar o calendário!",
                    "Instrução",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        MouseAdapter acaoDeClique = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                passarCena();
            }
        };

        this.addMouseListener(acaoDeClique);
        caixaPensamento.addMouseListener(acaoDeClique);

        passarCena();

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("SPACE"), "avancarHistoria"
        );

        this.getActionMap().put("avancarHistoria", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passarCena();
            }
        });


        add(labelFundo);
    }

    public void passarCena() {
        String[] textoCaixa = {
                "Preciso me trocar rápido antes que ele volte e desconfie de algo...",
                "Onde estão minhas roupas boas? Ai não, meu vestido favorito,",
                "o mesmo que ele rasgou naquele dia em que se irritou comigo.",
                "Preciso dar um jeito nisso e sair daqui o quanto antes.",
        };

        caixaPensamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelFundo.add(rosangela.getSprite());
        labelFundo.remove(botaoCalendario.getBotaoClicavel());
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        if (indiceDaFala < textoCaixa.length) {
            caixaPensamento.digitarTexto(textoCaixa[indiceDaFala]);
            indiceDaFala++;
        } else {
            caixaPensamento.setVisible(false);
            emDialogo = false; // diálogo encerrado
            labelFundo.remove(rosangela.getSprite());
            botaoVestidoRasgado.getBotaoClicavel().setEnabled(true); // libera interação
            botaoCalendario.getBotaoClicavel().setEnabled(true);
            labelFundo.repaint();
        }

    }
}