package game.main;

public class MainGameManager {
    MainViewContorller mainViewContorller;

    int manX = 0;
    int manY = 0;

    public void onBoardCreated() {
        mainViewContorller.board[manX][manY] = BlockTypes.Man;
    }

    public void upPressed() {
        if (manY != 0 && mainViewContorller.board[manX][manY-1] == BlockTypes.Empty) {
            mainViewContorller.board[manX][manY] = BlockTypes.Empty;
            manY--;
            mainViewContorller.board[manX][manY] =BlockTypes.Man;

        }
        mainViewContorller.repaint();
    }

    public void rightPressed() {
        if (manX != 19 && mainViewContorller.board[manX+1][manY] == BlockTypes.Empty) {
            mainViewContorller.board[manX][manY] = BlockTypes.Empty;
            manX++;
            mainViewContorller.board[manX][manY] = BlockTypes.Man;
        }
        mainViewContorller.repaint();

    }

    public void downPressed() {
        if (manY != 11 && mainViewContorller.board[manX][manY+1] == BlockTypes.Empty) {
            mainViewContorller.board[manX][manY] = BlockTypes.Empty;
            manY++;
            mainViewContorller.board[manX][manY] = BlockTypes.Man;
        }
        mainViewContorller.repaint();
    }

    public void leftPressed() {
        if (manX != 0 && mainViewContorller.board[manX-1][manY] == BlockTypes.Empty) {
            mainViewContorller.board[manX][manY] = BlockTypes.Empty;
            manX--;
            mainViewContorller.board[manX][manY] = BlockTypes.Man;
        }
        mainViewContorller.repaint();
    }

    public void spacePressed() {
        mainViewContorller.board[manX][manY] = BlockTypes.Bomb;
        mainViewContorller.repaint();
    }

}
