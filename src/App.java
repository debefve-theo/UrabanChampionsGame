import javax.swing.*;
import java.awt.event.KeyAdapter;

public class App {

    private static void initWindow() {
        JFrame window = new JFrame("Urban Champion");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Menu menu = new Menu(window);
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