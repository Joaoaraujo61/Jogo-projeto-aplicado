package telas;

import javax.swing.*;

import componentes.Botao;
import componentes.CaixaDialogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class CenaFinal  extends JPanel {
    
    private FrameJanela frame; 
    private JLabel labelFundo;
    private ImageIcon[] quadrosCenaFinal;
    private int quadroAtual = 0;
    private Timer timerCenaFinal;
   

    public CenaFinal(FrameJanela frame) {
        this.frame = frame; 
        
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
  
      quadrosCenaFinal = new ImageIcon[] {
    		  new ImageIcon(MenuInicial.class.getResource("/assets/Frame1.png")),
    		  new ImageIcon(MenuInicial.class.getResource("/assets/frame2.png")),
    		  new ImageIcon(MenuInicial.class.getResource("/assets/frame4.png")),
              new ImageIcon(MenuInicial.class.getResource("/assets/frame5.png")),
      };
      
        this.labelFundo = new JLabel(quadrosCenaFinal[0]);
        labelFundo.setBounds(0, 0, 1279, 720);
        this.add(labelFundo); 

        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1);
        
        MouseAdapter acaoDeClique = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	avancarQuadro();
            }
        };
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        	    KeyStroke.getKeyStroke("SPACE"), "avancarHistoria"
        	);

        	this.getActionMap().put("avancarHistoria", new AbstractAction() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	    	avancarQuadro();
        	    }
        	});

        this.addMouseListener(acaoDeClique);    
        
    } 
    
    public void avancarQuadro() {
    	quadroAtual++;
    	if(quadroAtual < quadrosCenaFinal.length) {
    		labelFundo.setIcon(quadrosCenaFinal[quadroAtual]);
    	} else {
    		 frame.trocarTela(new MenuInicial(frame));
    	}
    }


}

