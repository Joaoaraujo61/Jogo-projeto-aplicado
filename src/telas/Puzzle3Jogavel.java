package telas;


import telas.MenuInicial;
import telas.Puzzle1.Puzzle1;
import telas.Puzzle1.PuzzleEscolha1;
import telas.Puzzle4.Puzzle4;
import componentes.*;
import telas.FrameJanela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import componentes.Botao;
import componentes.CaixaDialogo;

public class Puzzle3Jogavel extends JPanel {
    
    private FrameJanela frame; 
    private JLabel labelFundo;
    private Botao telefoneBtn;
    private JLabel labelFiosDireita;
    private JLabel labelFioVerde;
    private JLabel labelFioVermelho;
    private JLabel labelFioAzul;
   

    public Puzzle3Jogavel(FrameJanela frame) {
        this.frame = frame; 
        
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        
     
        ImageIcon imgFiosDireita = new ImageIcon(getClass().getResource("/assets/fiosDireita.png"));
        this.labelFiosDireita = new JLabel(imgFiosDireita);
        labelFiosDireita.setBounds(358, 158, imgFiosDireita.getIconWidth(), imgFiosDireita.getIconHeight());
        this.add(labelFiosDireita); 
        
        ImageIcon imgfioAzul= new ImageIcon(getClass().getResource("/assets/fioAzul.png"));
        this.labelFioAzul = new JLabel(imgfioAzul);
        labelFioAzul.setBounds(358, 260, imgfioAzul.getIconWidth(), imgfioAzul.getIconHeight());
        this.add(labelFioAzul); 
        
        ImageIcon imgFioVerde = new ImageIcon(getClass().getResource("/assets/fioVerde.png"));
        this.labelFioVerde = new JLabel(imgFioVerde);
        labelFioVerde.setBounds(358, 350, imgFioVerde.getIconWidth(), imgFioVerde.getIconHeight());
        this.add(labelFioVerde); 
        
      
        ImageIcon imgfioVermelho= new ImageIcon(getClass().getResource("/assets/fioVermelho.png"));
        this.labelFioVermelho = new JLabel(imgfioVermelho);
        labelFioVermelho.setBounds(358, 420, imgfioVermelho.getIconWidth(), imgfioVermelho.getIconHeight());
        this.add(labelFioVermelho); 
      
        // 3. IMAGEM DE FUNDO (Adicionada por último para ir para trás de tudo)
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/FundoCaixadeFios.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        this.add(labelFundo); 
  
        // Garantia absoluta usando ZOrder: 
        // Empurra o fundo para o último lugar da fila (atrás)
        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1);
        
        // Coloca os fios nas posições da frente (índice 0 e 1)
        this.setComponentZOrder(labelFioAzul, 0);
        this.setComponentZOrder(labelFioVerde, 1);
        this.setComponentZOrder(labelFioVermelho, 2);
        this.setComponentZOrder(labelFiosDireita, 3);
    } 
     
    void mudarTela() {
        if (frame != null) {
            frame.trocarTela(new Puzzle4(frame));
        }
    } 
    
}  




