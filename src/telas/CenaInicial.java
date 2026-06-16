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
        });

        this.add(botao);
    }
    
}