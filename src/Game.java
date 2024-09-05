import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;

public class Game extends JPanel implements KeyListener {

    private final BufferedImage[] backgrounds;
    private final BufferedImage[] menuSprites;
    private final BufferedImage[] eventSprites;
    private JFrame window;
    private Clip musicClip;

    public Game(JFrame window, int mode) {
        this.window = window;
        setPreferredSize(new Dimension(1280, 960));

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/sprites/street.gif"));
            backgrounds = new BufferedImage[5];

            for (int i = 0; i < 5; i++) {
                backgrounds[i] = spriteSheet.getSubimage(i*256, 0, 256, 160);
            }

            BufferedImage spriteSheet2 = ImageIO.read(new File("src/sprites/characters.png"));
            menuSprites = new BufferedImage[1];

            if(mode == 0) {
                menuSprites[0] = spriteSheet2.getSubimage(256, 0, 256, 72);
            } else if (mode == 1) {
                menuSprites[0] = spriteSheet2.getSubimage(256, 73, 256, 72);
            }

            BufferedImage spriteSheet3 = ImageIO.read(new File("src/sprites/characters.png"));
            eventSprites = new BufferedImage[5];

            eventSprites[0] = makeColorTransparent(spriteSheet3.getSubimage(252, 318, 68, 26));
            eventSprites[1] = makeColorTransparent(spriteSheet3.getSubimage(319, 318, 68, 26));

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

        //requestFocusInWindow();
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgrounds != null && backgrounds.length > 0) {
            int currentBackgroundIndex = 0;
            g.drawImage(backgrounds[currentBackgroundIndex], 0, 0, 1280, 678, null); // menu
        }

        g.drawImage(menuSprites[0], 0, 678, 1280, 288, null); // background

        g.drawImage(eventSprites[0], 300, 548, 340, 130, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            window.getContentPane().removeAll();
            window.add(new Menu(window));
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

    public static BufferedImage makeColorTransparent(BufferedImage image) {
        Color colorToReplace = new Color(100,176,255);

        BufferedImage transparentImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                Color pixelColor = new Color(rgb, true);

                if (pixelColor.equals(colorToReplace)) {
                    transparentImage.setRGB(x, y, 0x00FFFFFF);
                } else {
                    transparentImage.setRGB(x, y, rgb);
                }
            }
        }

        return transparentImage;
    }
}
