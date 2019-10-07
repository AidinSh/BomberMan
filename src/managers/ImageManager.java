package managers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {

    private BufferedImage bufferedImage;

    public ImageManager() {
        try {
            bufferedImage = ImageIO.read(new File("C:\\Users\\aidin.shahmoradi\\IdeaProjects\\BomberMan\\Resources\\BlocksImage.jpg"));
        } catch (IOException e) {
            System.exit(1);
        }
    }

    public Image getImage() {
        return bufferedImage;
    }

    public Image getStoneImage() {
        return bufferedImage.getSubimage(168,12,26,26);
    }
}
