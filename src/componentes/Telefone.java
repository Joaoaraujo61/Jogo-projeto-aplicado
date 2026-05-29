package componentes;

import telas.FrameJanela;
import telas.MenuInicial;
import telas.Puzzle1;

import javax.swing.*;

public class Telefone {
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private Botao telefoneBtn;

   public Telefone(){
       this.imgTelefone = new ImageIcon(
               MenuInicial.class.getResource("/assets/telefone.png")
       );

       this.telefoneBtn = new Botao(imgTelefone, 1070, 200);

       JButton botao = telefoneBtn.getBotaoClicavel();

       botao.setBorderPainted(false);
       botao.setContentAreaFilled(false);
       botao.setFocusPainted(false);

       this.imgTelefoneTocando = new ImageIcon(
               MenuInicial.class.getResource("/assets/telefoneTocando.png")
       );
   }


    public Botao getTelefoneBtn() {
        return telefoneBtn;
    }

    public ImageIcon getImgTelefoneTocando() {
        return imgTelefoneTocando;
    }

    public ImageIcon getImgTelefone() {
        return imgTelefone;
    }
}
