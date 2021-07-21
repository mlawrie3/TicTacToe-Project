package cpsc2150.extendedTicTacToe;
/**
 * GameBoard provides a fast implementation of game board
 *    using a 2D array
 * @invariant MAX game board size is 100x100
 * 0,0 is always top left of board
 * player marker cannot be placed out of bounds
 * player marker cannot be added to full game board
 * Correspondence: totalRow = getNumRows totalCol = getNumColumns
 *   numWin = getNumToWin space = returnSpace
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    private char[][] GameArr;
    private int row_size;
    private int col_size;
    private int win_num;
    /**
     * constructor to create a 2D array and initializes all values to blank spaces
     * @pre there are no values in 2D array
     * @post 2D array is created and = [getNumRows][getNumColumns]
     * 2D array is initialized to = ' '
     */
    public GameBoard(int totalRow, int totalCol, int numWin)
    {
        row_size = totalRow;
        col_size = totalCol;
        win_num = numWin;
        //create a new 2D array
        GameArr = new char [row_size][col_size];
        //initialize all elements of 2D array to empty spaces
        for (int i = 0; i < row_size; i++)
        {
            for (int j = 0; j < col_size; j++)
            {
                GameArr[i][j] = space;
            }
        }
    }
    //primary
    public void placeMarker(BoardPosition marker, char player) {
        GameArr[marker.getRow()][marker.getColumn()] = player;
    }
    //primary
    public char whatsAtPos(BoardPosition pos) {

        return GameArr[pos.getRow()][pos.getColumn()];
    }
    //primary
    public int getNumRows() { return row_size; }
    //primary
    public int getNumColumns() {
        return col_size;
    }
    //primary
    public int getNumToWin() {
        return win_num;
    }
    //primary
    public char getSpace() {
        return space;
    }

}
