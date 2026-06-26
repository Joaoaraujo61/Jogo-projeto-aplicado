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
    
    private int mouseX;
    private int mouseY;
   
    private int origX;
    private int origY;

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
      
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/FundoCaixadeFios.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        this.add(labelFundo); 
  
        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1); 
       
        this.setComponentZOrder(labelFioAzul, 0);
        this.setComponentZOrder(labelFioVerde, 1);
        this.setComponentZOrder(labelFioVermelho, 2);
        this.setComponentZOrder(labelFiosDireita, 3);
        
        tornarArrastavelComEncaixe(labelFioAzul, 260);
       // tornarArrastavelComEncaixe(this.labelFioVerde);
       // tornarArrastavelComEncaixe(this.labelFioVermelho);
    } 
    
    private void tornarArrastavelComEncaixe(JLabel fio, int origY) {
    	
        // 1. Posição de ORIGEM (Onde o fio começa no seu construtor)
        final int origX = 358;
       // final int origY = 158;

        // 2. Posição do DESTINO CORRETO (Onde ele deve ser ligado)
        // Altere esses valores para as coordenadas X e Y onde o fio verde DEVE encaixar!
        final int destinoX = 358; 
        final int destinoY = 420;

        // Margem de erro em pixels (Se o jogador soltar a até 40px do destino, o jogo aceita)
        final int MARGEM_ENCAIXE = 20; 

        fio.setCursor(new Cursor(Cursor.HAND_CURSOR));

        fio.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            	 mouseX = e.getX();
            	 mouseY = e.getY();
            }

            // NOVO: Quando o jogador SOLTA o botão do mouse
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                int atualX = fio.getX();
                int atualY = fio.getY();

                // Calcula a distância usando a fórmula de distância entre dois pontos (Teorema de Pitágoras)
                double distanciaAoDestino = Math.sqrt(Math.pow(atualX - destinoX, 2) + Math.pow(atualY - destinoY, 2));

                if (distanciaAoDestino <= MARGEM_ENCAIXE) {
                    // Ganhou! O fio gruda perfeitamente na posição correta
                    fio.setLocation(destinoX, destinoY);
                    
                    // Aqui você pode chamar a função para avançar de tela ou liberar o puzzle!
                    // mudarTela(); 
                } else {
                    // Errou! O fio volta magicamente para o começo
                    fio.setLocation(origX, origY);
                    System.out.println("Errou o alvo, voltando para a origem.");
                }

                repaint(); // Atualiza a tela
            }
        });

        fio.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                int novoX = fio.getX() + e.getX() - mouseX;
                int novoY = fio.getY() + e.getY() - mouseY;
                fio.setLocation(novoX, novoY);
                repaint();
            }
        });
    }
     
    void mudarTela() {
        if (frame != null) {
            frame.trocarTela(new Puzzle4(frame));
        }
    } 
    
}  




