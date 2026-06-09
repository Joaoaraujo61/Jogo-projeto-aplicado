package telas.Puzzle4;
import telas.MenuInicial;

//package telas;

import componentes.*;
import telas.FrameJanela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import componentes.Botao;
import componentes.CaixaDialogo;

import javax.swing.*;
import java.awt.*;


public class Puzzle4 extends JPanel{
	private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private ImageIcon imgRosangelaNormal;
    private Botao telefoneBtn;
    private JLabel labelCaixaTelefone;
    //Tetando add imagem da rosângela
   // private JLabel labelRosangelNormal;
    private CaixaDialogo caixaDialogo;

    public Puzzle4(JFrame frame) {
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        //Fundo
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        //Imagem Telefone
        this.imgTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/telefone.png"));
        this.telefoneBtn = new Botao(imgTelefone,1070,200);
        JButton botao = telefoneBtn.getBotaoClicavel();
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        this.imgTelefoneTocando = new ImageIcon(MenuInicial.class.getResource("/assets/telefoneTocando.png"));
        
        //Tentando adicionar a Imagem da  Rosângela normal
        /*ImageIcon rosangelaNormal = new ImageIcon(MenuInicial.class.getResource("/assets/RosangelaNormal.png"));
        this.imgRosangelaNormal = new JLabel(labelRosangelaNormal);*/
        

        //Caixa Telefone
        ImageIcon caixaTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/DialogoTelefone.png"));
        this.labelCaixaTelefone = new JLabel(caixaTelefone);
        this.caixaDialogo = new CaixaDialogo(caixaTelefone, 90, 500, Color.BLACK);

        labelFundo.add(telefoneBtn.getBotaoClicavel());

        add(labelFundo);

        tocarTelefone();
    }
    
    private String[] dialogoMae = {
	    "Alô, mãe?",
	    "Oi, filha? É você, meu bem?",
	    "<html>Sim, sou eu sim. Preciso de ajuda, eu sei que faz muito<br>tempo que não visito você, mas hoje eu insisti para o<br>ricardo para poder sair, mas ele saiu de casa e levou a<br> chave</html>",
	    "<html>Minha filha… Não acredito ser a sua voz que estou ouvin-<br>do, e acredito menos ainda que esse cara tenha feito isso<br> com a minha filinha.</html>",
	    "<html>Ah, mãe! Você sabe como é o Jorjão, ele tem esses desli-<br>zes, deve ter levado a chave sem querer e você sabe que<br> eu não tenho uma chave, porque ele se preocupa comigo,<br> tem medo de eu ser roubada, essas coisas…</html>",
	    "<html>Você entende, né!? O papai também era atencioso.</html>",
	    "<html>Meu amor, o seu pai era sim atencioso de verdade,<br> mas não possessivo como o seu marido. Eu não faço<br> ideia de como você acabou se casando com um cara<br> como o Jorjão.</html>",
	    "Mãe…",
	    "<html>Não, me deixa terminar. Nada do que ele faz é atenção,<br> meu amor, ele só quer te prender, te impedir de sair de<br> casa, de usar a roupa que tem vontade, de falar comigo,<br> só quer que você fique aí presa cuidando dele e da casa.</html>",
	    "Vem para cá, eu te ajudo!", 
	    "Como?",
    };
    
    private int indiceDaFala = 0;

    public void tocarTelefone(){
        Timer timer = new Timer(500, e ->{
                JButton botao = telefoneBtn.getBotaoClicavel();
                Icon iconeAtual = botao.getIcon();
                if (iconeAtual == imgTelefone) {
                    botao.setIcon(imgTelefoneTocando);
                } else {
                    botao.setIcon(imgTelefone);
                }
        });

        telefoneBtn.getBotaoClicavel().addActionListener(e -> atenderTelefone(timer));
        timer.start();
    }

    public void atenderTelefone(Timer timer) {
        timer.stop();
        telefoneBtn.getBotaoClicavel().setIcon(imgTelefone);

        labelFundo.add(caixaDialogo);
        //como add imagem da Rosângela?
        //labelFundo.add(imgRosangelaNormal);
        labelFundo.setComponentZOrder(caixaDialogo, 0);
        //labelFundo.setComponentZOrder(labelRosangelaNormal, 1);
        labelFundo.revalidate();
        labelFundo.repaint();

        //caixaDialogo.digitarTexto("Alô, mãe?");
       // caixaDialogo.digitarTexto("Oi, filha? É você, meu bem?");// ← seu texto
        if (indiceDaFala < dialogoMae.length) {
            
            // Pega a fala atual da lista e manda digitar
            caixaDialogo.digitarTexto(dialogoMae[indiceDaFala]);
            
            // Aumenta o contador (+1) para que, na próxima batida do Timer, venha a próxima fala
            indiceDaFala++; 
            
        } else {
            // Se as falas acabaram, o diálogo terminou! 
            // Agora sim nós paramos o Timer.
            timer.stop();
            
            // Reseta o contador para 0 (caso queira usar o telefone de novo no jogo)
            //indiceDaFala = 0; 
            
            // Opcional: Se quiser sumir com a caixa de diálogo quando acabar, descomente a linha abaixo:
             labelFundo.remove(caixaDialogo); labelFundo.repaint();
        }
        labelFundo.setComponentZOrder(caixaDialogo, 0);

    }
}