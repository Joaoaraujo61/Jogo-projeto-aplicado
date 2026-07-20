package componentes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GerenciadorMensagem {

    // MÉTODO PRINCIPAL (Onde a mágica do design acontece)
	// ==============================================================================================
	// MÉTODO PRINCIPAL COM PALETA ROXO, BRANCO E TONS PASTÉIS
	// ==============================================================================================
	public static void mostrarMensagem(Component pai, String mensagem, String titulo) {
	    
	    // 1. Encontrar a janela principal para centralizar o diálogo
	    Frame framePai = null;
	    if (pai != null) {
	        framePai = (pai instanceof Frame) ? (Frame) pai : (Frame) SwingUtilities.getWindowAncestor(pai);
	    }

	    // 2. Criar o JDialog do zero (totalmente limpo)
	    JDialog dialog = new JDialog(framePai, titulo, true);
	    dialog.setUndecorated(true); // Sem barras feias
	    dialog.setBackground(new Color(0, 0, 0, 0)); // Fundo transparente para os cantos arredondados

	    // Cores da Paleta Customizada (Tons Pastéis e Roxo)
	    Color corFundoDark = new Color(38, 34, 46);       // Roxo escuro pastel (Fundo da caixinha)
	    Color corBordaRoxa = new Color(157, 131, 199);     // Roxo pastel médio (Borda da janela)
	    Color corRoxoBotao = new Color(110, 77, 163);      // Roxo marcante para o texto do botão
	    Color corHoverBotao = new Color(240, 235, 248);  
	    Color corTeste = new Color(168, 171, 179); 
	    Color corTeste2 = new Color(179, 181, 189); 

	    // 3. Criar o painel de fundo personalizado
	    JPanel painelPrincipal = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g;
	            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	            // Sombra suave (Glow traseiro)
	            for (int i = 0; i < 5; i++) {
	                g2d.setColor(new Color(0, 0, 0, 10 - (i * 2)));
	                g2d.fillRoundRect(i, i, getWidth() - (i * 2), getHeight() - (i * 2), 20, 20);
	            }

	            // Fundo Arredondado Roxo Escuro Pastel
	            //g2d.setColor(corFundoDark);
	            //g2d.setColor(corTeste2);
	            g2d.setColor(Color.WHITE);
	            g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);

	            // Borda Fina em Roxo Pastel
	            g2d.setStroke(new BasicStroke(1.8f));
	            g2d.setColor(corBordaRoxa); 
	            g2d.drawRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
	        }
	    };
	    
	    painelPrincipal.setLayout(new BorderLayout(20, 20));
	    painelPrincipal.setOpaque(false);
	    painelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 25, 20, 25)); 

	    // 4. Configurar o Texto da Mensagem (Branco suave)
	    String textoHtml = "<html><body style='text-align: center; font-family: Segoe UI, sans-serif; font-size: 15pt; color: #6E4DA3;'>"
	            + mensagem.replaceAll("\n", "<br>") + "</body></html>";
	    //F5F4F7
	    JLabel labelMensagem = new JLabel(textoHtml, SwingConstants.CENTER);
	    painelPrincipal.add(labelMensagem, BorderLayout.CENTER);

	    // 5. Botão OK Customizado: Fundo Branco, Letra Roxa
	    JButton btnOk = new JButton("OK");
	    btnOk.setFont(new Font("Segoe UI", Font.BOLD, 12));
	    btnOk.setForeground(corRoxoBotao); // Letra roxa
	    //btnOk.setBackground(Color.WHITE); // Fundo branco puro
	    btnOk.setBackground(corTeste2);
	    btnOk.setFocusPainted(false);
	    btnOk.setBorder(BorderFactory.createEmptyBorder()); // Sem bordas duras
	    btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btnOk.setPreferredSize(new Dimension(100, 35));

	    // Efeito Hover: Quando passa o mouse, o branco ganha um reflexo roxo bem clarinho pastel
	    btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mouseEntered(java.awt.event.MouseEvent evt) { btnOk.setBackground(corHoverBotao); }
	        public void mouseExited(java.awt.event.MouseEvent evt) { btnOk.setBackground(Color.WHITE); }
	    });

	    // O importantíssimo fechar da janela!
	    btnOk.addActionListener(e -> dialog.dispose());

	    // Painel inferior para centralizar o botão
	    JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    painelBotao.setOpaque(false);
	    painelBotao.add(btnOk);
	    painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

	    // 6. Montar e exibir a janela
	    dialog.setContentPane(painelPrincipal);
	    dialog.pack();
	    
	    if (dialog.getWidth() < 380) { dialog.setSize(380, dialog.getHeight()); }
	    dialog.setLocationRelativeTo(pai);
	    
	    dialog.setVisible(true);
	}
    
    public static void mostrarMensagem(String mensagem, String titulo, int tipoIcone) {
        mostrarMensagem(null, mensagem, titulo);
    }

    public static void mostrarMensagem(Component pai, String mensagem) {
        mostrarMensagem(pai, mensagem, "Aviso");
    }

    public static void mostrarMensagem(String mensagem) {
        mostrarMensagem(null, mensagem, "Aviso");
    }

    public static void mostrarMensagem(Component pai, String mensagem, String titulo, int tipoIcone) {
        mostrarMensagem(pai, mensagem, titulo);
    }
}