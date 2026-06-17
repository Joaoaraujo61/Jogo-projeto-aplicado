package telas;

import javax.swing.*;
import java.awt.*;
import componentes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CenaInicial extends JPanel {

    private FrameJanela frame;
    private CaixaDialogo caixaDialogo;
    private int indiceDaFala = 0;

    private String[] dialogoInicial = {
        "Eu estava no 2° semestre de medicina de uma universidade federal, quando trombei pelos corredores da faculdade, com o meu marido Jorjão, no 6° semestre de Relações Internacionais.",
        "À princípio ele era um cara legal, almoçavámos juntos sempre, ele sempre pagava a conta e mais uma sobremesa, estudávamos na casa dele, saímos com amigos.", 
        "Até que ele já não queria mais que eu saísse sem ele, ou perdesse muito tempo estudando, acabei deixando minha faculdade incompleta para me dedicar à casa depois que nos casamos.",
        "Fiquei ali até o dia 23/06/1999 e quero que você me acompanhe na minha Rota de Fuga"
    };

    public CenaInicial(FrameJanela frame) {
        this.frame = frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        this.setBackground(Color.BLACK); 
        this.setLayout(new FlowLayout());
    }     

    public void contarHistoria() {
        if (indiceDaFala < dialogoInicial.length) {
            caixaDialogo.digitarTexto(dialogoInicial[indiceDaFala]);
            indiceDaFala++; 
        } else {
            CenaInicial.this.frame.irParaMenu(); 
        }
    }
}