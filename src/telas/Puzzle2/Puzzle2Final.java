package telas.Puzzle2;

import componentes.*;
import telas.FrameJanela;
import telas.Puzzle3;
import telas.Puzzle1.Puzzle1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Puzzle2Final extends JPanel implements Puzzle2Interface {
    private JLabel labelFundo;
    private FrameJanela frame;
    private Botao botaoVestidoCosturado; // asset do vestido já costurado
    private Botao botaoCalendario;
    private Botao botaoCalendarioZoom;
    private JLabel labelBarraAvanco;
    private CaixaDialogo caixaPensamento;
    private int indiceDaFala;

    public Puzzle2Final(FrameJanela frame) {
        indiceDaFala = 0;
        this.frame = frame;
        ImageCon imageCon = new ImageCon();
        setLayout(null);
        // barra 2-6
        ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/2-6.png"));
        this.labelBarraAvanco = new JLabel(imgBarra);
        labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());

        ImageIcon fundo = new ImageIcon(getClass().getResource("/assets/QuartRosalngelaSemVestido.png"));

        // vestido já costurado no cabide — troque pelo asset correto
       //ImageIcon imgVestidoCosturado = new ImageIcon(getClass().getResource("/assets/2-6.png"));
        //this.botaoVestidoCosturado = new Botao(imageCon.vestidoRasgadoNoCabide, 1070, 220);
        this.botaoVestidoCosturado = new Botao(imageCon.vestidoRasgadoNoCabide, 1070, 220);
        botaoVestidoCosturado.configuracoesPadrao();

        this.botaoCalendario = new Botao(imageCon.calendario, 150, 270);
        botaoCalendario.configuracoesPadrao();

        this.botaoCalendarioZoom = new Botao(imageCon.calendarioZoom, 0, 0);
        botaoCalendarioZoom.configuracoesPadrao();

        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);
        labelFundo.add(botaoVestidoCosturado.getBotaoClicavel());
        labelFundo.add(labelBarraAvanco);
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        
        //Adicionando caixa de pensamento para fazer a ligação com o puzzle3
        ImageIcon imgCaixaPensamento = new ImageIcon(Puzzle1.class.getResource("/assets/caixaDePensamento.png"));
        this.caixaPensamento = new CaixaDialogo(imgCaixaPensamento, 70, 500, Color.BLACK);
        caixaPensamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        labelFundo.add(caixaPensamento);

        
        //Adicionando uma ação para o vestido costurado
        /*botaoVestidoCosturado.getBotaoClicavel().addActionListener(e -> {
        	frame.trocarTela(new Puzzle3(frame));
        });*/
        botaoCalendario.getBotaoClicavel().addActionListener(e -> darZoomCalendario());
        botaoCalendarioZoom.getBotaoClicavel().addActionListener(e -> tirarZoomCalendario());
       
        // vestido costurado leva para o próximo puzzle
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

     
    }

    @Override
    public void darZoomCalendario() {
        labelFundo.add(botaoCalendarioZoom.getBotaoClicavel());
        labelFundo.remove(botaoVestidoCosturado.getBotaoClicavel());
        labelFundo.remove(botaoCalendario.getBotaoClicavel());
        labelFundo.remove(labelBarraAvanco);
        labelFundo.revalidate();
        labelFundo.repaint();
    }

    @Override
    public void tirarZoomCalendario() {
        labelFundo.remove(botaoCalendarioZoom.getBotaoClicavel());
        labelFundo.add(botaoVestidoCosturado.getBotaoClicavel());
        labelFundo.add(botaoCalendario.getBotaoClicavel());
        labelFundo.add(labelBarraAvanco);
        labelFundo.revalidate();
        labelFundo.repaint();
    }

    @Override
    public void darZoomVestido() { }

    @Override
    public void tirarZoomVestido() { }

    public void passarCena() {
        String[] textoCaixa = {
                "Ficou bem melhor assim!",
                "Agora só falta ligar para minha mãe para saber o endereço!",
        };

        caixaPensamento.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Mudar para que o usuário possa clicar em qq lugar da tela para passar a cena?
//        caixaPensamento.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                frame.trocarTela(new Puzzle3(frame));
//                caixaPensamento.setVisible(false);
//                labelFundo.repaint();
//            }
//        });
        if (indiceDaFala < textoCaixa.length) {
            caixaPensamento.digitarTexto(textoCaixa[indiceDaFala]);
            indiceDaFala++;
        } else {
            caixaPensamento.setVisible(false);
            labelFundo.remove(botaoVestidoCosturado.getBotaoClicavel());
            labelFundo.repaint();
        }

    }
	}
	

	
