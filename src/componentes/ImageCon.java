package componentes;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageCon {

        public ImageIcon vestidoRasgadoNoCabide =
                new ImageIcon(getClass().getResource("/assets/VestidoRasgadoNoCabide.png"));

        public ImageIcon vestidoRasgado =
                new ImageIcon(getClass().getResource("/assets/VestidoRasgado.png"));

        public ImageIcon calendario =
                new ImageIcon(getClass().getResource("/assets/calendario.png"));

        public ImageIcon calendarioZoom =
                new ImageIcon(getClass().getResource("/assets/CalendarioZoom.png"));

        public ImageIcon rosangelaVestidoNovo =
            new ImageIcon(getClass().getResource("/assets/rosagela-vestido-cinza.png"));

    public ImageIcon seta =
            new ImageIcon(getClass().getResource("/assets/seta.png"));

    public BufferedImage agulha; // não inicializa inline — precisa de try/catch

    public ImageCon() {
        BufferedImage img = null;
        try {
            BufferedImage original = ImageIO.read(getClass().getResourceAsStream("/assets/agulha.png"));

            Dimension tamanhoMaximo = Toolkit.getDefaultToolkit().getBestCursorSize(original.getWidth(), original.getHeight());

            // Cria imagem ARGB do tamanho aceito pelo SO
            BufferedImage argb = new BufferedImage(tamanhoMaximo.width, tamanhoMaximo.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = argb.createGraphics();

            // Limpa com transparência total antes de desenhar
            g2d.setComposite(AlphaComposite.Clear);
            g2d.fillRect(0, 0, tamanhoMaximo.width, tamanhoMaximo.height);

            // Restaura composite padrão e desenha a agulha
            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(original, 0, 0, tamanhoMaximo.width, tamanhoMaximo.height, null);
            g2d.dispose();

            img = argb;

        } catch (IOException e) {
            System.err.println("Erro ao carregar agulha: " + e.getMessage());
        }
        this.agulha = img;
    }
}
