package telas.Puzzle1;

import componentes.CaixaDialogo;
import componentes.Personagem;
import telas.FrameJanela;

import javax.swing.*;
import java.awt.*;

public class PuzzleEscolha1 extends JPanel {

    private FrameJanela frame;
    private JLabel labelFundo;

    private JButton opcao1;
    private JButton opcao2;
    private JButton opcao3;
    private JButton opcao4;

    private JLabel spriteJorjao;
    private CaixaDialogo caixaDialogoJorjao;
    private Personagem jorjao;

    public PuzzleEscolha1(FrameJanela frame) {
        this.frame = frame;

        setLayout(null);
        setPreferredSize(new Dimension(1280,720));

        // FUNDO
        ImageIcon fundo = new ImageIcon(
                PuzzleEscolha1.class.getResource("/assets/salaDeEstar-pixilart.png")
        );

        labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0,0,1280,720);
        labelFundo.setLayout(null);

        add(labelFundo);

        this.jorjao = new Personagem(new ImageIcon(PuzzleEscolha1.class.getResource("/assets/JorjaoNormal.png")));
        this.spriteJorjao = jorjao.getSprite();

        criarJorjao();
        criarOpcoes();
    }

    private void criarJorjao(){
        ImageIcon imgDialogo = new ImageIcon(
                PuzzleEscolha1.class.getResource("/assets/DialogoJorjao.png")
        );

        caixaDialogoJorjao = new CaixaDialogo(
                imgDialogo,
                70,
                500,
                Color.black
        );
        caixaDialogoJorjao.setVisible(false);
    }

    private void criarOpcoes(){
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

    private JButton criarBotao(String texto, int y){

        JButton botao = new JButton(texto);

        botao.setBounds(140,y,1000,80);

        botao.setFont(new Font("Arial", Font.BOLD, 20));

        return botao;
    }

    private void configurarEventos(){
        opcao1.addActionListener(e -> {
            mostrarResposta(
                    "Não vou poder te acompanhar na sua mãe hoje e não quero que saia sozinha"
            );
            bloquearOpcao(opcao1);
        });
        opcao2.addActionListener(e -> {
            mostrarResposta(
                    "Como assim? Mas a casa está uma bagunça, você não pretende sair e deixar ela desse jeito, né?"
            );
            bloquearOpcao(opcao2);
        });
        opcao4.addActionListener(e -> {
            mostrarResposta(
                    "Mas nem pensar! Você nem terminou de passar minhas roupas"
            );
            bloquearOpcao(opcao4);
        });
        opcao3.addActionListener(e -> {
            mostrarResposta(
                    "Consulta na UBS? Não me lembro disso. Mas tudo bem, vá, mas troque de roupa antes, não quero que os médicos te vejam assim..."
            );
            desabilitarTodas();

            Timer timer = new Timer(3500, evento -> {
                ((Timer)evento.getSource()).stop();
                JOptionPane.showMessageDialog(
                        this,
                        "Puzzle concluído!"
                );
                // frame.trocarTela(new PuzzleRoupa(frame));
            });

            timer.setRepeats(false);
            timer.start();
        });
    }

    private void mostrarResposta(String texto){

        labelFundo.remove(spriteJorjao);
        labelFundo.remove(caixaDialogoJorjao);

        labelFundo.add(spriteJorjao, 0);
        labelFundo.add(caixaDialogoJorjao, 0);

        spriteJorjao.setVisible(true);
        caixaDialogoJorjao.setVisible(true);

        caixaDialogoJorjao.digitarTexto(texto);

        labelFundo.revalidate();
        labelFundo.repaint();
    }

    private void bloquearOpcao(JButton botao){
        botao.setEnabled(false);
        botao.setBackground(Color.GRAY);
        botao.setForeground(Color.DARK_GRAY);
    }

    private void desabilitarTodas(){
        opcao1.setEnabled(false);
        opcao2.setEnabled(false);
        opcao3.setEnabled(false);
        opcao4.setEnabled(false);
    }
}