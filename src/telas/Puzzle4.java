package telas;

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
        this.caixaDialogo = new CaixaDialogo(caixaTelefone, 80, 500);

        labelFundo.add(telefoneBtn.getBotaoClicavel());

        add(labelFundo);

        tocarTelefone();
    }
    
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
    
    private int indiceDaFala;
    

    public void tocarTelefone(){
        Timer timerToque = new Timer(500, e ->{
                JButton botao = telefoneBtn.getBotaoClicavel();
                Icon iconeAtual = botao.getIcon();
                if (iconeAtual == imgTelefone) {
                    botao.setIcon(imgTelefoneTocando);
                } else {
                    botao.setIcon(imgTelefone);
                }
        });

        telefoneBtn.getBotaoClicavel().addActionListener(e -> atenderTelefone(timerToque));
        timerToque.start();
    }
    Timer timerDoDialogo = new Timer(4000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            // Se ainda tem fala na lista, mostra a próxima
            if (indiceDaFala < falasDoTelefone.length) {
                caixaDialogo.digitarTexto(falasDoTelefone[indiceDaFala]);
                indiceDaFala++;
            } else {
                // Se as falas acabaram, desliga ESSE timer do diálogo
                ((Timer)e.getSource()).stop();
                
                // Opcional: Se quiser sumir com a caixa quando a conversa acabar:
                // labelFundo.remove(caixaDialogo); labelFundo.repaint();
            }
            
        }
    });

    public void atenderTelefone(Timer timerToque, timerDialogo) {
        timerToque.stop();
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
        indiceDaFala = 0;
        caixaDialogo.digitarTexto(dialogoMae[indiceDaFala]);
        indiceDaFala++;
        
    
        
        // Liga o timer do diálogo!
        timerDoDialogo.start();
        labelFundo.setComponentZOrder(caixaDialogo, 0);

    }
}
