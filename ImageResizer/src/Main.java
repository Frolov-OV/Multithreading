import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    private static final int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "src/Multithreading/ImageResizer/source";
        String dstFolder = "src/Multithreading/ImageResizer/dst";
        int cpuCount = Runtime.getRuntime().availableProcessors();
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        for (int i = 0; i <= cpuCount; i++) {
            System.out.println("Thread " + i + " start");
            new Multithreading(files, newWidth, dstFolder).start();

        }

    }
}
