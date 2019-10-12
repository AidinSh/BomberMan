package managers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {

    private BufferedImage blocks;
    private BufferedImage man;
    private BufferedImage bomb;


    public ImageManager() {
        try {

            String userHome = System.getProperty("user.home");
            blocks = ImageIO.read(new File(userHome + "\\IdeaProjects\\BomberMan\\Resources\\BlocksImage.jpg"));
            man = ImageIO.read(new File(userHome + "\\IdeaProjects\\BomberMan\\Resources\\man.png"));
            bomb = ImageIO.read(new File(userHome + "\\IdeaProjects\\BomberMan\\Resources\\Bomb.png"));

        } catch (IOException e) {
            System.exit(1);
        }
    }

    public Image getMan() {
        return man;
    }

    /*public Image getFlippedMan() {
        return man.getScaledInstance(-1,1,0);
    }*/

    public Image getStoneImage() {
        return blocks.getSubimage(168,12,26,26);
    }

    public Image getBrickImage() {
        return blocks.getSubimage(118,12,26,26);
    }

    public Image getBombImage() {
        return bomb;
    }
}
