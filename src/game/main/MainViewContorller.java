package game.main;

import managers.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainViewContorller extends JPanel implements KeyListener {
    private static final int blockWidth = 50;
    private static final int blockHeight = 50;
    BlockTypes [][] board;
    ImageManager imageManager = new ImageManager();
    MainGameManager mainGameManager = new MainGameManager();
    MapProvider mapProvider = new MapProvider();

    public MainViewContorller(int row, int column) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(column * blockWidth, row * blockHeight);
        jFrame.add(this);
        jFrame.setVisible(true);
        jFrame.setTitle("Bomber Man");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addKeyListener(this);
        board = mapProvider.mapBuilder(row,column);
        mainGameManager.mainViewContorller = this;
        mainGameManager.onBoardCreated();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                BlockTypes value = board[i][j];
                Image toDraw = null;
                if(value == BlockTypes.Man) {
                    toDraw = imageManager.getMan();
                } else if(value == BlockTypes.StoneBlock) {
                    toDraw = imageManager.getBrickImage();
                }else if(value == BlockTypes.BrikBlock) {
                    toDraw = imageManager.getBrickImage();
                }
                g.drawImage(toDraw,i*blockWidth,j*blockHeight,blockWidth,blockHeight,null);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            mainGameManager.upPressed();
        }
        if (e.getKeyCode() == 39) {
            mainGameManager.rightPressed();
        }
        if (e.getKeyCode() == 37) {
            mainGameManager.leftPressed();
        }
        if (e.getKeyCode() == 40) {
            mainGameManager.downPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}