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


    public ImageManager() {
        try {

            String userDir = System.getProperty("user.dir");
            blocks = ImageIO.read(new File(userDir + "\\Resources\\BlocksImage.jpg"));
            man = ImageIO.read(new File(userDir + "\\Resources\\man.png"));
            bomb = ImageIO.read(new File(userDir + "\\Resources\\Bomb.png"));
            explosion = ImageIO.read(new File(userDir + "\\Resources\\Explosion.png"));

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

    public Image getExplosionImage(int phase) {
        Image currentExplosionImage = null;
        switch (phase) {
            case 1:
                currentExplosionImage = explosion.getSubimage(0, 0, 70, 70);
            case 2:
                currentExplosionImage = explosion.getSubimage(70, 0, 70, 70);
            case 3:
                currentExplosionImage = explosion.getSubimage(140, 0, 70, 70);
            case 4:
                currentExplosionImage = explosion.getSubimage(210, 0, 70, 70);
            case 5:
                currentExplosionImage = explosion.getSubimage(0, 70, 70, 70);
            case 6:
                currentExplosionImage = explosion.getSubimage(70, 70, 70, 70);
        }
        return currentExplosionImage;
    }
        //return explosion.getSubimage(0,0,70,70);
    /*public Image getExplosionImage2() { return explosion.getSubimage(70,0,70,70);}
    public Image getExplosionImage3() { return explosion.getSubimage(140,0,70,70);}
    public Image getExplosionImage4() { return explosion.getSubimage(210,0,70,70);}
    public Image getExplosionImage5() { return explosion.getSubimage(0,70,70,70);}
    public Image getExplosionImage6() { return explosion.getSubimage(70,70,70,70);}*/
}
