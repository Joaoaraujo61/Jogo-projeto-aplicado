package telas;

import componentes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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
    private boolean telefoneAtendido = false;

    public Puzzle4(FrameJanela frame) {

        this.frame =  frame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        //Fundo
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);

        //this.rosangela = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/RosangelaNormal.png")), -120, 230);
        this.rosangela = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/rosagela-vestido-cinza.png")), -120, 230);
        this.odete = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/maeComFiltroAzul.png")), 250, 170);

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
        botao.setFocusable(false); 
        this.imgTelefoneTocando = new ImageIcon(MenuInicial.class.getResource("/assets/telefoneTocando.png"));


        //Caixa Telefone
        ImageIcon caixaTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/DialogoTelefone.png"));
        this.labelCaixaTelefone = new JLabel(caixaTelefone);
        this.caixaDialogo = new CaixaDialogo(caixaTelefone, 90, 500, Color.BLACK);

        labelFundo.add(telefoneBtn.getBotaoClicavel());

        add(labelFundo);

        configurarAvancoGlobal();
        tocarTelefone();
    }

    private void configurarAvancoGlobal() {
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("SPACE"), "avancarDialogo"
        );
        this.getActionMap().put("avancarDialogo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tentarAvancoGlobal();
            }
        });


        labelFundo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tentarAvancoGlobal();
            }
        });
    }
    private void tentarAvancoGlobal() {
        if (telefoneAtendido) {
            avancarDialogoMae();
        }
    }

    private String[] dialogoMae = {
            "Alô, mãe?",
            "Oi, filha? É você, meu bem?",
            "Sim, sou eu mesma. Preciso da sua ajuda.",
            "O que aconteceu, minha filha?",
            "<html>Eu insisti pro Jorjão me deixar sair hoje,mas ele saiu de casa e<br> levou a chave.</html>",
            "<html>Não acredito que esse homem fez isso com a minha filhinha...</html>",
            "<html>Ah, mãe, você sabe como ele é,deve ter levado a chave<br> sem querer.</html>",
            "<html>Sem querer? Ele te tranca dentro de casa e você ainda sai<br> em defesa dele?</html>",
            "Ele só se preocupa comigo, tem medo que eu seja roubada...",
            "<html>O seu pai era atencioso de verdade, não possessivo como<br> esse seu marido.</html>",
            "Mãe…",
            "<html>Deixa eu terminar. Nada do que ele faz é cuidado, ele só<br> quer te prender.</html>",
            "E como eu saio daqui? Nem dinheiro eu tenho!",
            "<html>Deve ter algum dinheiro guardado na casa. Pega um ônibus<br> e vem pra cá, eu te ajudo.</html>",
            "<html>Tá bem. Vou procurar nos armários da sala, deve ter algum<br> dinheiro do Jorjão por aqui.</html>",
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

        telefoneBtn.getBotaoClicavel().addActionListener(e -> {
            if (!telefoneAtendido) {
                atenderTelefone(timer);
            }
        });
        timer.start();
    }

    public void atenderTelefone(Timer timer) {
        timer.stop();
        telefoneBtn.getBotaoClicavel().setIcon(imgTelefone);
        telefoneAtendido = true; // libera o avanço por clique/espaço a partir daqui

        labelFundo.add(caixaDialogo);
        labelFundo.setComponentZOrder(caixaDialogo, 1);
        labelFundo.revalidate();
        labelFundo.repaint();

        avancarDialogoMae();
    }

    // Mostra a próxima fala do diálogo, ou troca de tela quando ele acaba.
    // Chamado tanto ao atender o telefone quanto pelo avanço global (clique/espaço).
    private void avancarDialogoMae() {
        if (indiceDaFala < dialogoMae.length) {
            caixaDialogo.digitarTexto(dialogoMae[indiceDaFala]);
            indiceDaFala++;
            labelFundo.remove(telefoneBtn.getBotaoClicavel());
            if (indiceDaFala % 2 == 0) {
                labelFundo.add(odete.getSprite());
                labelFundo.remove(rosangela.getSprite());
                labelFundo.repaint();
            } else {
                labelFundo.add(rosangela.getSprite());
                labelFundo.remove(odete.getSprite());
                labelFundo.repaint();
            }
            labelFundo.add(telefoneBtn.getBotaoClicavel());
        } else {
            // Primeira chegada ao Puzzle5: entra na tela normal (sem zoom no armário)
            frame.trocarTela(new Puzzle5(frame));
            labelFundo.remove(caixaDialogo);
            labelFundo.repaint();
        }
        labelFundo.setComponentZOrder(caixaDialogo, 0);
    }
}