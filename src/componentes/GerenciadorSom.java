package componentes;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por carregar e tocar sons do jogo (WAV).
 *
 * Por que centralizar aqui?
 * - Evita recarregar o mesmo arquivo de som toda vez que ele toca.
 * - Permite controlar a trilha de fundo (tocar/parar/trocar) de um único lugar,
 *   o que é útil quando o FrameJanela troca de tela (trocarTela).
 */
public class GerenciadorSom {

    // Cache de efeitos já carregados, para não ler do disco toda hora
    private static final Map<String, Clip> cacheEfeitos = new HashMap<>();

    // Referência à trilha de fundo atual, para poder pará-la ao trocar de tela
    private static Clip trilhaAtual;

    // Guarda a porcentagem de volume atualmente aplicada à trilha (1.0f = 100%)
    private static float volumeAtual = 1.0f;

    /**
     * Toca um efeito sonoro curto UMA vez (ex: clique de botão, passo, notificação).
     * @param caminhoResource caminho dentro de /resources, ex: "/assets/sons/click.wav"
     */
    public static void tocarEfeito(String caminhoResource) {
        try {
            Clip clip = cacheEfeitos.get(caminhoResource);

            if (clip == null) {
                clip = carregarClip(caminhoResource);
                cacheEfeitos.put(caminhoResource, clip);
            }

            // Se o efeito já estava tocando, volta ao início antes de tocar de novo
            clip.stop();
            clip.setFramePosition(0);
            clip.start();

        } catch (Exception e) {
            System.err.println("Erro ao tocar efeito: " + caminhoResource);
            e.printStackTrace();
        }
    }

    /**
     * Toca uma trilha sonora em LOOP contínuo, no volume normal (100%).
     * Para automaticamente a trilha anterior, se houver uma tocando.
     * @param caminhoResource caminho dentro de /resources, ex: "/assets/sons/trilha.wav"
     */
    public static void tocarTrilha(String caminhoResource) {
        tocarTrilha(caminhoResource, 1.0f);
    }

    /**
     * Toca uma trilha sonora em LOOP contínuo, com volume ajustado por porcentagem.
     * Para automaticamente a trilha anterior, se houver uma tocando.
     *
     * @param caminhoResource caminho dentro de /resources, ex: "/assets/sons/trilha.wav"
     * @param volumePorcentagem 1.0f = 100% (volume original), 0.65f = 65% (35% mais baixo), etc.
     */
    public static void tocarTrilha(String caminhoResource, float volumePorcentagem) {
        try {
            pararTrilha(); // garante que só toca uma trilha por vez

            trilhaAtual = carregarClip(caminhoResource);
            ajustarVolume(trilhaAtual, porcentagemParaDecibeis(volumePorcentagem));
            volumeAtual = volumePorcentagem; // guarda para consulta em outras classes
            trilhaAtual.loop(Clip.LOOP_CONTINUOUSLY);
            trilhaAtual.start();

        } catch (Exception e) {
            System.err.println("Erro ao tocar trilha: " + caminhoResource);
            e.printStackTrace();
        }
    }

    /**
     * Ajusta o volume da trilha que já está tocando, sem reiniciá-la do zero.
     * @param volumePorcentagem 1.0f = 100%, 0.65f = 65%, etc.
     */
    public static void ajustarVolumeTrilhaAtual(float volumePorcentagem) {
        if (trilhaAtual != null) {
            ajustarVolume(trilhaAtual, porcentagemParaDecibeis(volumePorcentagem));
            volumeAtual = volumePorcentagem;
        }
    }

    /**
     * Retorna o volume atual da trilha, em porcentagem (1.0f = 100%, 0.65f = 65%).
     * Pode ser chamado de qualquer classe do projeto, ex:
     * float volume = GerenciadorSom.getVolumeAtual();
     */
    public static float getVolumeAtual() {
        return volumeAtual;
    }

    /**
     * Converte uma porcentagem de volume (ex: 0.65f = 65%) para decibéis,
     * já que o controle de volume do Java Sound trabalha em escala logarítmica (dB),
     * e não em escala linear de porcentagem.
     */
    private static float porcentagemParaDecibeis(float porcentagem) {
        if (porcentagem <= 0f) {
            return -80f; // valor bem baixo, praticamente silêncio
        }
        return (float) (20.0 * Math.log10(porcentagem));
    }

    /** Para a trilha de fundo atual, se estiver tocando. */
    public static void pararTrilha() {
        if (trilhaAtual != null) {
            trilhaAtual.stop();
            trilhaAtual.close();
            trilhaAtual = null;
        }
    }

    /**
     * Ajusta o volume de um clip (em decibéis, de -80.0 a 6.0 aproximadamente).
     * Útil para não deixar sons "estourando".
     */
    public static void ajustarVolume(Clip clip, float decibeis) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl controle = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            controle.setValue(decibeis);
        }
    }

    // Método interno que faz o trabalho pesado: ler o arquivo e abrir o Clip
    private static Clip carregarClip(String caminhoResource)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        URL url = GerenciadorSom.class.getResource(caminhoResource);
        if (url == null) {
            throw new IOException("Arquivo de som não encontrado: " + caminhoResource);
        }

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }
}