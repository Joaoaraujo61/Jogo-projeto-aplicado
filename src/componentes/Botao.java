package componentes;

import javax.swing.*;

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
        this.posicaoX = (1280 - largura) / 2;//
        //int y = (720 - alturaBotao) / 2;

        botaoClicavel = new JButton(icone);
        botaoClicavel.setBounds(posicaoX, posicaoY, largura, altura);

        botaoClicavel.setBorderPainted(false);
        botaoClicavel.setContentAreaFilled(false);
        botaoClicavel.setFocusPainted(false);
        botaoClicavel.setOpaque(false);

    }

    public JButton getBotaoClicavel() {
        return botaoClicavel;
    }
}
