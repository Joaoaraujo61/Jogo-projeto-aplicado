    package telas.Puzzle1;

    import componentes.*;
    import telas.FrameJanela;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    
    import java.awt.AlphaComposite;
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.Color;

    public class Puzzle1 extends JPanel {
        private JLabel labelFundo;
        private CaixaDialogo caixaDialogo;
        private CaixaDialogo caixaPensamento;
        private  Personagem rosangela;
        private Personagem odete;
        private FrameJanela frame;
        private JLabel labelBarraAvanco;
        private Timer timerTelefone;
        private Telefone telefoneRef;
        
        private float alphaFade = 1.0f;

        private int etapa = 0;

        public Puzzle1(FrameJanela frame) {
            this.frame = frame;
         
            setLayout(null);
            setPreferredSize(new Dimension(1280, 720));
            
            //personagens
            this.odete = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/maeComFiltroAzul.png")), 250, 170);
            this.rosangela = new Personagem(new ImageIcon(Personagem.class.getResource("/assets/RosangelaNormal.png")), -70, 250);
            
            //Barra de progresso
            ImageIcon imgBarra = new ImageIcon(getClass().getResource("/assets/barras-de-avanco/0-6.png"));
            this.labelBarraAvanco = new JLabel(imgBarra);
            labelBarraAvanco.setBounds(0, 0, imgBarra.getIconWidth(), imgBarra.getIconHeight());
            labelBarraAvanco.setLayout(null);
            //Fundo
            ImageIcon fundo = new ImageIcon(Puzzle1.class.getResource("/assets/salaDeEstar-pixilart.png"));
            this.labelFundo = new JLabel(fundo);
            labelFundo.setBounds(0, 0, 1280, 720);
            labelFundo.setLayout(null);

            //Imagem Telefone
            Telefone telefone = new Telefone();
            telefone.getTelefoneBtn().getBotaoClicavel().setFocusable(false);

            //Caixa Telefone
           // ImageIcon imgCaixaTelefone = new ImageIcon(Puzzle1.class.getResource("/assets/DialogoTelefoneMae.png"));
            ImageIcon imgCaixaTelefone = new ImageIcon(Puzzle1.class.getResource("/assets/DialogoTelefone.png"));
            //this.caixaDialogo = new CaixaDialogo(imgCaixaTelefone, 101, 452, Color.BLACK);
            this.caixaDialogo = new CaixaDialogo(imgCaixaTelefone, 80, 500, Color.BLACK);

           // this.caixaDialogo.ajustarMargensTexto(125, 55);

            //Caixa Pensamento
            ImageIcon imgCaixaPensamento = new ImageIcon(Puzzle1.class.getResource("/assets/caixaDePensamento.png"));
            this.caixaPensamento = new CaixaDialogo(imgCaixaPensamento, 70, 500, Color.BLACK);
            caixaPensamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            add(labelFundo);
            labelFundo.add(telefone.getTelefoneBtn().getBotaoClicavel());
            labelFundo.add(labelBarraAvanco);
            //labelFundo.add(odete.getSprite());
         
            
            iniciarFadeIn();
            configurarAvancoGlobal();
            tocarTelefone(telefone);
        }

        private void configurarAvancoGlobal() {
            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                    KeyStroke.getKeyStroke("SPACE"), "avancarHistoria"
            );
            this.getActionMap().put("avancarHistoria", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tentarAvancoGlobal();
                }
            });

            // Clique em qualquer lugar do fundo: só avança se etapa >= 1
            labelFundo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tentarAvancoGlobal();
                }
            });
        }

        // Método "porteiro": só deixa passar se já passou da etapa do telefone
        private void tentarAvancoGlobal() {
            if (etapa >= 1) {
                avancaCena(timerTelefone, telefoneRef);
            }
        }

        public void tocarTelefone(Telefone telefone){
            this.timerTelefone = new Timer(500, e -> {
                JButton botao = telefone.getTelefoneBtn().getBotaoClicavel();
                Icon iconeAtual = botao.getIcon();
                if (iconeAtual == telefone.getImgTelefone()) {
                    botao.setIcon(telefone.getImgTelefoneTocando());
                } else {
                    botao.setIcon(telefone.getImgTelefone());
                }
            });

            telefone.getTelefoneBtn().getBotaoClicavel().addActionListener(e -> {
                if (etapa == 0) {
                    avancaCena(timerTelefone, telefone); // única porta de saída da etapa 0
                }
            });

            timerTelefone.start();
        }
        
        private void iniciarFadeIn() {
            JPanel painelFade = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaFade));
                    g2d.setColor(Color.BLACK); 
                    g2d.fillRect(0, 0, 1280, 720);
                    g2d.dispose();
                }
            };
            
            painelFade.setBounds(0, 0, 1280, 720);
            painelFade.setOpaque(false);
            this.add(painelFade);
            this.setComponentZOrder(painelFade, 0); 

          
            Timer timerFade = new Timer(20, e -> {
                alphaFade -= 0.02f;
                
                if (alphaFade <= 0.0f) {
                    alphaFade = 0.0f;
                    ((Timer)e.getSource()).stop(); // Para o timer
                    this.remove(painelFade); 
                }
                repaint();
            });
            
            timerFade.start();
        }


        public void avancaCena(Timer timer,Telefone telefone){
            switch (etapa){
                case 0:
                	labelFundo.add(odete.getSprite());
                    labelFundo.remove(telefone.getTelefoneBtn().getBotaoClicavel());
                    labelFundo.add(telefone.getTelefoneBtn().getBotaoClicavel());
                    rosangela.atenderTelefone(timer,telefone.getTelefoneBtn(), telefone.getImgTelefone(), labelFundo,caixaDialogo);
                    etapa++;
                    break;
                case 1:
                    rosangela.desligarTelefone(labelFundo,caixaDialogo, odete);
                    rosangela.escreverDialogo(labelFundo, caixaPensamento, "Preciso sair de casa, nem que seja pela primeira vez este mês,<br> preciso mesmo visitar a minha mãe, saber dessa oportunidade <br> de emprego, faz tanto tempo que eu não trabalho");
                    labelFundo.add(rosangela.getSprite());
                    labelFundo.revalidate();
                    labelFundo.repaint();
                    etapa++;
                    break;
                case 2:
                    rosangela.escreverDialogo(labelFundo, caixaPensamento, "Mas como eu vou pedir isso para ele? Ele não vai deixar. <br>Ele não vai me autorizar ir.");
                    etapa++;
                    break;
                case 3:
                    rosangela.escreverDialogo(labelFundo, caixaPensamento, "Posso começar pensando no que dizer para ele, quem sabe ...");
                    etapa++;
                    break;
                case 4:
                    frame.trocarTela(new Puzzle1Escolha(frame));;
                    JOptionPane.showMessageDialog(
                            frame,
                            "Puzzle 1: Escolha a opcão certa para conseguir a permissão para sair.",
                            "Instrução",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    etapa++;
                    break;
            }
        }
    }

