package cpsc2150.extendedTicTacToe;
/**
 * Interface includes functions needed for GameBoard and GameBoardMem implementations
 * Initialization ensures: all positions on the board are equal to
 *   blank spaces and board is row_size * col_size
 * Defines: Board is abstractly a 2 dimensional grid of characters.
 *    MAX_ROW - Z, MAX_COL - Z, WIN_NUM -Z, MIN_ROW - Z, MIN_COL - Z,
 *    WIN_MIN - Z, WIN_MAX - Z, space
 * Constraints: MAX_ROW_SIZE = 100, MAX_COLUMN_SIZE = 100, WIN_MAX = 25,
 *   space = ' '
 */
public interface IGameBoard {
    public static final int MAX_ROW = 100;
    public static final int MAX_COL = 100;
    public static final int MIN_ROW = 3;
    public static final int MIN_COL = 3;
    public static final int WIN_MAX = 25;
    public static final int WIN_MIN = 3;
    public static final char space = ' ';
    /**
     * Default method to check to see if player position is available and in bounds
     * @param pos represents player's chosen position
     * @return true if position specified in pos is available
     * false if position specified in pos is not available
     * @pre player chose position in getLocation
     * @post checkSpace = true if  position is available
     * checkSpace = false if  position is != ' '
     * MIN_ROW * MIN_COL < position < MAX_ROW * MAX_COL
     */
    default public boolean checkSpace(BoardPosition pos) {
        if (whatsAtPos(pos) == getSpace() && pos.getRow() < getNumRows() && pos.getColumn() < getNumColumns()) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Method to place character of player in specified position
     * @param marker is the board position specified by the player
     * player is the player character, either X or O
     * @pre position is != ' ' and MIN_ROW * MIN_COL < position < MAX_ROW * MAX_COL
     * @post player = position specified by the player
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * Default method to return true if lastPos placed resulted in winner
     * @param lastPos is last position in board specified by player where marker was placed
     * @return true if lastPost results in winner
     * false if lastPos does not result in winner
     * @pre last position is != ' ' and MIN_ROW * MIN_COL < last position < MAX_ROW * MAX_COL
     * @post checkForWin = true if lastPos placed resulted in winner
     * checkForWinner = false if lastPos placed did not result in winner
     */
    default public boolean checkForWinner(BoardPosition lastPos) {
        char player = whatsAtPos(lastPos);
        if(checkHorizontalWin(lastPos, player) == true && (checkForDraw() == false)) {
            return true;
        }
        if(checkVerticalWin(lastPos, player) == true && (checkForDraw() == false)) {
            return true;
        }
        if(checkDiagonalWin(lastPos, player) == true && (checkForDraw() == false)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Default method to return true if there are no free positions remaining, resulting in a draw
     * @return true if there are no remaining spaces left after placing lastPos
     * false if there are remaining spaces left after placing lastPos
     * @pre position is != ' ' and MIN_ROW * MIN_COL < position < MAX_ROW * MAX_COL
     * @post checkForDraw = false if there are remaining spaces left after placing lastPos
     */
    default public boolean checkForDraw() {
        for (int i = 0; i < getNumRows(); i++)
        {
            for (int j = 0; j < getNumColumns(); j++)
            {
                BoardPosition pos = new BoardPosition(i,j);
                if (whatsAtPos(pos) == getSpace())
                {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Default method that checks to see if lastPos placed resulted in a 5 in a row horizontally
     * @param lastPos is last position in board specified by player where marker was placed
     * player is the player character, either X or O
     * @return true if lastPos placed resulted in 5 in a row horizontally
     * false if lastPos placed did not result in 5 in a row horizontally
     * @pre last position is != ' ' and MIN_ROW * MIN_COL < last position < MAX_ROW * MAX_COL
     * winCount = 0
     * @post checkHorizontalWin = true if lastPos placed resulted in 5 in a row horizontally
     * checkHorizontalWin = false if lastPos placed did not result in 5 in a row horizontally
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int winCount = 0;
        for (int i = 0; i < getNumColumns(); i++)
        {
            BoardPosition current = new BoardPosition(lastPos.getRow(), i);
            if (isPlayerAtPos(current, player))
            {
                winCount++;
            }
            else
            {
                winCount = 0;
            }
            if (winCount == getNumToWin())
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Default method that checks to see if lastPos placed resulted in a 5 in a row vertically
     * @param lastPos is last position in board specified by player where marker was placed
     * player is the player character, either X or O
     * @return true if lastPos placed resulted in 5 in a row vertically
     * false if lastPos placed did not result in 5 in a row vertically
     * @pre last position is != ' ' and MIN_ROW * MIN_COL < last position < MAX_ROW * MAX_COL
     * winCount = 0
     * @post checkHorizontalWin = true if lastPos placed resulted in 5 in a row vertically
     * checkHorizontalWin = false if lastPos placed did not result in 5 in a row vertically
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int winCount = 0;
        for (int i = 0; i < getNumRows(); i++)
        {
            BoardPosition current = new BoardPosition(i, lastPos.getColumn());
            if(isPlayerAtPos(current, player))
            {
                winCount++;
            }
            else {
                winCount = 0;
            }
            if (winCount == getNumToWin())
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Default method that checks to see if lastPos placed resulted in a 5 in a row diagonally
     * @param lastPos is last position in board specified by player where marker was placed
     * player is the player character, either X or O
     * @return true if lastPos placed resulted in 5 in a row diagonally
     * false if lastPos placed did not result in 5 in a row diagonally
     * @pre last position is != ' ' and MIN_ROW * MIN_COL < last position < MAX_ROW * MAX_COL
     * winCount = 0
     * @post checkHorizontalWin = true if lastPos placed resulted in 5 in a row diagonally
     * checkHorizontalWin = false if lastPos placed did not result in 5 in a row diagonally
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int rowCopy = lastPos.getRow();
        int colCopy = lastPos.getColumn();
        int winCount = 0;
        while (rowCopy > 0 && colCopy > 0)
        {
            colCopy--;
            rowCopy--;
        }
        while (rowCopy < getNumRows() && colCopy < getNumColumns())
        {
            BoardPosition current = new BoardPosition( rowCopy, colCopy);
            if(isPlayerAtPos(current, player))
            {
                winCount++;
            }
            else {
                winCount = 0;
            }
            if(winCount == getNumToWin())
            {
                return true;
            }
            rowCopy++;
            colCopy++;
        }
        //reset
        rowCopy = lastPos.getRow();
        colCopy = lastPos.getColumn();
        winCount = 0;
        while (rowCopy > 0 && colCopy < getNumColumns() - 1)
        {
            colCopy++;
            rowCopy--;
        }
        while (rowCopy < getNumRows() && colCopy >= 0)
        {
            BoardPosition current = new BoardPosition(rowCopy, colCopy);
            if(isPlayerAtPos(current, player) == true)
            {
                winCount++;
            }
            else {
                winCount = 0;
            }
            if(winCount == getNumToWin())
            {
                return true;
            }
            rowCopy++;
            colCopy--;
        }
        return false;
    }
    /**
     * Method that returns what is on the game board at position pos
     * @param pos is an instance of BoardPosition
     * @return what is on GameBoard at position pos which is either a player marker
     * or a ' '
     * @pre pos = ' ' or MIN_ROW * MIN_COL < pos < MAX_ROW * MAX_COL and
     *    GameBoard constructor must have been called
     * @post whatsAtPos = ' ' or whatsAtPos = player marker
     */
    public char whatsAtPos(BoardPosition pos);
    /**
     * Default method that returns true or false if player is at position pos on game board
     *    if player specifies a memory efficient implementation, the method is overridden
     *    in GameBoardMem
     * param: pos is an instance of BoardPosition and player is the player character
     * return: returns true if player is at pos
     * returns false if player is not at pos
     * @pre pos = ' ' or MIN_ROW * MIN_COL < pos < MAX_ROW * MAX_COL and player = either X or O
     * @post isPlayerAtPos = true if player is at specified position
     * isPlayerAtPos = false if player is not at specified position
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {
        return whatsAtPos(pos) == player;
    }
    /**
     * Method that returns chosen row value
     * @return returns chosen row value
     * @post getNumRows = row_size
     */
    public int getNumRows();
    /**
     * Method that returns chosen column value
     * @return returns chosen column size
     * @post getNumRows = col_size
     */
    public int getNumColumns();
    /**
     * Method that returns number of tokens in a row to win game
     * @return returns the number of tokens to win
     * @pre GameBoard or GameBoardMem constructor must have been called
     * @post getNumToWin = numWin
     */
    public int getNumToWin();
    /**
     * Method that returns blank space
     * @return returns ' '
     * @post getSpace = ' '
     */
    public char getSpace();
}
