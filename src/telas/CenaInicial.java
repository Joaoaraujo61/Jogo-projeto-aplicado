package telas;

import javax.swing.*;
import java.awt.*;
import componentes.*;
import telas.Puzzle1.Puzzle1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class CenaInicial extends JPanel {

    private FrameJanela frame;
    private CaixaDialogo caixaDialogo;
    private int indiceDaFala = 0;
    
    private float alphaFade = 0.0f;
	

    private String[] dialogoInicial = {
        "Eu estava no 2° semestre de medicina de uma universidade federal, <br> quando trombei pelos corredores da faculdade, com um cara chamado <br>Jorjão,  que estava no 6° semestre de Relações Internacionais.",
        "De ínicio ele era um cara legal, almoçavámos juntos sempre, ele <br> sempre pagava a conta e mais uma sobremesa, estudávamos na casa<br> dele, saímos com amigos nos fins de semana...", 
        "Mas de repente, ele já não queria mais que eu saísse sem ele, ou <br> perdesse muito tempo estudando, então deixei minha faculdade para <br> me dedicar à casa depois que nos casamos.",
        "Fiquei ali até o dia 23/06/1999 e quero que você me acompanhe<br> na minha Rota de Fuga."
    };

    public CenaInicial(FrameJanela frame) {
       
        this.frame = frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Color.BLACK); 

        
        caixaDialogo = new CaixaDialogo(120, 500, Color.WHITE);
        this.add(caixaDialogo);

        MouseAdapter acaoDeClique = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                contarHistoria();
            }
        };

        this.addMouseListener(acaoDeClique);         
        caixaDialogo.addMouseListener(acaoDeClique); 
        
        contarHistoria();
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        	    KeyStroke.getKeyStroke("SPACE"), "avancarHistoria"
        	);

        	this.getActionMap().put("avancarHistoria", new AbstractAction() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
        	        contarHistoria();
        	    }
        	});

    }     
    
    
    
    private void iniciarFadeOut() {
        if (this.getMouseListeners().length > 0) this.removeMouseListener(this.getMouseListeners()[0]);
        if (caixaDialogo.getMouseListeners().length > 0) caixaDialogo.removeMouseListener(caixaDialogo.getMouseListeners()[0]);

        JPanel painelFade = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaFade));
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, 1280, 720);
                g2d.dispose();
            }
        };
        
        painelFade.setBounds(0, 0, 1280, 720);
        painelFade.setOpaque(false);
        this.add(painelFade);
        this.setComponentZOrder(painelFade, 0); 

        Timer timerFade = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alphaFade += 0.01f; 
                if (alphaFade >= 1.0f) {
                    alphaFade = 1.0f;
                    ((Timer)e.getSource()).stop();
                    frame.trocarTela(new Puzzle1(frame)); 
                }
                repaint();
            }
        });
        timerFade.start();
    }

    public void contarHistoria() {
        if (indiceDaFala < dialogoInicial.length) {
            caixaDialogo.digitarTexto(dialogoInicial[indiceDaFala]);
            indiceDaFala++; 
        } else {
        	iniciarFadeOut();
        }
    }
}