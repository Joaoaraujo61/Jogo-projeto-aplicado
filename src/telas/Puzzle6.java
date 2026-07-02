package telas;

import javax.swing.*;
import java.awt.*;

public class Puzzle6 extends JPanel {

    private FrameJanela frame;
    private JLabel fundo;
    private boolean pegouClipe = false;

    public Puzzle6(FrameJanela frame) {
        this.frame = frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        mostrarPortaFechada();
    }

    private void mostrarPortaFechada() {
        trocarFundo("/assets/PortaFechada.png");

        JButton localClipe = criarBotao(0, 350, 350, 370);
        localClipe.addActionListener(e -> mostrarAchouClipe());
        fundo.add(localClipe);

        JButton porta = criarBotao(390, 80, 360, 600);
        porta.addActionListener(e -> tentarAbrirPorta());
        fundo.add(porta);
    }

    private void mostrarAchouClipe() {
        pegouClipe = true;
        trocarFundo("/assets/AchouClip.png");

        JButton continuar = criarBotao(0, 0, 1280, 720);
        continuar.addActionListener(e -> mostrarPortaFechada());
        fundo.add(continuar);
    }

    private void tentarAbrirPorta() {
        if (!pegouClipe) {
            JOptionPane.showMessageDialog(this, "A porta está trancada. Procure algo para abrir.");
            return;
        }

        mostrarUsarClip();
    }

    private void mostrarUsarClip() {
        trocarFundo("/assets/UsarClip.png");

        JButton continuar = criarBotao(0, 0, 1280, 720);
        continuar.addActionListener(e -> mostrarAbrindo());
        fundo.add(continuar);
    }

    private void mostrarAbrindo() {
        trocarFundo("/assets/Abrindo.png");

        JButton continuar = criarBotao(0, 0, 1280, 720);
        continuar.addActionListener(e -> mostrarPortaAberta());
        fundo.add(continuar);
    }

    private void mostrarPortaAberta() {
        trocarFundo("/assets/PortaAberta.png");

        JButton sair = criarBotao(390, 80, 360, 600);
        sair.addActionListener(e -> mostrarParabens());
        fundo.add(sair);
    }

    private void mostrarParabens() {
        trocarFundo("/assets/Parabens.png");
        frame.trocarTela(new CenaFinal(frame));

    }

    private void trocarFundo(String caminho) {
        removeAll();

        ImageIcon icon = new ImageIcon(getClass().getResource(caminho));
        Image imagem = icon.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);

        fundo = new JLabel(new ImageIcon(imagem));
        fundo.setBounds(0, 0, 1280, 720);
        fundo.setLayout(null);

        add(fundo);
        revalidate();
        repaint();
    }

    private JButton criarBotao(int x, int y, int largura, int altura) {
        JButton botao = new JButton();
        botao.setBounds(x, y, largura, altura);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setOpaque(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }
}