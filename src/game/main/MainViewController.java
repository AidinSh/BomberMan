package game.main;

import managers.ImageManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainViewController extends JPanel implements KeyListener {
    private static final int blockWidth = 50;
    private static final int blockHeight = 50;
    BlockTypes [][] board;
    ImageManager imageManager = new ImageManager();
    MainGameManager mainGameManager = new MainGameManager();
    MapProvider mapProvider = new MapProvider();

    public MainViewController(int row, int column) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(column * blockWidth + 20, row * blockHeight + 40);
        jFrame.add(this);
        jFrame.setVisible(true);
        jFrame.setTitle("Bomber Man");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addKeyListener(this);
        board = mapProvider.mapBuilder(row, column);
        mainGameManager.mainViewController = this;
        mainGameManager.player1.onBoardCreated();
        mainGameManager.player2.onBoardCreated();
    }

    public void showGameOverMessage() {
        JOptionPane.showMessageDialog(null,"You Loss Retard !");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                BlockTypes value = board[i][j];
                Image toDraw = null;

                switch (value) {
                    case Man:
                        toDraw = imageManager.getMan();
                        break;
                    case StoneBlock:
                        toDraw = imageManager.getStoneImage();
                        break;
                    case BrickBlock:
                        toDraw = imageManager.getBrickImage();
                        break;
                    case Bomb:
                        toDraw = imageManager.getBombImage();
                        break;
                    case BombAndMan:
                        drawImage(imageManager.getBombImage(), g, i, j);
                        drawImage(imageManager.getMan(), g, i, j);
                        break;
                    case DoorAndBrick:
                        toDraw = imageManager.getBrickImage();
                        break;
                    case Door:
                        toDraw = imageManager.getDoorImage();
                        break;
                    case Explosion1:
                        toDraw = imageManager.getExplosionImage(0);
                        break;
                    case Explosion2:
                        toDraw = imageManager.getExplosionImage(1);
                        break;
                    case Explosion3:
                        toDraw = imageManager.getExplosionImage(2);
                        break;
                    case Explosion4:
                        toDraw = imageManager.getExplosionImage(3);
                        break;
                    case Explosion5:
                        toDraw = imageManager.getExplosionImage(4);
                        break;
                    case Explosion6:
                        toDraw = imageManager.getExplosionImage(5);
                        break;

                }
                drawImage(toDraw, g, i, j);
            }
        }

    }

    private void drawImage(Image toDraw, Graphics g, int i, int j) {
        g.drawImage(toDraw,i*blockWidth,j*blockHeight,blockWidth,blockHeight,null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case 65:
                mainGameManager.player1.leftPressed();
                break;
            case 87:
                mainGameManager.player1.upPressed();
                break;
            case 68:
                mainGameManager.player1.rightPressed();
                break;
            case 83:
                mainGameManager.player1.downPressed();
                break;
            case 32:
                mainGameManager.player1.bombPressed();
                break;
            case 10:
                mainGameManager.player2.bombPressed();
                break;
            case 37:
                mainGameManager.player2.leftPressed();
                break;
            case 38:
                mainGameManager.player2.upPressed();
                break;
            case 39:
                mainGameManager.player2.rightPressed();
                break;
            case 40:
                mainGameManager.player2.downPressed();
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}