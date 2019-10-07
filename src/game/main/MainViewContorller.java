package game.main;

import managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class MainViewContorller extends JPanel {
    private static final int blockWidth = 50;
    private static final int blockHeight = 50;
    int [][] board;
    ImageManager imageManager = new ImageManager();


    public MainViewContorller(int row, int column) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(row * blockWidth, row * blockHeight);
        jFrame.add(this);
        jFrame.setVisible(true);
        jFrame.setTitle("Bomber Man");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new int[row][column];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                int value = board[i][j];
                if(value == 1) {
                    g.drawImage(imageManager.getStoneImage(),i*blockWidth,j*blockHeight,blockWidth,blockHeight,null);
                }
            }
        }

    }
}