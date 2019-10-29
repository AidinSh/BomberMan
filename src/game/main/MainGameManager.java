package game.main;


public class MainGameManager {
    MainViewController mainViewController;

    Player player1 = new Player(0,0);
    Player player2 = new Player(18,0);
    boolean isBombPlanted = false;
    int currentPhase = 1;
    boolean isManDead = false;
    int doorX = 0;
    int doorY = 0;


    synchronized private void setBoard(int i, int j, BlockTypes type) {
        mainViewController.board[i][j] = type;
    }
    private BlockTypes getBoard(int i, int j) {
        return  mainViewController.board[i][j];
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
                int iArray[]=  {i, i+1, i-1, i, i};
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
                    mainViewController.showGameOverMessage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void explodeCell(int i, int j, int phase) {
            if (i < 0 || i > 18 || j < 0 || j > 11) {
                return;
            }
            if (!isTypeEqual(i, j, BlockTypes.StoneBlock)) {
                if (isTypeEqual(i, j, BlockTypes.Man) || isTypeEqual(i, j, BlockTypes.BombAndMan)) {
                    isManDead = true;
                }
                if (isTypeEqual(i, j, BlockTypes.DoorAndBrick)) {
                    doorX = i;
                    doorY = j;
                }
                setBoard(i, j, explosionAnimator(phase));
                mainViewController.repaint();
                }
            }

        private void setCellEmpty(int i, int j) {
            if (i < 0 || i > 18 || j < 0 || j > 11) {
                return;
            }
            if (!isTypeEqual(i, j, BlockTypes.StoneBlock)) {
                setBoard(i, j, BlockTypes.Empty);
                if (i == doorX && j == doorY) {
                    setBoard(i, j, BlockTypes.Door);
                }
                mainViewController.repaint();
            }
        }

    }

    class Player {
        int manX,manY;

        Player(int playerX, int playerY){
            manX = playerX;
            manY = playerY;
        }
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

        public void bombPressed() {
            if (!isBombPlanted) {
                setBoard(manX, manY, BlockTypes.BombAndMan);
                mainViewController.repaint();
                new Thread(new Bomb(manX, manY)).start();
                isBombPlanted = true;
            }
        }

        private void moveManTo(int i, int j) {
            if (!isManDead) {
                if (i < 0 || i > 18 || j < 0 || j > 11) {
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
                mainViewController.repaint();
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

