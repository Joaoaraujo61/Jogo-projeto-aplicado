import java.awt.Image;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        ImageIcon iconeComecar = new ImageIcon(Main.class.getResource("/assets/botaoComecar.png"));
        ImageIcon iconeContinuar = new ImageIcon(Main.class.getResource("/assets/botaoContinuar.png"));
        ImageIcon fundo = new ImageIcon(Main.class.getResource("/assets/fundoMenu.png"));
        ImageIcon nomeJogo = new ImageIcon(Main.class.getResource("/assets/RotaDeFuga.png"));

        JFrame frame = new JFrame("Menu");

        //fundo
        JLabel labelFundo = new JLabel(fundo);
        labelFundo.setLayout(null);

        //nome Jogo
        JLabel labelNome = new JLabel(nomeJogo);
        labelNome.setBounds(410, 40, 459, 51);

        //botoes
        Botao botaoComecar = new Botao(iconeComecar,490,380);
        Botao botaoContinuar = new Botao(iconeContinuar,472, 504);

        //adiciona componentes
        labelFundo.add(labelNome);
        labelFundo.add(botaoContinuar.getBotaoClicavel());
        labelFundo.add(botaoComecar.getBotaoClicavel());

        frame.setContentPane(labelFundo);

        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}