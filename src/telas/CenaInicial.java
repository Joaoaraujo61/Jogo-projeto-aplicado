package telas;

import javax.swing.*;
import java.awt.*;
import componentes.*;
import telas.Puzzle1.Puzzle1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CenaInicial extends JPanel {

    private FrameJanela frame;
    private CaixaDialogo caixaDialogo;
    private int indiceDaFala = 0;
	

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

    }     

    public void contarHistoria() {
        if (indiceDaFala < dialogoInicial.length) {
            caixaDialogo.digitarTexto(dialogoInicial[indiceDaFala]);
            indiceDaFala++; 
        } else {
        	  frame.trocarTela(new Puzzle1(frame));
        }
    }
}