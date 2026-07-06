package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Puzzle3Jogavel extends JPanel {
	private static final long serialVersionUID = 1L;
	private FrameJanela frame; 
    private JLabel labelFundo;
    private JLabel labelFiosDireita;
    private JLabel labelFiosConectados;
    private JLabel labelFioVerde;
    private JLabel labelFioVermelho;
    private JLabel labelFioAzul;
    
    
    private final Point[] slotsFios = {
        new Point(358, 260), // Slot 0
        new Point(358, 350), // Slot 1
        new Point(358, 420)  // Slot 2
    };
    private int[] slotAtualDoFio = {0, 1, 2};

    private JLabel[] labelFios = new JLabel[3];

    public Puzzle3Jogavel(FrameJanela frame) {
        this.frame = frame; 
        
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));
        
        // Fios Fixos da Direita
        ImageIcon imgFiosDireita = new ImageIcon(getClass().getResource("/assets/fiosDireita.png"));
        this.labelFiosDireita = new JLabel(imgFiosDireita);
        labelFiosDireita.setBounds(358, 158, imgFiosDireita.getIconWidth(), imgFiosDireita.getIconHeight());
        this.add(labelFiosDireita); 
        
        // Fios Conectados
        ImageIcon imgFiosConectados = new ImageIcon(getClass().getResource("/assets/fiosConectados.png"));
        this.labelFiosConectados = new JLabel(imgFiosConectados);
        labelFiosConectados.setBounds(358, 158, imgFiosConectados.getIconWidth(), imgFiosConectados.getIconHeight());
        //this.add(labelFiosConectados); 
        
        // Fio Azul (ID 0)
        ImageIcon imgfioAzul = new ImageIcon(getClass().getResource("/assets/fioAzul.png"));
        this.labelFioAzul = new JLabel(imgfioAzul);
        labelFioAzul.setBounds(slotsFios[0].x, slotsFios[0].y, imgfioAzul.getIconWidth(), imgfioAzul.getIconHeight());
        this.labelFios[0] = this.labelFioAzul; 
        configurarFioComTroca(this.labelFioAzul, 0); 
        this.add(labelFioAzul); 
        
        // Fio Verde (ID 1)
        ImageIcon imgFioVerde = new ImageIcon(getClass().getResource("/assets/fioVerde.png"));
        this.labelFioVerde = new JLabel(imgFioVerde);
        labelFioVerde.setBounds(slotsFios[1].x, slotsFios[1].y, imgFioVerde.getIconWidth(), imgFioVerde.getIconHeight());
        this.labelFios[1] = this.labelFioVerde; 
        configurarFioComTroca(this.labelFioVerde, 1); 
        this.add(labelFioVerde); 
        
        // Fio Vermelho (ID 2)
        ImageIcon imgfioVermelho = new ImageIcon(getClass().getResource("/assets/fioVermelho.png"));
        this.labelFioVermelho = new JLabel(imgfioVermelho);
        labelFioVermelho.setBounds(slotsFios[2].x, slotsFios[2].y, imgfioVermelho.getIconWidth(), imgfioVermelho.getIconHeight());
        this.labelFios[2] = this.labelFioVermelho; 
        configurarFioComTroca(this.labelFioVermelho, 2); 
        this.add(labelFioVermelho); 
      
        // Imagem de Fundo
        ImageIcon fundo = new ImageIcon(MenuInicial.class.getResource("/assets/FundoCaixadeFios.png"));
        this.labelFundo = new JLabel(fundo);
        labelFundo.setBounds(0, 0, 1280, 720);
        this.add(labelFundo); 
  
        // Configura a ordem das camadas
        this.setComponentZOrder(labelFundo, this.getComponentCount() - 1); 
        this.setComponentZOrder(labelFioAzul, 0);
        this.setComponentZOrder(labelFioVerde, 1);
        this.setComponentZOrder(labelFioVermelho, 2);
        this.setComponentZOrder(labelFiosDireita, 3);
    } 
    
    private void configurarFioComTroca(JLabel fioInstancia, int idDoFio) {
        final Point cliqueInicial = new Point();
        final Point posicaoOriginal = new Point(); 

        fioInstancia.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cliqueInicial.setLocation(e.getPoint());
                posicaoOriginal.setLocation(fioInstancia.getLocation()); 
                fioInstancia.getParent().setComponentZOrder(fioInstancia, 0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int slotMaisProximo = -1;
                double menorDistancia = 100.0; 

                for (int i = 0; i < slotsFios.length; i++) {
                    double dist = fioInstancia.getLocation().distance(slotsFios[i]);
                    if (dist < menorDistancia) {
                        menorDistancia = dist;
                        slotMaisProximo = i;
                    }
                }

                int slotAntigo = slotAtualDoFio[idDoFio];
                
                if (slotMaisProximo != -1 && slotMaisProximo != slotAntigo) {
                    int fioParaTrocar = -1;
                    for (int f = 0; f < slotAtualDoFio.length; f++) {
                        if (slotAtualDoFio[f] == slotMaisProximo) {
                            fioParaTrocar = f;
                            break;
                        }
                    }

                    if (fioParaTrocar != -1) {
                        labelFios[fioParaTrocar].setLocation(slotsFios[slotAntigo]);
                        slotAtualDoFio[fioParaTrocar] = slotAntigo;

                        fioInstancia.setLocation(slotsFios[slotMaisProximo]);
                        slotAtualDoFio[idDoFio] = slotMaisProximo;
                      
                        
                        verificarPuzzleResolvido();
                    }
                } else {
                    fioInstancia.setLocation(posicaoOriginal);
                }
                
                fioInstancia.getParent().repaint();
            }
        });

        fioInstancia.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xJanela = fioInstancia.getX() + e.getX();
                int yJanela = fioInstancia.getY() + e.getY();
                fioInstancia.setLocation(xJanela - cliqueInicial.x, yJanela - cliqueInicial.y);
                fioInstancia.getParent().repaint();
            }
        });
    }

    private void verificarPuzzleResolvido() {	
        if (slotAtualDoFio[0] == 1 && slotAtualDoFio[1] == 2 && slotAtualDoFio[2] == 0) {
        	this.add(labelFiosConectados);
        	this.remove(labelFiosDireita);
        	labelFioAzul.setVisible(false);
        	labelFioVerde.setVisible(false);
        	labelFioVermelho.setVisible(false);
            this.setComponentZOrder(labelFiosConectados, 3);
          
        	   Timer timer = new Timer(500, evento -> {
                   ((Timer) evento.getSource()).stop();
                   SwingUtilities.invokeLater(() -> {
                       JOptionPane.showMessageDialog(
                               SwingUtilities.getWindowAncestor(Puzzle3Jogavel.this),
                               "Puzzle concluído!"
                       );                      
                       frame.trocarTela(new Puzzle4(frame));
                   });
               });

               timer.setRepeats(false);
               timer.start();
          
    	}
      }
}




