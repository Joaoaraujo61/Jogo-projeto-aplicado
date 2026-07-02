package componentes;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class LinhasCosturaPanel extends JPanel {
    private List<Point> pontosConectados = new ArrayList<>();

    public LinhasCosturaPanel() {
        setOpaque(false);
        setLayout(null);
        setEnabled(false);
    }

    public void adicionarLinha(Point p) {
        pontosConectados.add(p);
        repaint(); // força redesenho imediato
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // antialiasing deixa a linha suave, não pixelada
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(180, 120, 60)); // cor de linha de costura
        g2d.setStroke(new BasicStroke(2f));

        for (int i = 1; i < pontosConectados.size(); i++) {
            Point a = pontosConectados.get(i - 1);
            Point b = pontosConectados.get(i);
            g2d.drawLine(a.x, a.y, b.x, b.y);
        }
    }
    public int getTotalPontos() {
        return pontosConectados.size();
    }
    @Override
    public boolean contains(int x, int y) {
        return false; // faz o Swing ignorar este painel para eventos de mouse
    }
}
