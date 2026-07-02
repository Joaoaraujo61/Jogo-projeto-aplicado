package telas;

import telas.MenuInicial;
import telas.Puzzle3Jogavel;
import componentes.*;
import telas.FrameJanela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import componentes.Botao;
import componentes.CaixaDialogo;

import javax.swing.*;
import java.awt.*;


public class Puzzle6 extends JPanel{
	
    private FrameJanela frame; 
	private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private Botao telefoneBtn;
    private JLabel labelCaixaTelefone;
    private CaixaDialogo caixaDialogo;

    public Puzzle6(FrameJanela frame) {
    	
    	this.frame =  frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        //Fundo
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/PortaFechada.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        add(labelFundo);
        
        mostrarAviso();

     
    }
    public void mostrarAviso() {
    	 Timer timer = new Timer(250, evento -> {
             ((Timer) evento.getSource()).stop();
             SwingUtilities.invokeLater(() -> {
                 JOptionPane.showMessageDialog(
                         SwingUtilities.getWindowAncestor(Puzzle6.this),
                         "A porta está trancada!"
                 ); 
             });
         });

         timer.setRepeats(false);
         timer.start();

    }
   
    
    
    
}