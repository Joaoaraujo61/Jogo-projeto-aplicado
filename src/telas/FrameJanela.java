package telas;

import javax.swing.*;

public class FrameJanela extends JFrame {

    public FrameJanela(){
        setTitle("Rota de Fuga");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setContentPane(new MenuInicial(this));
        
        pack(); // ← deixa o frame se ajustar ao tamanho do painel
        setLocationRelativeTo(null);
        setVisible(true);
        
        /*// coloca o menu na janela 
        setContentPane(new CenaInicial(this));
        //
        
        
            // 1. Configurações básicas da janela principal
           /*setTitle("Meu Jogo");
            setSize(800, 600); 
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); // Centraliza a janela na tela */

            // 2. Coloca a CenaInicial para aparecer logo de cara
            //CenaInicial cenaInicial = new CenaInicial(this);
           // this.add(cenaInicial);
           

            // 3. Mostra a janela já com a cena inicial dentro dela
            setVisible(true);
            //CenaInicial.contarHistoria();*/
        }

        // Esse é o método que você vai chamar DEPOIS para atualizar para o menu
        public void irParaMenu() {
            this.getContentPane().removeAll(); // Remove a CenaInicial da tela
            
            // Cria e adiciona o seu painel de menu principal
            // (Substitua 'MenuJogo' pelo nome correto da sua classe de menu)
            JPanel menu = new MenuInicial(this); 
            this.add(menu);
            
            // Avisa o Java para redesenhar a tela com o novo conteúdo
            this.revalidate();
            this.repaint();
      
    }

    public void trocarTela(JPanel tela){
        setContentPane(tela);
        revalidate();
        repaint();
    }
}
