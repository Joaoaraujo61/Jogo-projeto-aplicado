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
   

    public Puzzle3Jogavel(FrameJanela frame) {
        this.frame = frame; 
        
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
      
        // Imagem de Fundo 
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/FundoCaixadeFios.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        this.add(labelFundo); 
  
        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1);
        
        ImageIcon imgFiosDireita = new ImageIcon(getClass().getResource("/assets/fiosDireita.png"));
        this.labelFiosDireita = new JLabel(imgFiosDireita);
        labelFiosDireita.setBounds(358, 158, imgFiosDireita.getIconWidth(), imgFiosDireita.getIconHeight());
        labelFiosDireita.setLayout(null);
        labelFundo.add(labelFiosDireita);
        
      
    } 
     
    void mudarTela() {
        if (frame != null) {
            frame.trocarTela(new Puzzle4(frame));
        }
    } 
    
}  




