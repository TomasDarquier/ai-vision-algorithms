import java.awt.image.BufferedImage;

class ImageProcessor {
    public static BufferedImage patternToImage(int[] pattern, int imageSize) {
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < imageSize; i++) {
            for (int j = 0; j < imageSize; j++) {
                int pixelValue = pattern[i * imageSize + j] == 1 ? 0xFFFFFF : 0;
                image.setRGB(j, i, pixelValue); // Se intercambian i y j
            }
        }
        return image;
    }

    public static int[] imageToPattern(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pattern = new int[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(i, j);
                int gray = (pixel & 0xFF);
                pattern[i * width + j] = gray > 128 ? 1 : -1;
            }
        }
        return pattern;
    }
}