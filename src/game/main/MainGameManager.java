package game.main;

import com.sun.tools.javac.Main;

public class MainGameManager {
    MainViewContorller mainViewContorller;

    int manX = 0;
    int manY = 0;
    boolean isBombPlanted = false;
    int currentPhase = 1;


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
        if (!isBombPlanted) {
            setBoard(manX, manY, BlockTypes.BombAndMan);
            mainViewContorller.repaint();
            new Thread(new Bomb(manX, manY)).start();
            isBombPlanted = true;
        }
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
                Thread.sleep(2000);
                explodeBomb(i,j);
                isBombPlanted = false;
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

        private void explodeCell (int i, int j) {
            if (i<0 || i>19 || j<0 || j>11) {
                return;
            }
            if (!isTypeEqual(i,j,BlockTypes.StoneBlock)) {

                    try {
                        for (int x=1; x<=6; x++) {
                            currentPhase = x;
                            Thread.sleep(1000 / 6);
                            setBoard(i, j, explosionAnimator(x));
                            mainViewContorller.repaint();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                setBoard(i,j,BlockTypes.Empty);
            }
        }

        private BlockTypes explosionAnimator(int phase) {
            BlockTypes currentPhase = null;
            switch (phase) {
                case 1:
                    currentPhase =  BlockTypes.Explosion1;
                    break;
                case 2:
                    currentPhase =  BlockTypes.Explosion2;
                    break;
                case 3:
                    currentPhase =  BlockTypes.Explosion3;
                    break;
                case 4:
                    currentPhase =  BlockTypes.Explosion4;
                    break;
                case 5:
                    currentPhase =  BlockTypes.Explosion5;
                    break;
                case 6:
                    currentPhase =  BlockTypes.Explosion6;
                    break;
            }
            return  currentPhase;
        }
    }

