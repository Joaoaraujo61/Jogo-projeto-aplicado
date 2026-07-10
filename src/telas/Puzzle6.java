package telas;

import javax.swing.*;

import componentes.Botao;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Puzzle6 extends JPanel {

    private JLabel fundo;
    private JLabel labelFundo;
    private boolean pegouGrampo = false;
    private JLabel labelBarraAvanco;
    private FrameJanela frame;
    private ImageIcon imgFechaduraGrampo;
    private Botao btnFechaduraGrampo;

    

    public Puzzle6(FrameJanela frame) {
		this.frame = frame;
		
		setLayout(null);
		setPreferredSize(new Dimension(1280, 720));
		
		/*//Fundo
		ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
		this.labelFundo = new JLabel(fundo);
		labelFundo.setBounds(0, 0, 1280, 720);
		labelFundo.setLayout(null);*/
	  
		//Imagem barra de avanço
		ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/5-6.png"));
		this.labelBarraAvanco = new JLabel(imgBarra);
		labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());
		labelBarraAvanco.setLayout(null);
		//this.add(labelBarraAvanco);
		
		 //Botão fechadura grampo
	       this.imgFechaduraGrampo = new ImageIcon(MenuInicial.class.getResource("/assets/FechaduraComGampoDaPortaZoom2.png"));
	       this.btnFechaduraGrampo = new Botao(imgFechaduraGrampo, 160, 412);
	       JButton botao = btnFechaduraGrampo.getBotaoClicavel();
	       botao.setBorderPainted(false);
	       botao.setContentAreaFilled(false);
	       botao.setFocusPainted(false);
	       
		
		mostrarSala();
    }

    private void mostrarSala() {
        trocarFundo("/assets/SalaComPorta.png");

        JButton planta = criarBotao(950, 250, 250, 350);
        planta.addActionListener(e -> mostrarZoomPlanta());
        fundo.add(planta);

        JButton porta = criarBotao(510, 80, 290, 470);
        porta.addActionListener(e -> mostrarPortaZoom());
        fundo.add(labelBarraAvanco);
        fundo.add(porta);
    }

    private void mostrarZoomPlanta() {
        trocarFundo("/assets/zoomDaPlantaComGrampo.png");

        JButton grampo = criarBotao(0, 0, 1280, 720);
        grampo.addActionListener(e -> pegarGrampo());
        fundo.add(grampo);
    }

    private void pegarGrampo() {
        pegouGrampo = true;

        trocarFundo("/assets/Grampo.png");

        JOptionPane.showMessageDialog(
                this,
                "Você encontrou um grampo de cabelo!\nEsse objeto pode servir para abrir a porta."
        );

        mostrarSala();
    }

    private void mostrarPortaZoom() {
        trocarFundo("/assets/PortaZoom.png");

        JButton fechadura = criarBotao(0, 0, 1280, 720);
        fechadura.addActionListener(e -> tentarAbrirFechadura());
        fundo.add(fechadura);
    }

    private void tentarAbrirFechadura() {
        if (!pegouGrampo) {
            //trocarFundo("/assets/fechadurazoom2.png");

            JOptionPane.showMessageDialog(
                    this,
                    "A porta está trancada.\nTalvez exista algo fino para abrir a fechadura."
            );

            mostrarSala();
            return;
        }

        mostrarFechaduraComGrampo();
    }

  /*  private void mostrarFechaduraComGrampo() {
       // trocarFundo("/assets/FechaduraComGampoDaPortaZoom2.png");]
    	//labelFundo.add(btnFechaduraGrampo.getBotaoClicavel());
    	fundo.add(btnFechaduraGrampo.getBotaoClicavel());
    	//fundo.add(btnFechaduraGrampo);
    	/*btnFechaduraGrampo.getBotaoClicavel().addActionListener(e -> mostrarPortaAberta());

        JOptionPane.showMessageDialog(
                this,
                "Você usou o grampo para destrancar a porta."
        );*/

        //mostrarPortaAberta();
     /*   btnFechaduraGrampo.getBotaoClicavel().addActionListener(e -> {
        	JOptionPane.showMessageDialog(
                    this,
                    "Você usou o grampo para destrancar a porta."
            );
            mostrarPortaAberta();

        });
    }*/

private void mostrarFechaduraComGrampo() {
        // 1. Muda o fundo para o asset da fechadura em zoom
        //trocarFundo("/assets/FechaduraComGampoDaPortaZoom2.png");
	trocarFundo("/assets/PortaZoom.png");
        
        // 2. Resgata o botão clicável de dentro do seu componente customizado
        JButton botao = btnFechaduraGrampo.getBotaoClicavel();
        
        // 3. Garante que ele tenha um tamanho e posição válidos na tela!
        // Ajuste X, Y, Largura e Altura para ficarem exatamente onde o jogador deve clicar
        botao.setBounds(160, 412, 200, 200); 
        
        // 4. Remove action listeners antigos para não acumular cliques duplicados
        for (java.awt.event.ActionListener al : botao.getActionListeners()) {
            botao.removeActionListener(al);
        }
        
        // 5. Configura a ação de clique do botão
        botao.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Você usou o grampo para destrancar a porta."
            );
            mostrarPortaAberta();
        });
        
        // 6. Adiciona o botão no novo fundo e puxa para a frente
        fundo.add(botao);
        fundo.setComponentZOrder(botao, 0);
        
        // 7. Força o Java Swing a desenhar o novo botão na tela!
        fundo.revalidate();
        fundo.repaint();
    }

    private void mostrarPortaAberta() {
        trocarFundo("/assets/SalaComPortaAberta.png");

        JButton sair = criarBotao(500, 80, 450, 550);
        sair.addActionListener(e -> mostrarFinal());
        fundo.add(sair);
    }

    private void mostrarFinal() {
        //trocarFundo("/assets/SalaSemAPorta.png");

        JOptionPane.showMessageDialog(
                this,
                "Parabéns!\nVocê conseguiu sair."
        );
        frame.trocarTela(new CenaFinal(frame));     
    }

    private void trocarFundo(String caminho) {
        removeAll();

        ImageIcon icon = new ImageIcon(getClass().getResource(caminho));

        Image imagem = icon.getImage().getScaledInstance(
                1280,
                720,
                Image.SCALE_SMOOTH
        );

        fundo = new JLabel(new ImageIcon(imagem));
        fundo.setBounds(0, 0, 1280, 720);
        fundo.setLayout(null);
        fundo.setOpaque(false);

        add(fundo);

        revalidate();
        repaint();
    }

    private JButton criarBotao(int x, int y, int largura, int altura) {
        JButton botao = new JButton();
        botao.setBounds(x, y, largura, altura);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setOpaque(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }
}