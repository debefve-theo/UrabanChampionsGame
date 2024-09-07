import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class Game extends JPanel implements KeyListener {

    private JFrame window;
    private Sprites sprites;
    private AudioManager audioManagerMusic;
    private AnimationManager animationManager;
    private Clip musicClip;
    private int mode;
    private Player player1, player2;

    public Game(JFrame window, Sprites sprites, int mode) {
        this.window = window;
        this.sprites = sprites;
        this.mode = mode;

        setPreferredSize(new Dimension(1280, 960));

        player1 = new Player(1, sprites);
        player2 = new Player(2, sprites);

        audioManagerMusic = new AudioManager("src/sounds/track8.wav");
        //audioManagerMusic.startMusic();

        animationManager = new AnimationManager();

        addKeyListener(this);
        setFocusable(true);

        SwingUtilities.invokeLater(this::requestFocusInWindow);

        new GameLoop(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int currentBackgroundIndex = 0;
        g.drawImage(sprites.getSprite(currentBackgroundIndex, "street"), 0, 0, 1280, 678, null); // background

        //g.drawImage(sprites.getSprite(mode, "ath"), 0, 678, 1280, 288, null); // menu

        g.drawImage(sprites.getSprite(1, "event"), 300, 548, 340, 130, null); // police car

        g.drawImage(sprites.getSprite(0, "event"), 1082, 585, 199, 65, null); //221 72 *0,9
        g.drawImage(sprites.getSprite(0, "event"), 1, 585, 199, 65, null); //221 72 *0,9

        g.drawImage(sprites.getSprite(0, "player1"), 700, 700, 155, 160, null); //221 72 *0,9
        g.drawImage(sprites.getSprite(0, "player2"), 900, 700, 155, 160, null); //221 72 *0,9

        g.drawImage(player1.getCurrentSprite(), player1.getCurrentPosition(), 435, 155, 160, null);
        g.drawImage(player2.getCurrentSprite(), 780, 435, 155, 160, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            window.getContentPane().removeAll();
            window.add(new Menu(window, sprites));
            window.revalidate();
            window.repaint();
            audioManagerMusic.stopMusic();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player1.setState(PlayerState.MOVE_FORWARD);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player1.setState(PlayerState.MOVE_BACKWARD);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player1.setState(PlayerState.PUNCH_HEAD);
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            player1.setState(PlayerState.PUNCH_CHEST);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player1.setState(PlayerState.IDLE);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player1.setState(PlayerState.IDLE);
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            player1.setState(PlayerState.IDLE);
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            player1.setState(PlayerState.IDLE);
        }
    }

    public void updateGame() {
        player1.update();
        repaint();
    }
}
