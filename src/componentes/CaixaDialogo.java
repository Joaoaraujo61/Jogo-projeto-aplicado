package componentes;

import javax.swing.*;
import java.awt.*;

public class CaixaDialogo extends JLabel {
    private JLabel labelTexto;
    private Timer timerDigitando;
    private int larguraTexto;

    public CaixaDialogo(ImageIcon imagem, int x, int y, Color cor) {
        super(imagem);
        setBounds(x, y, imagem.getIconWidth(), imagem.getIconHeight());
        setLayout(null);
        setOpaque(false);

        this.larguraTexto = imagem.getIconWidth() - 250;


        labelTexto = new JLabel();
        labelTexto.setBounds(140, 25, larguraTexto, imagem.getIconHeight()-20);
        //mudei o segundo parâmetro para 25
        

        labelTexto.setForeground(cor);
        labelTexto.setFont(new Font("Arial", Font.PLAIN, 35));
      

        labelTexto.setVerticalAlignment(JLabel.TOP);

        add(labelTexto);
    }	
    //outras configurações possíveis, que talvez ficariam melhores para o puzzle4 
       // this.larguraTexto = imagem.getIconWidth() - 100;
       //labelTexto.setBounds(100, 40, larguraTexto, imagem.getIconHeight()-20);
       //labelTexto.setFont(new Font("Arial", Font.PLAIN, 30));
      

    public void digitarTexto(String texto) {
        if (timerDigitando != null) timerDigitando.stop();

        String[] letras = texto.split("");
        int[] index = {0};
        StringBuilder textoAtual = new StringBuilder();

        timerDigitando = new Timer(20, e -> {
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