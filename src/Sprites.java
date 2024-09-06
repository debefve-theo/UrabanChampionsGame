import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Sprites {

    private ArrayList<Sprite> sprites;

    public Sprites() {
        sprites = setDefaultValue();
        getSpritesFromFiles();
    }

    public BufferedImage getSprite(int i, String collection) {

        int count = 0;

        for (Sprite sprite : sprites) {
            if (sprite.getCollection().equals(collection)) {
                if (count == i) {
                    return sprite.getImage();
                }
                count++;
            }
        }

        throw new IllegalArgumentException("Sprite not found");
    }

    public static BufferedImage transparentSprite(BufferedImage image, Color colorToReplace) {
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

    private void getSpritesFromFiles() {
        try {
            BufferedImage spriteSheetCharacters = ImageIO.read(new File("src/sprites/characters.png"));
            BufferedImage spriteSheetStreet = ImageIO.read(new File("src/sprites/street.gif"));
            BufferedImage spriteSheetHole = ImageIO.read(new File("src/sprites/hole.png"));

            for (Sprite sprite : sprites) {

                if(Objects.equals(sprite.getSrc(), "characters.png")) {
                    sprite.setImage(spriteSheetCharacters.getSubimage(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()));
                } else if (Objects.equals(sprite.getSrc(), "street.gif")) {
                    sprite.setImage(spriteSheetStreet.getSubimage(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()));
                } else if (Objects.equals(sprite.getSrc(), "hole.png")) {
                    sprite.setImage(spriteSheetHole.getSubimage(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()));
                }

                if(sprite.getBackground() != null) {
                    sprite.setImage(transparentSprite(sprite.getImage(), sprite.getBackground()));
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private ArrayList<Sprite> setDefaultValue() {
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        // Sprites street
        for (int i = 0; i < 5; i++) {
            sprites.add(new Sprite("street.gif", i*256, 0, 256, 160, null, "street"));   // Five street area
        }

        // Sprites menu
        sprites.add(new Sprite("characters.png", 0, 0, 256, 144, null, "menu"));     // Urban champion
        sprites.add(new Sprite("characters.png", 252, 360, 256, 96, null, "menu"));  // Menu
        sprites.add(new Sprite("characters.png", 280, 365, 15, 15, null, "menu"));   // White arrow
        sprites.add(new Sprite("characters.png", 265, 365, 15, 15, null, "menu"));   // Black arrow

        // Sprites game ATH
        sprites.add(new Sprite("characters.png", 256, 0, 256, 72, null, "ath"));     // Solo ATH
        sprites.add(new Sprite("characters.png", 256, 73, 256, 72, null, "ath"));    // 1vs1 ATH
        // Green number XL
        // Purple number XL
        // White letter
        // Yellow number XS
        // Blue number XS
        // Grey number XS
        // Icons (9)

        // Sprites event
        sprites.add(new Sprite("hole.png", 0, 0, 221, 72, null, "event"));
        sprites.add(new Sprite("characters.png", 252, 318, 68, 26, new Color(100,176,255), "event"));   // Police car 1
        sprites.add(new Sprite("characters.png", 319, 318, 68, 26, new Color(100,176,255), "event"));   // Police car 2
        // Police agent
        // Flower man 1
        // Flower man 2
        // Flower 1
        // Flower 2
        // Happy woman 1
        // Happy woman 2
        // Confetti 1
        // Confetti 2
        // Confetti 3
        // Confetti 4
        // Confetti 5
        // Confetti 6

        // Sprites player 1
        sprites.add(new Sprite("characters.png", 10, 159, 24, 38, new Color(100,176,255), "player1"));  // Player 1 state 1
        sprites.add(new Sprite("characters.png", 40, 160, 21, 37, new Color(100,176,255), "player1"));  // Player 1 state 2
        sprites.add(new Sprite("characters.png", 63, 159, 24, 38, new Color(100,176,255), "player1"));  // Player 1 state 3
        sprites.add(new Sprite("characters.png", 91, 161, 31, 36, new Color(100,176,255), "player1"));  // Player 1 state 4

        sprites.add(new Sprite("characters.png", 7, 202, 22, 40, new Color(100,176,255), "player1"));   // Player 1 state 5
        sprites.add(new Sprite("characters.png", 31, 203, 21, 39, new Color(100,176,255), "player1"));  // Player 1 state 6
        sprites.add(new Sprite("characters.png", 55, 203, 18, 39, new Color(100,176,255), "player1"));  // Player 1 state 7
        sprites.add(new Sprite("characters.png", 76, 202, 14, 40, new Color(100,176,255), "player1"));  // Player 1 state 8

        sprites.add(new Sprite("characters.png", 10, 249, 16, 40, new Color(100,176,255), "player1"));  // Player 1 state 9
        sprites.add(new Sprite("characters.png", 30, 249, 16, 40, new Color(100,176,255), "player1"));  // Player 1 state 10
        sprites.add(new Sprite("characters.png", 48, 249, 21, 40, new Color(100,176,255), "player1"));  // Player 1 state 11
        sprites.add(new Sprite("characters.png", 72, 253, 29, 36, new Color(100,176,255), "player1"));  // Player 1 state 12

        sprites.add(new Sprite("characters.png", 10, 292, 14, 40, new Color(100,176,255), "player1"));  // Player 1 state 13
        sprites.add(new Sprite("characters.png", 27, 292, 14, 40, new Color(100,176,255), "player1"));  // Player 1 state 14
        sprites.add(new Sprite("characters.png", 43, 292, 21, 40, new Color(100,176,255), "player1"));  // Player 1 state 15
        sprites.add(new Sprite("characters.png", 66, 296, 30, 36, new Color(100,176,255), "player1"));  // Player 1 state 16

        sprites.add(new Sprite("characters.png", 10, 334, 16, 39, new Color(100,176,255), "player1"));  // Player 1 state 17
        sprites.add(new Sprite("characters.png", 28, 336, 16, 37, new Color(100,176,255), "player1"));  // Player 1 state 18
        sprites.add(new Sprite("characters.png", 48, 336, 24, 37, new Color(100,176,255), "player1"));  // Player 1 state 19
        sprites.add(new Sprite("characters.png", 74, 342, 22, 31, new Color(100,176,255), "player1"));  // Player 1 state 20

        sprites.add(new Sprite("characters.png", 10, 379, 30, 24, new Color(100,176,255), "player1"));  // Player 1 state 21
        sprites.add(new Sprite("characters.png", 42, 378, 30, 29, new Color(100,176,255), "player1"));  // Player 1 state 22
        sprites.add(new Sprite("characters.png", 74, 386, 22, 21, new Color(100,176,255), "player1"));  // Player 1 state 23

        sprites.add(new Sprite("characters.png", 7, 416, 23, 38, new Color(100,176,255), "player1"));   // Player 1 state 24
        sprites.add(new Sprite("characters.png", 32, 415, 21, 39, new Color(100,176,255), "player1"));  // Player 1 state 25
        sprites.add(new Sprite("characters.png", 55, 419, 31, 35, new Color(100,176,255), "player1"));  // Player 1 state 26
        sprites.add(new Sprite("characters.png", 91, 416, 31, 38, new Color(100,176,255), "player1"));  // Player 1 state 27

        // Sprites player 2

        return sprites;
    }
}
