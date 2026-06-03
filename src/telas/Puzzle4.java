package telas;

import componentes.Botao;
import componentes.CaixaDialogo;
import componentes.Personagem;
import componentes.Telefone;
import telas.Puzzle1.Puzzle1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Puzzle4 extends JPanel{
	//private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private ImageIcon imgRosangelaNormal;
    private Botao telefoneBtn;
    private JLabel labelCaixaTelefone;
    //Tetando add imagem da rosângela
   // private JLabel labelRosangelNormal;
   // private CaixaDialogo caixaDialogo;
    
    //
    private JLabel labelFundo;
    private CaixaDialogo caixaDialogo;
    private CaixaDialogo caixaPensamento;
    private  Personagem rosangela;
    private FrameJanela frame;

   /* public Puzzle4(JFrame frame) {
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
        
    public Puzzle4(FrameJanela frame) {
        this.frame = frame;
        this.rosangela = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/RosangelaNormal.png")), -70, 250);
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        //Fundo
        ImageIcon fundo = new ImageIcon(Puzzle1.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        //Imagem Telefone
        Telefone telefone = new Telefone();

        //Caixa Telefone
        ImageIcon imgCaixaTelefone = new ImageIcon(Puzzle1.class.getResource("/assets/DialogoTelefone.png"));
        this.caixaDialogo = new CaixaDialogo(imgCaixaTelefone, 80, 500, Color.white);

        //Caiaxa Pensamento
        ImageIcon imgCaixaPensamento = new ImageIcon(Puzzle1.class.getResource("/assets/caixaDePensamento.png"));
        this.caixaPensamento = new CaixaDialogo(imgCaixaPensamento, 70, 500, Color.BLACK);

        labelFundo.add(telefone.getTelefoneBtn().getBotaoClicavel());

        add(labelFundo);

        tocarTelefone(telefone);
    }
       /* //Caixa Telefone
        ImageIcon caixaTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/DialogoTelefone.png"));
        this.labelCaixaTelefone = new JLabel(caixaTelefone);
        this.caixaDialogo = new CaixaDialogo(caixaTelefone, 80, 500);

        labelFundo.add(telefoneBtn.getBotaoClicavel());

        add(labelFundo);

        tocarTelefone();
    }*/
    
    private String[] dialogoMae = {
	    "Alô, mãe?",
	    "Oi, filha? É você, meu bem?",
	    "Sim, sou eu sim. Preciso de ajuda, eu sei que faz muito tempo que não visito você, mas hoje eu insisti para o ricardo para poder sair, mas ele saiu de casa e levou a chave",
	    "Minha filha… Não acredito ser a sua voz que estou ouvindo, e acredito menos ainda que esse cara tenha feito isso com a minha filha.",
	    "Ah, mãe! Você sabe como é o Jorjão, ele tem esses deslizes, deve ter levado a chave sem querer e você sabe que eu não tenho uma chave, porque ele se preocupa comigo, tem medo de eu ser roubada, essas coisas… Você entende meu pai também era bem atencioso.",
	    " Meu amor, o seu pai era sim atencioso de verdade, mas não possessivo como o seu marido. Eu não faço ideia de como você acabou se casando com um cara como o Jorjão",
	    "Mãe…",
	    "Não, me deixa terminar. Nada do que ele faz é atenção, meu amor, ele só quer te prender, te impedir de sair de casa, de usar a roupa que tem vontade, de falar comigo, só quer que você fique aí presa cuidando dele e da casa.",
	    "Vem para cá, eu te ajudo!", 
	    "Como?",
    };
    
    private int indiceDaFala = 0;

    /*public void tocarTelefone(){
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
    }*/

    
    public void tocarTelefone(Telefone telefone){
        Timer timer = new Timer(500, e ->{
            JButton botao = telefone.getTelefoneBtn().getBotaoClicavel();
            Icon iconeAtual = botao.getIcon();
            if (iconeAtual == telefone.getImgTelefone()) {
                botao.setIcon(telefone.getImgTelefoneTocando());
            } else {
                botao.setIcon(telefone.getImgTelefone());
            }
        });

        telefone.getTelefoneBtn().getBotaoClicavel().addActionListener(e -> {
            if(etapa < 2){
                avancaCena(timer, telefone);
            }
                caixaPensamento.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (etapa >= 2) {
                            avancaCena(timer, telefone);
                        }
                    }
                });
        });
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
            indiceDaFala = 0; 
            
            // Opcional: Se quiser sumir com a caixa de diálogo quando acabar, descomente a linha abaixo:
            // labelFundo.remove(caixaDialogo); labelFundo.repaint();
        }
        labelFundo.setComponentZOrder(caixaDialogo, 0);

    }
}
