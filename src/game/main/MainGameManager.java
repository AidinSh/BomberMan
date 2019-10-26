package game.main;


public class MainGameManager {
    game.main.mainViewContorller mainViewContorller;

    Player player1 = new Player();
    int manX = 0;
    int manY = 0;
    boolean isBombPlanted = false;
    int currentPhase = 1;
    boolean isManDead = false;


    public void onBoardCreated() {
        setBoard(manX, manY, BlockTypes.Man);
    }

    public void upPressed() {
        player1.moveManTo(manX, manY-1);
    }

    public void rightPressed() {
        player1.moveManTo(manX+1, manY);
    }

    public void downPressed() {
        player1.moveManTo(manX, manY + 1);
    }

    public void leftPressed() {
        player1.moveManTo(manX-1, manY);
    }

    public void spacePressed() {
        if (!isBombPlanted) {
            setBoard(manX, manY, BlockTypes.BombAndMan);
            mainViewContorller.repaint();
            new Thread(new Bomb(manX, manY)).start();
            isBombPlanted = true;
        }
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
        int i, j;

        Bomb(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                explodeBomb(i, j);
                isBombPlanted = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void explodeBomb(int i, int j) {
            try {
                int iArray[]=  {i, i+1, i-1 ,i, i};
                int jArray[]=  {j, j, j, j+1 ,j-1};

                for (int x = 1; x <= 6; x++) {
                    for (int y = 0; y < 5; y++) {
                        explodeCell(iArray[y], jArray[y], x);

                    }
                    Thread.sleep(1000 / 6);

                }
                for (int y = 0; y < 5; y++) {
                    setCellEmpty(iArray[y], jArray[y]);
                }

                if(isManDead) {
                    mainViewContorller.showGameOverMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void explodeCell(int i, int j, int phase) {
            if (i < 0 || i > 19 || j < 0 || j > 11) {
                return;
            }
            if (!isTypeEqual(i, j, BlockTypes.StoneBlock)) {
                if (isTypeEqual(i, j, BlockTypes.Man) || isTypeEqual(i, j, BlockTypes.BombAndMan)) {
                     isManDead = true;
                }
                setBoard(i, j, explosionAnimator(phase));
                mainViewContorller.repaint();
                }
            }

        private void setCellEmpty(int i, int j) {
            if (i < 0 || i > 19 || j < 0 || j > 11) {
                return;
            }
            if (!isTypeEqual(i, j, BlockTypes.StoneBlock)) {
                setBoard(i, j, BlockTypes.Empty);
                mainViewContorller.repaint();
            }
        }

    }

    class Player {

        private void moveManTo(int i, int j) {
            if (!isManDead) {
                if (i < 0 || i > 19 || j < 0 || j > 11) {
                    return;
                }
                if (isTypeEqual(i, j, BlockTypes.Empty)) {
                    if (isTypeEqual(manX, manY, BlockTypes.BombAndMan)) {
                        setBoard(manX, manY, BlockTypes.Bomb);
                    } else {
                        setBoard(manX, manY, BlockTypes.Empty);
                    }
                    setBoard(i, j, BlockTypes.Man);
                    manX = i;
                    manY = j;
                }
                mainViewContorller.repaint();
            }
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

