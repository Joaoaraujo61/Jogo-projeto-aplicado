package telas;

import componentes.*;

import javax.swing.*;
import java.awt.*;


public class Puzzle4 extends JPanel{

    private FrameJanela frame;
    private JLabel labelFundo;
    private ImageIcon imgTelefone;
    private ImageIcon imgTelefoneTocando;
    private Botao telefoneBtn;
    private JLabel labelCaixaTelefone;
    private CaixaDialogo caixaDialogo;
    private  Personagem rosangela;
    private Personagem odete;
    private JLabel labelBarraAvanco;

    public Puzzle4(FrameJanela frame) {

        this.frame =  frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        //Fundo
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        this.rosangela = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/RosangelaNormal.png")), -120, 230);
        this.odete = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/Odete.png")), 737, 291);
        //this.odete = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/Odete2.png")), 850, 320);

        //Barra de progresso
        ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/3-6.png"));
        this.labelBarraAvanco = new JLabel(imgBarra);
        labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());
        labelBarraAvanco.setLayout(null);
        labelFundo.add(labelBarraAvanco);


        //Imagem Telefone
        this.imgTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/telefone.png"));
        this.telefoneBtn = new Botao(imgTelefone,1070,200);
        JButton botao = telefoneBtn.getBotaoClicavel();
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        this.imgTelefoneTocando = new ImageIcon(MenuInicial.class.getResource("/assets/telefoneTocando.png"));


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
            "<html>Sim, sou eu sim. Preciso de ajuda, eu sei que faz muito<br>tempo que não visito você, mas hoje eu insisti para o<br>Jorjão para poder sair, mas ele saiu de casa e levou a<br> chave.</html>",
            "<html>Minha filha… Não acredito ser a sua voz que estou ouvin-<br>do, e acredito menos ainda que esse cara tenha feito isso<br> com a minha filinha.</html>",
            "<html>Ah, mãe! Você sabe como é o Jorjão, ele tem esses desli-<br>zes, deve ter levado a chave sem querer e você sabe que<br> eu não tenho uma chave, porque ele se preocupa comigo,<br> tem medo de eu ser roubada, essas coisas…</html>",
            "<html>O seu pai era tão atencioso, e nem um pouco possessivo como<br> o seu marido. Eu não faço<br> ideia de como você acabou se casando com um cara<br> como o Jorjão.</html>",
            "Mãe…",
            "<html>Não, me deixa terminar. Nada do que ele faz é atenção,<br> meu amor, ele só quer te prender, te impedir de sair de<br> casa, de usar a roupa que tem vontade, de falar comigo,<br> só quer que você fique aí presa cuidando dele e da casa.</html>",
            "E como eu poderia sair daqui? Não posso!",
            "Deve ter algumd dinehrio na sua casa, pegue um ônibus, vem pra cá, eu te ajudo!",
            "Ok! Vou procurar nos armários aqui da sala, deve ter algum dinheiro do Jorjão aqui.",
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
        labelFundo.setComponentZOrder(caixaDialogo, 1);
        labelFundo.revalidate();
        labelFundo.repaint();

        if (indiceDaFala < dialogoMae.length) {
            caixaDialogo.digitarTexto(dialogoMae[indiceDaFala]);
            indiceDaFala++;
            if(indiceDaFala % 2 == 0) {
                labelFundo.add(odete.getSprite());
                labelFundo.remove(rosangela.getSprite());
            }else {
                labelFundo.add(rosangela.getSprite());
                labelFundo.remove(odete.getSprite());
            }

        } else {

            timer.stop();
            frame.trocarTela(new Puzzle5(frame, false));
            labelFundo.remove(caixaDialogo); labelFundo.repaint();
        }
        labelFundo.setComponentZOrder(caixaDialogo, 0);

    }
}