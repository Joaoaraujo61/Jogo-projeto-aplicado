package telas;

import javax.swing.*;

import componentes.Botao;

import java.awt.*;

public class Puzzle5 extends JPanel {

    private FrameJanela frame;
    private JLabel fundo;
    private String senhaDigitada = "";
    private final String SENHA = "581831";
    private JLabel labelFundo;
    private JLabel labelBarraAvanco;
    private JLabel labelImgTelefone;
    private ImageIcon imgArmarioSozinho;
    private Botao btnArmarioSozinho;
    
    public Puzzle5(FrameJanela frame) {
        this.frame = frame;

        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        
        //Fundo
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/salaDeEstar-pixilart.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        labelFundo.setLayout(null);
       
        //Imagem barra de avanço
        ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/4-6.png"));
        this.labelBarraAvanco = new JLabel(imgBarra);
        labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());
        labelBarraAvanco.setLayout(null);
        labelFundo.add(labelBarraAvanco);
        
        //Imagem Telefone
        ImageIcon imgTelefone = new ImageIcon(MenuInicial.class.getResource("/assets/telefone.png"));
        this.labelImgTelefone = new JLabel(imgTelefone);
        labelImgTelefone.setBounds(1070, 200, imgTelefone.getIconWidth(), imgTelefone.getIconHeight());
        labelImgTelefone.setLayout(null);
        labelFundo.add(labelImgTelefone);
        
      //Botão armário
        this.imgArmarioSozinho = new ImageIcon(MenuInicial.class.getResource("/assets/ArmarioSozinho.png"));
        this.btnArmarioSozinho = new Botao(imgArmarioSozinho, 111, 69);
        JButton botao = btnArmarioSozinho.getBotaoClicavel();
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        labelFundo.add(btnArmarioSozinho.getBotaoClicavel());
        
        
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    frame,
                    "Procure por dinheiro nesta sala",
                    "Instrução",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        
         add(labelFundo);
         btnArmarioSozinho.getBotaoClicavel().addActionListener(e -> mostrarArmario());
     
         
    }

    private void mostrarArmario() {
        trocarFundo("/assets/ArmarioZoom.png");

        JButton cofre = criarBotao(430, 200, 420, 360);
        cofre.addActionListener(e -> mostrarCofreFechado());

        fundo.add(cofre);
    }

    private void mostrarCofreFechado() {
        senhaDigitada = "";
        trocarFundo("/assets/CofreFechado.png");

        criarBotaoNumero("1", 680, 350);
        criarBotaoNumero("2", 722, 350);
        criarBotaoNumero("3", 760, 350);

        criarBotaoNumero("4", 680, 390);
        criarBotaoNumero("5", 722, 390);
        criarBotaoNumero("6", 760, 390);

        criarBotaoNumero("7", 680, 430);
        criarBotaoNumero("8", 722, 430);
        criarBotaoNumero("9", 760, 430);

        criarBotaoNumero("0", 722, 470);

        JButton enter = criarBotao(760, 470, 38, 38);
        enter.addActionListener(e -> verificarSenha());
        fundo.add(enter);

        JButton apagar = criarBotao(680, 470, 38, 38);
        apagar.addActionListener(e -> {
            if (senhaDigitada.length() > 0) {
                senhaDigitada = senhaDigitada.substring(0, senhaDigitada.length() - 1);
            }
        });
        fundo.add(apagar);
    }

    private void criarBotaoNumero(String numero, int x, int y) {
        JButton botao = criarBotao(x, y, 38, 38);
        botao.addActionListener(e -> {
            if (senhaDigitada.length() < 6) {
                senhaDigitada += numero;
                System.out.println("Senha digitada: " + senhaDigitada);
            }
        });
        fundo.add(botao);
    }

    private void verificarSenha() {
        if (senhaDigitada.equals(SENHA)) {
            mostrarCofreComDinheiro();
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta!");
            senhaDigitada = "";
        }
    }

    private void mostrarCofreComDinheiro() {
        trocarFundo("/assets/CofreAbertoDinheiro.png");

        JButton dinheiro = criarBotao(500, 390, 260, 150);
        dinheiro.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Você pegou o dinheiro!");
            mostrarCofreAberto();
        });

        fundo.add(dinheiro);
    }

    private void mostrarCofreAberto() {
        trocarFundo("/assets/CofreAberto.png");

        JOptionPane.showMessageDialog(this, "Puzzle 5 concluído!");
        frame.trocarTela(new Puzzle6(frame));
    }

    private void trocarFundo(String caminho) {
        removeAll();

        ImageIcon imagem = new ImageIcon(getClass().getResource(caminho));
        fundo = new JLabel(imagem);
        fundo.setBounds(0, 0, 1280, 720);
        fundo.setLayout(null);

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