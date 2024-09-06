import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    private int id;
    private int roundWin = 0;
    private int stamina = 200;
    private BufferedImage[] playerSprites;

    public Player(int id) {
        this.id = id;

        if(this.id == 1) {
            try{
                BufferedImage spriteSheet = ImageIO.read(new File("src/sprites/characters.png"));
                playerSprites = new BufferedImage[27];

                playerSprites[0] = spriteSheet.getSubimage(10, 159, 24, 38);

            } catch (IOException e) {
                throw new RuntimeException();
            }
        } else if (this.id == 2) {
            int i = 0;
        }
    }

    public int getId() {
        return id;
    }

    public int getRoundWin() {
        return roundWin;
    }

    public void setRoundWin(int roundWin) {
        this.roundWin = roundWin;
    }

    public int getStamina() {
        return stamina;
    }

    public void resetStamina() {
        this.stamina = 200;
    }

    public BufferedImage getPlayerSprites(int i) {
        return playerSprites[i];
    }
}
