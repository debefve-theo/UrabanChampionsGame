import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {

    private static <Image> void initWindow() {
        JFrame window = new JFrame("Urban Champion");

        try {
            BufferedImage icon = ImageIO.read(new File("src/sprites/icon.png"));
            window.setIconImage(icon);

            // MAC OS code
            Taskbar taskbar = Taskbar.getTaskbar();
            taskbar.setIconImage(icon);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Sprites sprites = new Sprites();

        Menu menu = new Menu(window, sprites);
        window.add(menu);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initWindow();
            }
        });
    }
}