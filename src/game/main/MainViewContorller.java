package game.main;

import managers.ImageManager;

import javax.swing.*;

public class MainViewContorller extends JFrame {
    private static final int blockWidth = 50;
    private static final int blockHeight = 50;
    ImageManager imageManager = new ImageManager();

    public MainViewContorller(int row, int column) {
        setSize(row * blockWidth, row * blockHeight);
        setLayout(null);
        add(imageManager.)
    }


}