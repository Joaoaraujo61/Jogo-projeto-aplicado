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

        //this.larguraTexto = imagem.getIconWidth() - 150;
        this.larguraTexto = imagem.getIconWidth() - 250;


        labelTexto = new JLabel();
        //labelTexto.setBounds(140, 25, larguraTexto, imagem.getIconHeight()-20);
        //Novo tamanho não fica saindo da caixa de pensamento
        labelTexto.setBounds(140, 35, larguraTexto, imagem.getIconHeight()-20);

        

        labelTexto.setForeground(cor);
        labelTexto.setFont(new Font("Arial", Font.PLAIN, 35));
      

        labelTexto.setVerticalAlignment(JLabel.TOP);

        add(labelTexto);
    }	   
    
    //"Caixa de diálogo" usada na cena inicial
    public CaixaDialogo(int x, int y, Color cor) {
        setBounds(x, y, 1180, 150);
        setLayout(null);

        this.larguraTexto = this.getWidth();


        labelTexto = new JLabel();
        labelTexto.setBounds(20, 20, larguraTexto, 150);
       
        this.setBackground(Color.BLACK); 
        this.setOpaque(true);
        labelTexto.setForeground(Color.WHITE);
        labelTexto.setFont(new Font("Georgia", Font.ITALIC, 35));
     

        labelTexto.setVerticalAlignment(JLabel.TOP);

        add(labelTexto);
    }	 

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