package telas.Puzzle2;

import componentes.LinhasCosturaPanel;
import telas.FrameJanela;
import telas.Puzzle3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseEvent;

public class PuzzleCostura extends JPanel {
    // Sequência correta: alternar borda A (esquerda) e borda B (direita)
    private Point[] sequencia = {
            new Point(780, 140),  // ponto 1 — borda esquerda do rasgo, topo
            new Point(890, 140),  // ponto 2 — borda direita do rasgo, topo
            new Point(780, 240),  // ponto 3 — borda esquerda, meio
            new Point(890, 240),  // ponto 4 — borda direita, meio
            new Point(780, 340),  // ponto 5 — borda esquerda, abaixo do meio
            new Point(890, 340),  // ponto 6 — borda direita, abaixo do meio
            new Point(780, 440),  // ponto 7 — borda esquerda, baixo
            new Point(890, 440),
    };

    private int indiceAtual = 0;
    private LinhasCosturaPanel painelLinhas;
    private JLabel[] spritesPontos;
    private FrameJanela frame;

    public PuzzleCostura(FrameJanela frame) {
        this.frame = frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        ImageIcon fundo = new ImageIcon(getClass().getResource("/assets/VestidoRasgado.png"));
        JLabel labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        painelLinhas = new LinhasCosturaPanel();
        painelLinhas.setBounds(0, 0, 1280, 720);

        BufferedImage imgAgulha = null;
        try {
            imgAgulha = ImageIO.read(getClass().getResourceAsStream("/assets/agulha.png"));
        } catch (IOException e) {
            System.err.println("Erro ao carregar agulha: " + e.getMessage());
        }
        if (imgAgulha != null) {
            Cursor cursorAgulha = Toolkit.getDefaultToolkit()
                    .createCustomCursor(imgAgulha, new Point(0, 0), "agulha");
            setCursor(cursorAgulha);
        }

        criarPontos(labelFundo);

        // Adiciona ao PuzzleCostura em ordem: fundo primeiro, linhas por cima
        add(painelLinhas); // por cima do labelFundo
        add(labelFundo);   // embaixo de tudo

        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    frame,
                    "Puzzle 2: costure a roupa ligando os pontos na sequência correta",
                    "Instrução",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }

        private void criarPontos(JLabel labelFundo) {
            spritesPontos = new JLabel[sequencia.length];
            ImageIcon imgPonto = new ImageIcon(getClass().getResource("/assets/ponto.png"));

            // Cria o cursor agulha uma vez para reusar em todos os pontos
            Cursor cursorAgulha = getCursor(); // pega o cursor da agulha já setado no painel

            for (int i = 0; i < sequencia.length; i++) {
                final int idx = i;
                JLabel ponto = new JLabel(imgPonto);
                int px = sequencia[i].x - imgPonto.getIconWidth() / 2;
                int py = sequencia[i].y - imgPonto.getIconHeight() / 2;
                ponto.setBounds(px, py, imgPonto.getIconWidth(), imgPonto.getIconHeight());
                ponto.setCursor(cursorAgulha); // agulha em vez de HAND_CURSOR

                ponto.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        // só inicia o arrasto se for o ponto esperado
                        if (idx == indiceAtual) {
                            System.out.println("Arrasto iniciado no ponto: " + idx);
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // quando solta, verifica se o mouse está sobre o próximo ponto
                        // converte a posição do mouse para coordenadas do labelFundo
                        Point posicaoNoFundo = SwingUtilities.convertPoint(ponto, e.getPoint(), ponto.getParent());

                        for (int j = 0; j < spritesPontos.length; j++) {
                            if (spritesPontos[j] != null && spritesPontos[j].isVisible()) {
                                Rectangle bounds = spritesPontos[j].getBounds();
                                if (bounds.contains(posicaoNoFundo) && j == indiceAtual) {
                                    verificarClique(j);
                                    break;
                                }
                            }
                        }
                    }
                });

                spritesPontos[i] = ponto;
                labelFundo.add(ponto, 0);
            }
        }

    private void verificarClique(int indiceClicado) {
        if (indiceClicado != indiceAtual) return;

        // Se não é o primeiro ponto, adiciona o anterior para ter de onde sair a linha
        if (indiceAtual > 0) {
            painelLinhas.adicionarLinha(sequencia[indiceAtual - 1]);
        }
        painelLinhas.adicionarLinha(sequencia[indiceAtual]);

        spritesPontos[indiceAtual].setVisible(false);
        indiceAtual++;

        if (indiceAtual >= sequencia.length) {
            Timer timer = new Timer(1000, ev -> {
                ((Timer) ev.getSource()).stop();
                frame.trocarTela(new Puzzle2Final(frame));
                //frame.trocarTela(new Puzzle3(frame));
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}
