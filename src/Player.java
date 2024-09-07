import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    private int id;
    private int stamina;
    private int roundWin;
    private PlayerState state;
    private int currentFrame;
    private int currentPosition;

    private BufferedImage[] spritesIdle;
    private BufferedImage[] spritesMoveForward;
    private BufferedImage[] spritesMoveBackward;
    private BufferedImage[] spritesPunchHead;
    private BufferedImage[] spritesPunchChest;
    private BufferedImage[] spritesHeadHit;
    private BufferedImage[] spritesChestHit;
    private BufferedImage[] spritesKo;
    private BufferedImage[] spritesDefend;
    private BufferedImage[] spritesDodge;
    private BufferedImage[] spritesPolicePatrol;
    private BufferedImage[] spritesPoliceCatch;
    private BufferedImage[] spritesDance;

    private int animationSpeed = 5;
    private int frameCounter = 0;

    public Player(int id, Sprites sprites) {
        this.id = id;
        this.stamina = 200;
        this.state = PlayerState.IDLE;
        this.currentFrame = 0;
        this.currentPosition = 200;

        setAnnimations(sprites);
    }

    public void setState(PlayerState state) {
        this.state = state;
        currentFrame = 0;
        frameCounter = 0;
    }

    public void update() {
        // Incrémente le compteur de frames
        frameCounter++;

        if (frameCounter >= animationSpeed) {
            currentFrame = (currentFrame + 1) % getFrameCountForState();
            frameCounter = 0;

            if (currentFrame == 0 && state == PlayerState.MOVE_FORWARD) {
                currentFrame = 0;
            }
            if (currentFrame == 0 && state == PlayerState.MOVE_BACKWARD) {
                currentFrame = 0;
            }
        }

        if (state == PlayerState.MOVE_FORWARD) {
            currentPosition += 5;
        }
        if (state == PlayerState.MOVE_BACKWARD) {
            currentPosition -= 5;
        }
    }

    public BufferedImage getCurrentSprite() {
        switch (state) {
            case MOVE_FORWARD:
                return spritesMoveForward[currentFrame];
            case MOVE_BACKWARD:
                return spritesMoveBackward[currentFrame];
            case PUNCH_HEAD:
                return spritesPunchHead[currentFrame];
            case PUNCH_CHEST:
                return spritesPunchChest[currentFrame];
            default:
                return spritesIdle[currentFrame];
        }
    }

    private int getFrameCountForState() {
        switch (state) {
            case MOVE_FORWARD:
                return spritesMoveForward.length;
            case MOVE_BACKWARD:
                return spritesMoveBackward.length;
            case PUNCH_HEAD:
                return spritesPunchHead.length;
            case PUNCH_CHEST:
                return spritesPunchChest.length;
            case IDLE:
            default:
                return spritesIdle.length;
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    private void setAnnimations(Sprites sprites) {
        spritesIdle = new BufferedImage[1];
        spritesIdle[0] = sprites.getSprite(0, "player1");

        spritesMoveForward = new BufferedImage[6];
        spritesMoveForward[0] = sprites.getSprite(6, "player1");
        spritesMoveForward[1] = sprites.getSprite(7, "player1");
        spritesMoveForward[2] = sprites.getSprite(5, "player1");
        spritesMoveForward[3] = sprites.getSprite(6, "player1");
        spritesMoveForward[4] = sprites.getSprite(4, "player1");
        spritesMoveForward[5] = sprites.getSprite(5, "player1");

        spritesMoveBackward = new BufferedImage[6];
        spritesMoveBackward[0] = sprites.getSprite(6, "player1");
        spritesMoveBackward[1] = sprites.getSprite(7, "player1");
        spritesMoveBackward[2] = sprites.getSprite(5, "player1");
        spritesMoveBackward[3] = sprites.getSprite(6, "player1");
        spritesMoveBackward[4] = sprites.getSprite(4, "player1");
        spritesMoveBackward[5] = sprites.getSprite(5, "player1");

        spritesPunchHead = new BufferedImage[2];
        spritesPunchHead[0] = sprites.getSprite(10, "player1");
        spritesPunchHead[1] = sprites.getSprite(11, "player1");

        spritesPunchChest = new BufferedImage[2];
        spritesPunchChest[0] = sprites.getSprite(14, "player1");
        spritesPunchChest[1] = sprites.getSprite(15, "player1");

        spritesHeadHit = new BufferedImage[1];
        spritesHeadHit[0] = sprites.getSprite(16, "player1");

        spritesChestHit = new BufferedImage[1];
        spritesChestHit[0] = sprites.getSprite(17, "player1");

        spritesKo = new BufferedImage[5];
        spritesKo[0] = sprites.getSprite(18, "player1");
        spritesKo[1] = sprites.getSprite(20, "player1");
        spritesKo[2] = sprites.getSprite(21, "player1");
        spritesKo[3] = sprites.getSprite(22, "player1");
        spritesKo[4] = sprites.getSprite(19, "player1");

        spritesDefend = new BufferedImage[2];
        spritesDefend[0] = sprites.getSprite(8, "player1");
        spritesDefend[1] = sprites.getSprite(9, "player1");

        spritesDodge = new BufferedImage[1];
        spritesDodge[0] = sprites.getSprite(23, "player1");

        spritesPolicePatrol = new BufferedImage[1];
        spritesPolicePatrol[0] = sprites.getSprite(2, "player1");

        spritesPoliceCatch = new BufferedImage[1];
        spritesPoliceCatch[0] = sprites.getSprite(3, "player1");

        spritesDance = new BufferedImage[1];
        spritesDance[0] = sprites.getSprite(1, "player1");
    }
}