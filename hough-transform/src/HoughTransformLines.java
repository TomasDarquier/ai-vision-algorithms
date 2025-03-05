import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HoughTransformLines extends ImageProcessor {

    public HoughTransformLines(String imagePath) throws IOException {
        super(imagePath);
    }

    private List<int[]> houghTransform(BufferedImage borders) {
        int width = borders.getWidth();
        int height = borders.getHeight();
        int maxDistance = (int) Math.hypot(width, height);
        int[][] accumulator = new int[2 * maxDistance][180];
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if ((borders.getRGB(x, y) & 0xFF) == 0xFF) {
                    for (int theta = 0; theta < 180; theta++) {
                        double radians = Math.toRadians(theta);
                        int r = (int) (x * Math.cos(radians) + y * Math.sin(radians));
                        if (r + maxDistance >= 0 && r + maxDistance < 2 * maxDistance) {
                            accumulator[r + maxDistance][theta]++;
                        }
                    }
                }
            }
        }
        
        List<int[]> lines = new ArrayList<>();
        int threshold = 120; // umbral para deteccion de lineas
        for (int r = 0; r < 2 * maxDistance; r++) {
            for (int theta = 0; theta < 180; theta++) {
                if (accumulator[r][theta] > threshold) {
                    lines.add(new int[]{r - maxDistance, theta});
                }
            }
        }
        
        return lines;
    }

    private void drawLines(List<int[]> lines) {
        for (int[] linea : lines) {
            int r = linea[0];
            int theta = linea[1];
            double radians = Math.toRadians(theta);
            
            for (int x = 0; x < image.getWidth(); x++) {
                int y = (int) ((r - x * Math.cos(radians)) / Math.sin(radians));
                if (y >= 0 && y < image.getHeight()) {
                    image.setRGB(x, y, 0xFF0000);
                }
            }
        }
    }

    public void processImage() throws IOException {
        BufferedImage grayScales = convertGrayScales(image);
        BufferedImage borders = detectBorders(grayScales);
        List<int[]> lines = houghTransform(borders);
        drawLines(lines);
        saveImage("output_lines.png");
    }
}

