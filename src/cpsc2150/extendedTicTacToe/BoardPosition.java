package cpsc2150.extendedTicTacToe;


public class BoardPosition {
    private int row;
    private int column;
    /**
     *Constructor that initializes row and column values
     * @param r is the row position, c is the column position
     * @post row = r and column = c
     */
    public BoardPosition(int r, int c)
    {
        row = r;
        column = c;
    }
    /**
     * Getter to return row value
     * @return returns column value
     * @pre
     * @post getRow = row
     */
    public int getRow()
    {
        return row;
    }
    /**
     * Getter to return column value
     * @return returns column value
     * @pre
     * @post getColumn = column
     */
    public int getColumn()
    {
        return column;
    }
    /**
     * Overridden equals returns true if two board positions are equal
     * @param inst is inherited from the object class
     * @return true if two positions have same row and column and
     * false if they do not
     * @post equals = true if board positions are same
     * equals = false if board positions are not equal
     */
    @Override
    public boolean equals(Object inst) {
        if (inst == this)
        {
            return true;
        }
        if (!(inst instanceof BoardPosition))
        {
            return false;
        }
        BoardPosition pos = (BoardPosition) inst;
        return column == pos.column && row == pos.row;
    }
    /**
     * Overridden toString creates a string in format <row>, <column>
     * @return a string containing row and column values in format <row>, <column>
     * @post toString = <row>, <column>
     */
    @Override
    public String toString() {
        //create a string in the following format “<row>,<column>”
        String out = "";
        out += row + ", " + column;
        return out;
    }
}
