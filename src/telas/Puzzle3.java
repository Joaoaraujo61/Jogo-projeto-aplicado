package telas;

import telas.MenuInicial;
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
    private ImageIcon imgTelefoneTocando;
    private ImageIcon imgRosangelaNormal;
    private Botao telefoneBtn;
    private JLabel labelCaixaTelefone;
    private CaixaDialogo caixaDialogo;
    private JButton botao1;

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
       
        // Configuração do botão auxiliar "Clique aqui"
        this.botao1 = new JButton("Clique aqui");
        botao1.setBounds(500, 600, 200, 50);
        
        // Configura as ações de clique
        botao1.addActionListener(e -> mudaTela());
        botaoTelefone.addActionListener(e -> mudaTela());

        // Adiciona os componentes na tela principal (frente)
        this.add(botao1);
        this.add(botaoTelefone);
      
        // Imagem de Fundo (atrás)
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        this.add(labelFundo); 

        // Garante que o fundo fique na última camada
        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1);
    } // <- Fecha o construtor corretamente aqui
     
    void mudaTela() {
        /*if (frame != null) {
            frame.trocarTela(new Puzzle4(frame));
        }*/
        Timer timer = new Timer(200, evento -> {
            ((Timer) evento.getSource()).stop();
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                        SwingUtilities.getWindowAncestor(Puzzle3.this),
                        "Puzzle concluído!"
                );
                frame.trocarTela(new Puzzle4(frame));
            });
        });

        timer.setRepeats(false);
        timer.start();
    } // <- Fecha o método mudaTela aqui

}