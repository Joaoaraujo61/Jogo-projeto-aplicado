import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    public static void main(String[] args) {

        ImageIcon fundo = new ImageIcon(
                Main.class.getResource("/assets/fundoMenu.png"));
        ImageIcon comecarIcon = new ImageIcon(
                Main.class.getResource("/assets/botaoComecar.png"));
        ImageIcon continuarIcon = new ImageIcon(
                Main.class.getResource("/assets/botaoContinuar.png"));

        JFrame frame = new JFrame("Menu");

        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel labelFundo = new JLabel(fundo);
        labelFundo.setLayout(null);

        JButton botaoComecar = new JButton(comecarIcon);
        JButton botaoContinuar = new JButton(continuarIcon);
        
        int larguraBotao = continuarIcon.getIconWidth();
        int alturaBotao = continuarIcon.getIconHeight();

        int x = (1280 - larguraBotao) / 2;
        int y = (720 - alturaBotao) / 2;


        botaoContinuar.setBounds(x, y, larguraBotao, alturaBotao);
        botaoComecar.setBounds(x, 130, larguraBotao, alturaBotao);

        botaoContinuar.setBorderPainted(false);
        botaoContinuar.setContentAreaFilled(false);
        botaoContinuar.setFocusPainted(false);
        botaoContinuar.setOpaque(false);

        botaoComecar.setBorderPainted(false);
        botaoComecar.setContentAreaFilled(false);
        botaoComecar.setFocusPainted(false);
        botaoComecar.setOpaque(false);

        labelFundo.add(botaoContinuar);
        labelFundo.add(botaoComecar);

        frame.setContentPane(labelFundo);

        frame.setVisible(true);
    }
}