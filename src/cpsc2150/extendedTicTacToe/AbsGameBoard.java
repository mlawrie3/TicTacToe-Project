package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * Method that returns string that shows the game board
     * @return returns one string that displays entire game board
     * @pre GameBoard constructor must have been called
     * @post toString = display of game board
     */
    @Override
    public String toString() {
        int twoNum = 10;
        String displayBoard = "";
        int count = 0;
        for (int i = 0; i < getNumColumns(); i++)
        {
            //if the column number is between 0 and 9
            displayBoard += " ";
            if (i < twoNum && i != (getNumColumns() - 1)) {
                displayBoard += " |" + i;
            }
            //if the column number is 10 or greater
            if (i >= twoNum && i != (getNumColumns() - 1)) {
                //when reaching the first double digit number...
                if(i == 10) {
                    displayBoard += " |" + i;
                }
                else if(i != 10) {
                    displayBoard += "|" + i;
                }
            }
            //format the last column on the board to account for double digits
            if(i == getNumColumns() - 1) {
                if(i < twoNum) {
                    displayBoard += " |" + i + "  ";
                }
                else if (i >= twoNum){
                    displayBoard += "|" + i + " ";
                }
            }

        }
        displayBoard += "|\n";
        for (int i = 0; i < getNumRows(); i++)
        {
            if(i < twoNum) {
                displayBoard += count + " ";
            }
            else{
                displayBoard += count;
            }
            for (int j = 0; j < getNumColumns(); j++)
            {
                BoardPosition pos = new BoardPosition(i, j);
                displayBoard += "| " + whatsAtPos(pos) + " ";
            }
            count++;
            displayBoard += "|\n";
        }
        return displayBoard;
    }
}
