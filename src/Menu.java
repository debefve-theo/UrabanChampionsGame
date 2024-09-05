import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements KeyListener {

    private BufferedImage[] menuSprites;
    private int selectedOption = 0;
    private JFrame window;

    public Menu(JFrame window) {
        this.window = window;
        setPreferredSize(new Dimension(1280, 960)); // *5 - *4

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/sprites/characters.png"));
            menuSprites = new BufferedImage[4];

            menuSprites[0] = spriteSheet.getSubimage(0, 0, 256, 144);
            menuSprites[1] = spriteSheet.getSubimage(252, 360, 256, 96);
            menuSprites[2] = spriteSheet.getSubimage(280, 365, 15, 15);
            menuSprites[3] = spriteSheet.getSubimage(265, 365, 15, 15);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        addKeyListener(this);
        setFocusable(true);

        //requestFocusInWindow();
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(menuSprites[0], 0, 0, 1280, 576,null);
        g.drawImage(menuSprites[1], 0, 576, 1280, 384, null);

        if (selectedOption == 0) {
            g.drawImage(menuSprites[2], 140, 596, 75, 60, null);  // x-596 y-140 up // x-660 y-140 down
            g.drawImage(menuSprites[3], 140, 660, 75, 60, null);
        } else {
            g.drawImage(menuSprites[2], 140, 660, 75, 60, null);  // x-596 y-140 up // x-660 y-140 down
            g.drawImage(menuSprites[3], 140, 596, 75, 60, null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            selectedOption = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            selectedOption = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            window.getContentPane().removeAll();
            window.add(new Game(window, selectedOption));
            window.revalidate();
            window.repaint();
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}