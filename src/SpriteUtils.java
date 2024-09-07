import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class SpriteUtils {

    public static BufferedImage resizeAndCenterSprite(BufferedImage original, int targetWidth, int targetHeight) {

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();

        int x = (targetWidth - original.getWidth()) / 2;
        int y = targetHeight - original.getHeight();

        g2d.drawImage(original, x, y, null);
        g2d.dispose();

        return resizedImage;
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

    public static BufferedImage flipImageHorizontally(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage flippedImage = new BufferedImage(width, height, originalImage.getType());

        Graphics2D g2d = flippedImage.createGraphics();

        AffineTransform transform = new AffineTransform();
        transform.scale(-1, 1);
        transform.translate(-width, 0);

        g2d.drawImage(originalImage, transform, null);
        g2d.dispose();

        return flippedImage;
    }

}
