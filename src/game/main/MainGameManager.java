package game.main;

public class MainGameManager {
    MainViewContorller mainViewContorller;

    int manX = 0;
    int manY = 0;


    public void onBoardCreated() {
        setBoard(manX, manY, BlockTypes.Man);
    }

    public void upPressed() {
        moveManTo(manX, manY-1);
    }

    public void rightPressed() {
        moveManTo(manX+1, manY);
    }

    public void downPressed() {
        moveManTo(manX, manY + 1);
    }

    public void leftPressed() {
        moveManTo(manX-1, manY);
    }

    public void spacePressed() {
        setBoard(manX, manY, BlockTypes.BombAndMan);
        mainViewContorller.repaint();
        new Thread(new Bomb(manX, manY)).start();
    }

    private void moveManTo(int i, int j) {
        if (i<0 || i>19 || j<0 || j>11) {
            return;
        }
        if (isTypeEqual(i, j, BlockTypes.Empty)) {
            if (isTypeEqual(manX, manY, BlockTypes.BombAndMan)) {
                setBoard(manX, manY, BlockTypes.Bomb);
            }else {
                setBoard(manX, manY, BlockTypes.Empty);
            }
            setBoard(i, j, BlockTypes.Man);
            manX = i;
            manY = j;
        }
        mainViewContorller.repaint();
    }

    synchronized private void setBoard(int i, int j, BlockTypes type) {
        mainViewContorller.board[i][j] = type;
    }
    private BlockTypes getBoard(int i, int j) {
        return  mainViewContorller.board[i][j];
    }
    private Boolean isTypeEqual(int i, int j, BlockTypes type) {
        return  getBoard(i,j) == type;
    }

    class Bomb implements Runnable {
        int i,j;
        Bomb(int i, int j) {
            this.i = i;
            this.j = j;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                explodeBomb(i,j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private void explodeBomb(int i, int j) {
            explodeCell(i,j);
            explodeCell(i+1,j);
            explodeCell(i-1,j);
            explodeCell(i,j+1);
            explodeCell(i,j-1);
            mainViewContorller.repaint();
        }

        private void explodeCell(int i, int j) {
            if (i<0 || i>19 || j<0 || j>11) {
                return;
            }
            if (!isTypeEqual(i,j,BlockTypes.StoneBlock)) {
                setBoard(i,j,BlockTypes.Empty);
            }
        }
    }
}
