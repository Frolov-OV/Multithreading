import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Multithreading extends Thread{
    private final File[] files;
    private final int newWidth;
    private final String dstFolder;

    public Multithreading(File[] files, int newWidth, String dstFolder) {
    this.files = files;
    this.newWidth = newWidth;
    this.dstFolder = dstFolder;
    }
    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round(
                        image.getHeight() / (image.getWidth() / (double) newWidth));

/*                BufferedImage newImage = new BufferedImage(
                        newWidth, newHeight, BufferedImage.TYPE_INT_RGB);*/
                BufferedImage scaledImg = Scalr.resize(image, Scalr.Mode.AUTOMATIC,
                        newWidth, newHeight);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(scaledImg, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
