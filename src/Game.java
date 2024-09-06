import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class Game extends JPanel implements KeyListener {

    private JFrame window;
    private Sprites sprites;
    private Clip musicClip;
    private int mode;
    private Player player1;
    private Player player2;

    public Game(JFrame window, Sprites sprites, int mode) {
        this.window = window;
        this.sprites = sprites;
        this.mode = mode;
        setPreferredSize(new Dimension(1280, 960));

        player1 = new Player(1);
        player2 = new Player(2);

        try {
            File audioFile = new File("src/sounds/track8.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            musicClip = (Clip) AudioSystem.getLine(info);
            musicClip.open(audioStream);
            musicClip.start();

        } catch (IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        addKeyListener(this);
        setFocusable(true);

        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int currentBackgroundIndex = 0;
        g.drawImage(sprites.getSprite(currentBackgroundIndex, "street"), 0, 0, 1280, 678, null); // background

        g.drawImage(sprites.getSprite(mode, "ath"), 0, 678, 1280, 288, null); // menu

        g.drawImage(sprites.getSprite(1, "event"), 300, 548, 340, 130, null); // police car

        g.drawImage(sprites.getSprite(0, "event"), 1082, 585, 199, 65, null); //221 72 *0,9
        g.drawImage(sprites.getSprite(0, "event"), 1, 585, 199, 65, null); //221 72 *0,9

        g.drawImage(sprites.getSprite(20, "player1"), 800, 440, 120, 152, null); // 24*5 38*4
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            window.getContentPane().removeAll();
            window.add(new Menu(window, sprites));
            window.revalidate();
            window.repaint();
            stopMusic();
        }

        repaint();
    }

    private void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
