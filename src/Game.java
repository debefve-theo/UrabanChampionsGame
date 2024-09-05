import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JPanel implements KeyListener {

    private final BufferedImage[] backgrounds;
    private final BufferedImage[] menuSprites;
    private JFrame window;

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

        } catch (IOException e) {
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
            g.drawImage(backgrounds[currentBackgroundIndex], 0, 0, 1280, 678, null);
        }

        g.drawImage(menuSprites[0], 0, 678, 1280, 288, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            window.getContentPane().removeAll();
            window.add(new Menu(window));
            window.revalidate();
            window.repaint();
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
