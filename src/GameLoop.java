import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop {
    private Game game;

    public GameLoop(Game game) {
        this.game = game;

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.updateGame();
            }
        });
        timer.start();
    }
}
