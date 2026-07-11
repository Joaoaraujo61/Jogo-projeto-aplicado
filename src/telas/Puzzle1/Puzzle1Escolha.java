package telas.Puzzle1;

import componentes.CaixaDialogo;
import componentes.Personagem;
import telas.FrameJanela;
import telas.Puzzle2.Puzzle2;

import javax.swing.*;
import java.awt.*;

public class Puzzle1Escolha extends JPanel {

    private final JLabel labelTituloPuzzle1;
    private FrameJanela frame;
    private JLabel labelFundo;

    private JButton opcao1;
    private JButton opcao2;
    private JButton opcao3;
    private JButton opcao4;

    private JLabel spriteJorjao;
    private CaixaDialogo caixaDialogoJorjao;
    private Personagem jorjao;

    public Puzzle1Escolha(FrameJanela frame) {
        this.frame = frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        ImageIcon fundo = new ImageIcon(
                Puzzle1Escolha.class.getResource("/assets/salaDeEstar-pixilart.png")
        );

        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        ImageIcon imgPuzzle1 = new ImageIcon(getClass().getResource("/assets/Puzzle_1.png"));
        this.labelTituloPuzzle1 = new JLabel(imgPuzzle1);
        labelTituloPuzzle1.setBounds(270, 30, imgPuzzle1.getIconWidth(), imgPuzzle1.getIconHeight());

        this.jorjao = new Personagem(
                new ImageIcon(Puzzle1Escolha.class.getResource("/assets/JorjaoNormal.png")),
                70, 180
        );
        this.spriteJorjao = jorjao.getSprite();
        spriteJorjao.setVisible(false);



        ImageIcon imgDialogo = new ImageIcon(
                Puzzle1Escolha.class.getResource("/assets/DialogoJorjao.png")
        );

        caixaDialogoJorjao = new CaixaDialogo(
                imgDialogo,
                0,
                500,
                Color.BLACK
        );
        caixaDialogoJorjao.setVisible(false);

        add(labelFundo);
        criarOpcoes();
        labelFundo.add(labelTituloPuzzle1);
    }

    private void criarOpcoes() {
        opcao1 = criarBotao(
                "<html><center>Amor, preciso ver minha mãe hoje,<br>posso sair depois de terminar de organizar a cozinha?</center></html>",
                120
        );
        opcao2 = criarBotao(
                "<html><center>Meu bem, preciso sair um pouco de casa,<br>não aguento mais ficar trancada</center></html>",
                230
        );
        opcao3 = criarBotao(
                "<html><center>Jorjão, tenho uma consulta na UBS hoje,<br>então tenho que sair à tarde</center></html>",
                340
        );
        opcao4 = criarBotao(
                "<html><center>Vou sair hoje, depois do almoço</center></html>",
                450
        );

        labelFundo.add(opcao1);
        labelFundo.add(opcao2);
        labelFundo.add(opcao3);
        labelFundo.add(opcao4);

        configurarEventos();
    }

    private JButton criarBotao(String texto, int y) {
        JButton botao = new JButton(texto);
        botao.setBounds(140, y, 700, 80);
        botao.setFont(new Font("Arial", Font.BOLD, 20));
        return botao;
    }

    private void configurarEventos() {
        opcao1.addActionListener(e -> tratarErro(opcao1,
                "Não vou poder te acompanhar na sua mãe hoje<br> e não quero que saia sozinha."));
        opcao2.addActionListener(e -> tratarErro(opcao2,
                "Como assim? Mas a casa está uma bagunça, você <br>não pretende sair e deixar ela desse jeito, né?"));
        opcao4.addActionListener(e -> tratarErro(opcao4,
                "Mas nem pensar! Você nem terminou de passar <br> minhas roupas."));

        opcao3.addActionListener(e -> {
            mostrarResposta("Consulta na UBS? Não me lembro disso. Mas tudo bem,<br> vá, mas troque de roupa antes, não quero que os <br>médicos te vejam assim...");
            desabilitarTodas();

            Timer timer = new Timer(3500, evento -> {
                ((Timer) evento.getSource()).stop();
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(
                            SwingUtilities.getWindowAncestor(Puzzle1Escolha.this),
                            "Puzzle concluído!"
                    );
                    frame.trocarTela(new Puzzle2(frame));
                });
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    private void tratarErro(JButton botao, String fala) {
        mostrarResposta(fala);
        bloquearOpcao(botao);
        desabilitarTodas();      // trava tudo enquanto o diálogo não aparece

        Timer timerTente = new Timer(3000, evento -> {
            ((Timer) evento.getSource()).stop();
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                        SwingUtilities.getWindowAncestor(Puzzle1Escolha.this),
                        "Tente outra desculpa"
                );
                reabilitarOpcoesNaoBloqueadas(); // libera só as ainda não tentadas
            });
        });
        timerTente.setRepeats(false);
        timerTente.start();
    }

    private void reabilitarOpcoesNaoBloqueadas() {
        for (JButton b : new JButton[]{opcao1, opcao2, opcao3, opcao4}) {
            if (b.getBackground() != Color.GRAY) { // não foi "bloqueado" permanentemente
                b.setEnabled(true);
            }
        }
    }
    private void mostrarResposta(String texto) {

        labelFundo.add(spriteJorjao, 0);
        labelFundo.add(caixaDialogoJorjao, 0);

        spriteJorjao.setVisible(true);
        caixaDialogoJorjao.setVisible(true);

        caixaDialogoJorjao.digitarTexto(texto);

        labelFundo.revalidate();
        labelFundo.repaint();
        
    }
    
    private void tenteNovamente() {
    	 Timer timerTente = new Timer(3000, evento -> {
             ((Timer) evento.getSource()).stop();
             SwingUtilities.invokeLater(() -> {
                 JOptionPane.showMessageDialog(
                         SwingUtilities.getWindowAncestor(Puzzle1Escolha.this),
                         "Tente outra desculpa"
                 );
              
             });
         });

         timerTente.setRepeats(false);
         timerTente.start();
    }

    private void bloquearOpcao(JButton botao) {
        botao.setEnabled(false);
        botao.setBackground(Color.GRAY);
        botao.setForeground(Color.DARK_GRAY);
    }

    private void desabilitarTodas() {
        opcao1.setEnabled(false);
        opcao2.setEnabled(false);
        opcao3.setEnabled(false);
        opcao4.setEnabled(false);
    }
}