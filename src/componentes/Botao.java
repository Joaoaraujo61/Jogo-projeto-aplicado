package componentes;

import javax.swing.*;
import java.awt.*;

public class Botao {
    private ImageIcon icone;
    private JButton botaoClicavel;
    private int largura;
    private int altura;
    private int posicaoX;
    private int posicaoY;

    public Botao(ImageIcon icone,int posicaoX,int posicaoY){
        this.icone = icone;
        this.largura = icone.getIconWidth();
        this.altura = icone.getIconHeight();
        this.posicaoY = posicaoY;
        this.posicaoX = posicaoX;
        botaoClicavel = new JButton(icone);
        botaoClicavel.setBounds(posicaoX, posicaoY, largura, altura);

        botaoClicavel.setBorderPainted(false);
        botaoClicavel.setContentAreaFilled(false);
        botaoClicavel.setFocusPainted(false);
        botaoClicavel.setOpaque(false);
        botaoClicavel.setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    public JButton getBotaoClicavel() {
        return botaoClicavel;
    }
}
