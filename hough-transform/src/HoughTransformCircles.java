import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HoughTransformCircles extends ImageProcessor {

    public HoughTransformCircles(String imagePath) throws IOException {
        super(imagePath);
    }

    private List<int[]> houghTransformCircles(BufferedImage borders, int radioMin, int radioMax, int threshold) {
        int width = borders.getWidth();
        int height = borders.getHeight();
        int[][][] accumulator = new int[width][height][radioMax - radioMin + 1];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if ((borders.getRGB(x, y) & 0xFF) == 0xFF) {
                    for (int r = radioMin; r <= radioMax; r++) {
                        for (int theta = 0; theta < 360; theta++) {
                            double radians = Math.toRadians(theta);
                            int a = (int) (x - r * Math.cos(radians));
                            int b = (int) (y - r * Math.sin(radians));
                            if (a >= 0 && a < width && b >= 0 && b < height) {
                                accumulator[a][b][r - radioMin]++;
                            }
                        }
                    }
                }
            }
        }

        List<int[]> circles = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int r = radioMin; r <= radioMax; r++) {
                    if (accumulator[x][y][r - radioMin] > threshold) {
                        circles.add(new int[]{x, y, r});
                    }
                }
            }
        }
        return circles;
    }

    private List<int[]> suppressionNotMax(List<int[]> circles, int radioSuppression) {
        List<int[]> suppressedCircles = new ArrayList<>();

        for (int i = 0; i < circles.size(); i++) {
            int[] circle1 = circles.get(i);
            boolean isMax = true;

            for (int j = 0; j < circles.size(); j++) {
                if (i == j) continue;
                int[] circle2 = circles.get(j);

                double distance = Math.sqrt(Math.pow(circle1[0] - circle2[0], 2) + Math.pow(circle1[1] - circle2[1], 2));
                if (distance < radioSuppression && circle1[2] < circle2[2]) {
                    isMax = false;
                    break;
                }
            }

            if (isMax) {
                suppressedCircles.add(circle1);
            }
        }

        return suppressedCircles;
    }

    private void drawCircles(List<int[]> circles, int thickness) {
        int color = 0xFF0000; // Color rojo para círculos

        for (int[] circle : circles) {
            int x0 = circle[0];
            int y0 = circle[1];
            int radio = circle[2];

            for (int theta = 0; theta < 360; theta++) {
                double radians = Math.toRadians(theta);
                int x = (int) (x0 + radio * Math.cos(radians));
                int y = (int) (y0 + radio * Math.sin(radians));
                drawThickPoint(image, x, y, thickness, color);
            }
        }
    }

    private void drawThickPoint(BufferedImage img, int x, int y, int thickness, int color) {
        for (int i = -thickness; i <= thickness; i++) {
            for (int j = -thickness; j <= thickness;) {
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < img.getWidth() && newY >= 0 && newY < img.getHeight()) {
                    img.setRGB(newX, newY, color);
                }
            }
        }
    }

    public void processCirclesImage(int radioMin, int radioMax, int threshold, int radioSuppression, int thickness) throws IOException {
        BufferedImage grayScales = convertGrayScales(image);
        BufferedImage borders = detectBorders(grayScales);
        List<int[]> circles = houghTransformCircles(borders, radioMin, radioMax, threshold);
        circles = suppressionNotMax(circles, radioSuppression);
        drawCircles(circles, thickness);
        saveImage("output_circles.png");
    }
}
