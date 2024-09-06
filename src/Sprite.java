import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    private String src;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color background;
    private String collection;
    private BufferedImage image;

    public Sprite(String src, int x, int y, int width, int height, Color background, String collection) {
        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.background = background;
        this.collection = collection;
        this.image = null;
    }

    public String getSrc() {
        return src;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getBackground() {
        return background;
    }

    public String getCollection() {
        return collection;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
