package game.main;

public class MapProvider {

    public BlockTypes[][] mapBuilder(int row , int column) {
        BlockTypes [][] board = new BlockTypes[column][row];
        for(int x=0; x<column; x++) {
            for (int y=0; y<row; y++) {
                board[x][y] = BlockTypes.Empty;
            }
        }
        for(int x=1; x<column; x+=2) {
            for (int y=0; y<row; y+=2) {
                board[x][y] = BlockTypes.StoneBlock;
            }
        }
        return board;
    }
}
