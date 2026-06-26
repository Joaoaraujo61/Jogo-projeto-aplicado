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
   

    public Puzzle3Jogavel(FrameJanela frame) {
        this.frame = frame; 
        
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        
        // Imagens do Telefone
       // this.imgTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/FundoCaixadeFios.png"));
               
        // Configuração do botão customizado do telefone
       /* this.telefoneBtn = new Botao(imgTelefone, 1070, 200);
        JButton botaoTelefone = telefoneBtn.getBotaoClicavel();
        botaoTelefone.setBorderPainted(false);
        botaoTelefone.setContentAreaFilled(false);
        botaoTelefone.setFocusPainted(false);*/
       
        
        // Configura as ações de clique
       // botaoTelefone.addActionListener(e -> mudarTela());

      //  this.add(botaoTelefone);
      
        // Imagem de Fundo (atrás)
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/FundoCaixadeFios.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        this.add(labelFundo); 
        
  
        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1);
        
      
    } 
     
    void mudarTela() {
        if (frame != null) {
            frame.trocarTela(new Puzzle4(frame));
        }
    } 
    
}




