package telas;

import javax.swing.*;
import componentes.Botao;
import telas.Puzzle2.Puzzle2Final;
import java.awt.*;

public class Puzzle5 extends JPanel {
    private FrameJanela frame;
    private JLabel fundo;
    private JLabel labelVisorSenha;
    private String senhaDigitada = "";
    private final String SENHA = "581831";
    private JLabel labelFundo;
    private JLabel labelBarraAvanco;
    private JLabel labelImgTelefone;
    private ImageIcon imgArmarioSozinho;
    private Botao btnArmarioSozinho;

    public Puzzle5(FrameJanela frame) {
        this(frame, false);
    }

    /**
     * @param abrirArmarioDireto true = pula direto para a tela do armário aberto
     *                           (usado ao voltar do Puzzle2Final, pra não obrigar
     *                           o jogador a clicar no armário de novo).
     */
    public Puzzle5(FrameJanela frame, boolean abrirArmarioDireto) {
        this.frame = frame;
        //this.labelVisorSenha = labelVisorSenha;

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

        // Se o jogador voltou do Puzzle2Final, pula direto pra tela do armário aberto
        if (abrirArmarioDireto) {
            mostrarArmario();
        }
    }
    private void mostrarArmario() {
        trocarFundo("/assets/ArmarioZoom.png");

        JButton cofre = criarBotao(430, 200, 420, 360);
        cofre.addActionListener(e -> mostrarCofreFechado());
        fundo.add(cofre);

        // Seta que leva para o Puzzle2Final. Passa "true" para que o
        // Puzzle2Final saiba que o jogador veio daqui e mostre a seta de volta.
        // OBS: ajuste "/assets/seta.png" para o caminho real do seu asset.
        ImageIcon imgSeta = new ImageIcon(getClass().getResource("/assets/seta.png"));
        JButton botaoSeta = criarBotao(1130, 600, imgSeta.getIconWidth(), imgSeta.getIconHeight());
        botaoSeta.setIcon(imgSeta);
        botaoSeta.addActionListener(e -> frame.trocarTela(new Puzzle2Final(frame, true)));
        fundo.add(botaoSeta);
    }

    // 2. Dentro do seu construtor ou método de inicialização:
    public void configurarVisorCofre(JLabel labelFundoCofre) {
        // Cria o rótulo de texto com uma fonte que lembre um cofre/digital
        this.labelVisorSenha = new JLabel("");

        // Customize o estilo: Cor, Fonte e Alinhamento Centralizado
        labelVisorSenha.setForeground(Color.GREEN); // Ou Color.BLACK, depende do seu asset
        labelVisorSenha.setFont(new Font("Monospaced", Font.BOLD, 30)); // Fonte estilo relógio digital
        labelVisorSenha.setHorizontalAlignment(SwingConstants.CENTER);

        // CALIBRAÇÃO: Defina a posição exata onde fica o "vidro" do visor no seu asset
        // Exemplo: X=500, Y=200, Largura=200, Altura=50
        labelVisorSenha.setBounds(657, 284, 134, 45);

        // Adicione o visor EM CIMA da imagem de fundo do cofre
        //labelFundoCofre.add(labelVisorSenha);
        fundo.add(labelVisorSenha);

        // Garante que o visor fique na camada da frente
        labelFundoCofre.setComponentZOrder(labelVisorSenha, 0);
        fundo.setComponentZOrder(labelVisorSenha, 0);
    }
    private void mostrarCofreFechado() {
        senhaDigitada = "";

        trocarFundo("/assets/CofreFechadoVisor.png");

        configurarVisorCofre(fundo);
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    frame,
                    "Use a combinação achada durante o jogo",
                    "Instrução",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
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
                labelVisorSenha.setText(senhaDigitada);
            }
        });
        fundo.add(apagar);
    }

    private void criarBotaoNumero(String numero, int x, int y) {
        JButton botao = criarBotao(x, y, 38, 38);
        botao.addActionListener(e -> {
            if (senhaDigitada.length() < 6) {
                senhaDigitada += numero;
                labelVisorSenha.setText(senhaDigitada);
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
            labelVisorSenha.setText(senhaDigitada);
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