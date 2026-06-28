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

public class Puzzle3 extends JPanel {
    
    private FrameJanela frame; 
    private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private Botao telefoneBtn;
    private CaixaDialogo caixaPensamento;


    public Puzzle3(FrameJanela frame) {
        this.frame = frame; 
        
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        
        // Imagens do Telefone
        this.imgTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/telefoneRompido.png"));
               
        // Configuração do botão customizado do telefone
        this.telefoneBtn = new Botao(imgTelefone, 1070, 200);
        JButton botaoTelefone = telefoneBtn.getBotaoClicavel();
        botaoTelefone.setBorderPainted(false);
        botaoTelefone.setContentAreaFilled(false);
        botaoTelefone.setFocusPainted(false);
       
        
        // Configura as ações de clique
        botaoTelefone.addActionListener(e -> mudarTela());

        this.add(botaoTelefone);
      
        // Imagem de Fundo (atrás)
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        this.add(labelFundo); 
        
        //Adicionando a caixa de pensamento
        ImageIcon imgCaixaPensamento = new ImageIcon(Puzzle1.class.getResource("/assets/caixaDePensamento.png"));
        this.caixaPensamento = new CaixaDialogo(imgCaixaPensamento, 70, 500, Color.BLACK);
        caixaPensamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(caixaPensamento);

        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1);
        
        apareceDialogo();
    } 
     
    void mudarTela() {
        if (frame != null) {
            frame.trocarTela(new Puzzle3Jogavel(frame));
        }
    } 
    
    void apareceDialogo() {
    	caixaPensamento.digitarTexto("Ah, não! O Jorjão cortou os fios do telefone, vou ter que concertá-<br> lo para falar com a minha mãe.");
    	 
    	 Timer timerCaixaPensamento = new Timer(4000, evento -> {
    		frame.remove(caixaPensamento);
    		 caixaPensamento.setVisible(false); 
    	        this.repaint();
    	 	});
         timerCaixaPensamento.setRepeats(false);
         timerCaixaPensamento.start();
         
    	 }


}


