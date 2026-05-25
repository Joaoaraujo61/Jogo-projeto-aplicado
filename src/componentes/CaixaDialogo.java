package componentes;

import javax.swing.*;
import java.awt.*;

public class CaixaDialogo extends JLabel {
    private JLabel labelTexto;
    private Timer timerDigitando;
    private int larguraTexto;

    public CaixaDialogo(ImageIcon imagem, int x, int y) {
        super(imagem);
        setBounds(x, y, imagem.getIconWidth(), imagem.getIconHeight());
        setLayout(null);
        setOpaque(false);

        this.larguraTexto = imagem.getIconWidth() - 250;

        labelTexto = new JLabel();
        labelTexto.setBounds(140, 30, larguraTexto, imagem.getIconHeight()-20);
        labelTexto.setForeground(Color.WHITE);
        labelTexto.setFont(new Font("Arial", Font.PLAIN, 35));
        labelTexto.setVerticalAlignment(JLabel.TOP);

        add(labelTexto);
    }

    public void digitarTexto(String texto) {
        if (timerDigitando != null) timerDigitando.stop(); // para se já estiver rodando

        String[] letras = texto.split("");
        int[] index = {0};
        StringBuilder textoAtual = new StringBuilder();

        timerDigitando = new Timer(50, e -> {
            if (index[0] < letras.length) {
                textoAtual.append(letras[index[0]]);
                labelTexto.setText("<html><body style='width: "
                        + larguraTexto + "px; word-wrap: break-word; overflow-wrap: break-word;'>"
                        + textoAtual
                        + "</body></html>");
                index[0]++;
            } else {
                timerDigitando.stop();
            }
        });

        timerDigitando.start();
    }
}