package telas;

import javax.swing.*;
import java.awt.*;
import componentes.*;
import telas.Puzzle1.Puzzle1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CenaInicial extends JPanel {

    private FrameJanela frame;
    private CaixaDialogo caixaDialogo;
    private int indiceDaFala = 0;
	private JLabel labelFundo;


    private String[] dialogoInicial = {
        "Eu estava no 2° semestre de medicina de uma universidade federal, <br> quando trombei pelos corredores da faculdade, com um cara chamado <br>Jorjão,  que estava no 6° semestre de Relações Internacionais.",
        "De ínicio ele era um cara legal, almoçavámos juntos sempre, ele <br> sempre pagava a conta e mais uma sobremesa, estudávamos na casa<br> dele, saímos com amigos nos fins de semana...", 
        "Mas de repente, ele já não queria mais que eu saísse sem ele, ou <br> perdesse muito tempo estudando, então deixei minha faculdade para <br> me dedicar à casa depois que nos casamos.",
        "Fiquei ali até o dia 23/06/1999 e quero que você me acompanhe<br> na minha Rota de Fuga."
    };

    public CenaInicial(FrameJanela frame) {
	   setLayout(null);
       setPreferredSize(new Dimension(1280, 720));
       //Fundo
       ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
       this.labelFundo = new JLabel(fundo);
       labelFundo.setBounds(0, 0, 1280, 720);
       labelFundo.setLayout(null);
       
        this.frame = frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Color.BLACK); 
       
        JButton botao = new JButton("Entrar no Jogo");
        JButton botao1 = new JButton("Digitar texto");

       
        // Ação do botão para mudar de tela
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.trocarTela(new Puzzle1(frame));
            }
        });
        
        botao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               contarHistoria();
            }
        });
        
        this.add(botao);
        this.add(botao1);
        botao.setBounds(50, 50, 150, 40);
        botao1.setBounds(220, 50, 150, 40);
        
        caixaDialogo = new CaixaDialogo(150, 500, Color.WHITE);
       // caixaDialogo.setBounds(100, 500, 1080, 150);
        //labelFundo.setComponentZOrder(caixaDialogo, 0);
        this.add(caixaDialogo);
        //labelFundo.add(caixaDialogo);

    }     

    public void contarHistoria() {
        if (indiceDaFala < dialogoInicial.length) {
        	//caixaDialogo.labelTexto.setForeground(Color.WHITE);
           // label.setFont(new Font("Arial", Font.PLAIN, fontsize));
            caixaDialogo.digitarTexto(dialogoInicial[indiceDaFala]);
            indiceDaFala++; 
        } else {
        	  frame.trocarTela(new Puzzle1(frame));
        }
    }
}