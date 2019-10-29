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
    private BufferedImage explosion;
    private BufferedImage door;


    public ImageManager() {
        try {

            String userDir = System.getProperty("user.dir");
            blocks = ImageIO.read(new File(userDir + "\\Resources\\BlocksImage.jpg"));
            man = ImageIO.read(new File(userDir + "\\Resources\\man.png"));
            bomb = ImageIO.read(new File(userDir + "\\Resources\\Bomb.png"));
            explosion = ImageIO.read(new File(userDir + "\\Resources\\Explosion.png"));
            door = ImageIO.read(new File(userDir + "\\Resources\\Door.png"));

        } catch (IOException e) {
            System.exit(1);
        }
    }

    public Image getMan() {
        return man;
    }

    public Image getDoorImage() {
        return door;
    }

    public Image getStoneImage() {
        return blocks.getSubimage(168,12,26,26);
    }

    public Image getBrickImage() {
        return blocks.getSubimage(118,12,26,26);
    }

    public Image getBombImage() {
        return bomb;
    }

    public Image getExplosionImage(int phase) {
        Image[] currentExplosionImage = new Image[6];

        currentExplosionImage[0] = explosion.getSubimage(0, 0, 70, 70);
        currentExplosionImage[1] = explosion.getSubimage(70, 0, 70, 70);
        currentExplosionImage[2] = explosion.getSubimage(140, 0, 70, 70);
        currentExplosionImage[3] = explosion.getSubimage(210, 0, 70, 70);
        currentExplosionImage[4] = explosion.getSubimage(0, 70, 70, 70);
        currentExplosionImage[5] = explosion.getSubimage(70, 70, 70, 70);

        return currentExplosionImage[phase];
    }
}
