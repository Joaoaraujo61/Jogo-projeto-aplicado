package telas;

import javax.swing.*;
import java.awt.*;
import componentes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Como ela estende JPanel, a própria CenaInicial É o painel
public class CenaInicial extends JPanel {
    private FrameJanela frame; // Guarda a referência da janela principal
    
    // Recebe a janela principal do jogo por parâmetro
    public CenaInicial(FrameJanela frame) {
        this.frame = frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        
        // Configura a cor de fundo deste painel
        this.setBackground(Color.BLACK); 
        
        // Define um gerenciador de layout (FlowLayout posiciona os itens em fila)
        this.setLayout(new FlowLayout());
        /*
        // Criando o botão e adicionando direto NESTE painel (this)
        JButton botao = new JButton("Clique Aqui");
        this.add(botao);
        
       // JButton botao = new JButton("Entrar no Jogo");
        
        // Ação do botão para mudar de tela
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chama o método da janela para avançar para o menu!
                CenaInicial.this.frame.irParaMenu(); 
            }
        });*/

        //this.add(botao);
    } 
    private String[] dialogoIncial= {
        		"Eu estava no 2° semestre de medicina de uma universidade federal, quando trombei pelos corredores da faculdade, com o meu marido Jorjão, no 6° semestre de Relações Internacionais.",
        		"À princípio ele era um cara legal, almoçavámos juntos sempre, ele sempre pagava a conta e mais uma sobremesa, estudávamos na casa dele, saímos com amigos.", 
        		"Até que ele já não queria mais que eu saísse sem ele, ou perdesse muito tempo estudando, acabei deixando minha faculdade incompleta para me dedicar à casa depois que nos casamos.",
        		"Fiquei ali até o dia 23/06/1999 e quero que você me acompanhe na minha Rota de Fuga"
    };
    
    private int indiceDaFala = 0;
    
    if (indiceDaFala < dialogoMae.length) {
        caixaDialogo.digitarTexto(dialogoIncial[indiceDaFala]);
   
        indiceDaFala++; 
        
    } else {
 
        
       // Reseta o contador para 0 (caso seja necessário  usar o telefone de novo no jogo)
        //indiceDaFala = 0; 
     
    	 CenaInicial.this.frame.irParaMenu(); 
    }
    
}
}