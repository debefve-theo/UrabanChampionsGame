import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements KeyListener {

    private int selectedOption = 0;
    private JFrame window;
    private Sprites sprites;
    private AudioManager audioManager;

    public Menu(JFrame window, Sprites sprites) {
        this.window = window;
        this.sprites = sprites;

        setPreferredSize(new Dimension(1280, 960)); // *5 - *4

        audioManager = new AudioManager("src/sounds/track1.wav");
        //audioManager.startMusic();

        addKeyListener(this);
        setFocusable(true);
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(sprites.getSprite(0, "menu"), 0, 0, 1280, 576,null);
        g.drawImage(sprites.getSprite(1, "menu"), 0, 576, 1280, 384, null);

        if (selectedOption == 0) {
            g.drawImage(sprites.getSprite(2, "menu"), 140, 596, 75, 60, null);  // x-596 y-140 up // x-660 y-140 down
            g.drawImage(sprites.getSprite(3, "menu"), 140, 660, 75, 60, null);
        } else {
            g.drawImage(sprites.getSprite(2, "menu"), 140, 660, 75, 60, null);  // x-596 y-140 up // x-660 y-140 down
            g.drawImage(sprites.getSprite(3, "menu"), 140, 596, 75, 60, null);
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
            window.add(new Game(window, sprites, selectedOption));
            window.revalidate();
            window.repaint();
            audioManager.stopMusic();
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}