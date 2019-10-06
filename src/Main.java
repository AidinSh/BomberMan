import game.main.MainViewContorller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainViewContorller mainViewContorller = new MainViewContorller(20,20);
        mainViewContorller.setVisible(true);
        mainViewContorller.setTitle("Bomber Man");
        mainViewContorller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
