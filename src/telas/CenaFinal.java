package telas;

import javax.swing.*;

import componentes.Botao;
import componentes.CaixaDialogo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class CenaFinal  extends JPanel {
    
    private FrameJanela frame; 
    private JLabel labelFundo;
    private ImageIcon[] quadrosCenaFinal;
    private int quadroAtual = 1;
    private Timer timerCenaFinal;
   

    public CenaFinal(FrameJanela frame) {
        this.frame = frame; 
        
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
  
      quadrosCenaFinal = new ImageIcon[] {
    		  new ImageIcon(MenuInicial.class.getResource("/assets/cenas-finais/cenaFinal1.png")),
    		  new ImageIcon(MenuInicial.class.getResource("/assets/cenas-finais/cenaFinal2.png")),
    		  new ImageIcon(MenuInicial.class.getResource("/assets/cenas-finais/cenaFinal3.png")),
    		  new ImageIcon(MenuInicial.class.getResource("/assets/cenas-finais/cenaFinal4.png")),
    		  new ImageIcon(MenuInicial.class.getResource("/assets/cenas-finais/cenaFinal5.png")),
    		  new ImageIcon(MenuInicial.class.getResource("/assets/cenas-finais/cenaFinal6.png")),
      };
      
        this.labelFundo = new JLabel(quadrosCenaFinal[1]);
        labelFundo.setBounds(0, 0, 1280, 720);
        this.add(labelFundo); 

        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1);
        
        this.timerCenaFinal = new Timer(3000, EventObject -> {
        	avancarQuadro();
        	
        });
        
        this.timerCenaFinal.start();
        
    } 
    
    public void avancarQuadro() {
    	quadroAtual++;
    	
    	if(quadroAtual < quadrosCenaFinal.length) {
    		labelFundo.setIcon(quadrosCenaFinal[quadroAtual]);
    	} else {
    		timerCenaFinal.stop();
    	}
    }


}

