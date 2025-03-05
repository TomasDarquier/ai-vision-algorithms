import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws IOException {
        int imageSize = 160;
        File inputFile = new File("test_image.png");
        BufferedImage inputImage = ImageIO.read(inputFile);
        int[] inputPattern = ImageProcessor.imageToPattern(inputImage);

        HopfieldNetwork network = new HopfieldNetwork(imageSize * imageSize);

        // Training with input image
        network.train(inputPattern);

        // Recognize the pattern
        int[] recognizedPattern = network.recognize(inputPattern);

        // Convert the pattern into an image
        BufferedImage recognizedImage = ImageProcessor.patternToImage(recognizedPattern, imageSize);

        // Save the output image
        File outputFile = new File("recognized_image.png");
        ImageIO.write(recognizedImage, "png", outputFile);

        System.out.println("Imagen reconocida guardada en: " + outputFile.getAbsolutePath());
    }
}
