import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {

        ImageIcon fundo = new ImageIcon(
                Main.class.getResource("/assets/fundo1-pixilart.png"));

        ImageIcon entrarIcon = new ImageIcon(
                Main.class.getResource("/assets/pixilart-drawing (1).png"));

        JFrame frame = new JFrame("Menu");

        frame.setSize(640, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel labelFundo = new JLabel(fundo);
        labelFundo.setLayout(null);

        JButton botaoIniciar = new JButton(entrarIcon);
        
        int larguraBotao = entrarIcon.getIconWidth();
        int alturaBotao = entrarIcon.getIconHeight();

        int x = (640 - larguraBotao) / 2;
        int y = (360 - alturaBotao) / 2;

        botaoIniciar.setBounds(x, y, larguraBotao, alturaBotao);

        botaoIniciar.setBorderPainted(false);
        botaoIniciar.setContentAreaFilled(false);
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.setOpaque(false);

        labelFundo.add(botaoIniciar);

        frame.setContentPane(labelFundo);

        frame.setVisible(true);
    }
}