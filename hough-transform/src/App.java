import java.io.IOException;

public class App {
public static void main(String[] args) {
    try {
        HoughTransformCircles houghCircles = new HoughTransformCircles("input_circle.png");
        HoughTransformLines houghLines = new HoughTransformLines("input_line.png");
        houghLines.processImage();
        houghCircles.processCirclesImage(50, 80, 185, 20, 3);  // Example radius range
        System.out.println("Done");
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
