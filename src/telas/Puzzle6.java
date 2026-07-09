package telas;

import javax.swing.*;
import java.awt.*;

public class Puzzle6 extends JPanel {

    private JLabel fundo;
    private boolean pegouGrampo = false;
    private JLabel labelBarraAvanco;

    public Puzzle6(FrameJanela frame) {
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        mostrarSala();
    }

    private void mostrarSala() {
        trocarFundo("/assets/SalaComPorta.png");
        
        //Imagem barra de avanço
        ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/5-6.png"));
        this.labelBarraAvanco = new JLabel(imgBarra);
        labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());
        labelBarraAvanco.setLayout(null);
        this.add(labelBarraAvanco);

        JButton planta = criarBotao(950, 250, 250, 350);
        planta.addActionListener(e -> mostrarPlanta());
        fundo.add(planta);

        JButton porta = criarBotao(510, 80, 290, 470);
        porta.addActionListener(e -> mostrarPortaZoom());
        fundo.add(porta);
    }

    private void mostrarPlanta() {
        trocarFundo("/assets/plantaDaSala.png");

        JButton vaso = criarBotao(0, 0, 1280, 720);
        vaso.addActionListener(e -> mostrarZoomPlanta());
        fundo.add(vaso);
    }

    private void mostrarZoomPlanta() {
        trocarFundo("/assets/zoomDaPlantaComGrampo.png");

        JButton grampo = criarBotao(0, 0, 1280, 720);
        grampo.addActionListener(e -> pegarGrampo());
        fundo.add(grampo);
    }

    private void pegarGrampo() {
        pegouGrampo = true;

        trocarFundo("/assets/Grampo.png");

        JOptionPane.showMessageDialog(
                this,
                "Você encontrou um grampo de cabelo!\nEsse objeto pode servir para abrir a porta."
        );

        mostrarSala();
    }

    private void mostrarPortaZoom() {
        trocarFundo("/assets/PortaZoom.png");

        JButton fechadura = criarBotao(0, 0, 1280, 720);
        fechadura.addActionListener(e -> tentarAbrirFechadura());
        fundo.add(fechadura);
    }

    private void tentarAbrirFechadura() {
        if (!pegouGrampo) {
            trocarFundo("/assets/fechadurazoom.png");

            JOptionPane.showMessageDialog(
                    this,
                    "A porta está trancada.\nTalvez exista algo fino para abrir a fechadura."
            );

            mostrarSala();
            return;
        }

        mostrarFechaduraComGrampo();
    }

    private void mostrarFechaduraComGrampo() {
        trocarFundo("/assets/FechaduraComGampoDaPortaZoom.png");

        JOptionPane.showMessageDialog(
                this,
                "Você usou o grampo para destrancar a porta."
        );

        mostrarPortaAberta();
    }

    private void mostrarPortaAberta() {
        trocarFundo("/assets/SalaComPortaAberta.png");

        JButton sair = criarBotao(500, 80, 450, 550);
        sair.addActionListener(e -> mostrarFinal());
        fundo.add(sair);
    }

    private void mostrarFinal() {
        trocarFundo("/assets/SalaSemAPorta.png");

        JOptionPane.showMessageDialog(
                this,
                "Parabéns!\nVocê conseguiu sair."
        );
    }

    private void trocarFundo(String caminho) {
        removeAll();

        ImageIcon icon = new ImageIcon(getClass().getResource(caminho));

        Image imagem = icon.getImage().getScaledInstance(
                1280,
                720,
                Image.SCALE_SMOOTH
        );

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