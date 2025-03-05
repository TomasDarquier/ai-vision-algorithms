import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public abstract class ImageProcessor {
    protected BufferedImage image;

    public ImageProcessor(String imagePath) throws IOException {
        image = ImageIO.read(new File(imagePath));
    }

    protected BufferedImage convertGrayScales(BufferedImage original) {
        BufferedImage grayScales = new BufferedImage(
            original.getWidth(), original.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                int rgb = original.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int gray = (r + g + b) / 3;
                int grayRGB = gray << 16 | gray << 8 | gray;
                grayScales.setRGB(x, y, grayRGB);
            }
        }
        
        return grayScales;
    }

    protected BufferedImage detectBorders(BufferedImage grayScales) {
        int width = grayScales.getWidth();
        int height = grayScales.getHeight();
        BufferedImage borders = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        
        int[][] sobelX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] sobelY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int gx = 0;
                int gy = 0;
                
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int pixel = (grayScales.getRGB(x + i, y + j) & 0xFF);
                        gx += sobelX[i + 1][j + 1] * pixel;
                        gy += sobelY[i + 1][j + 1] * pixel;
                    }
                }
                
                int magnitude = (int) Math.sqrt(gx * gx + gy * gy);
                int borderColor = (magnitude > 128) ? 0xFFFFFF : 0x000000;
                borders.setRGB(x, y, borderColor);
            }
        }
        
        return borders;
    }

    protected void saveImage(String outputPath) throws IOException {
        ImageIO.write(image, "png", new File(outputPath));
    }
}
