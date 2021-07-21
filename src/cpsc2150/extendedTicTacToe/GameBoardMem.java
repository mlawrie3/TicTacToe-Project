package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GameBoardMem provides a memory efficient implementation of game board
 *   using a map
 * @invariant MAX game board size is 100x100
 * 0,0 is always top left of board
 * player marker cannot be placed out of bounds
 * player marker cannot be added to full game board
 * Correspondence: totalRow = getNumRows totalCol = getNumColumns
 *   numWin = getNumToWin space = returnSpace
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    /*The key field of our Map will be a Character that represents the player, so each
    player will get their own entry in the Map. The value associated with that player is
    be a List of
    BoardPositions that the player occupies on the board.*/
    private Map<Character, List<BoardPosition>> GameArr;
    private int row_size;
    private int col_size;
    private int win_num;
    /**
     * constructor to create a Map without using space in memory
     * @pre there are no values in 2D array
     * @post 2D array is created and = new HashMap<>()
     * 2D array is initialized to empty map
     */
    public GameBoardMem(int totalRow, int totalCol, int numWin)
    {
        row_size = totalRow;
        col_size = totalCol;
        win_num = numWin;
        GameArr = new HashMap<>();
    }
    //primary
    public void placeMarker(BoardPosition marker, char player) {
        //if GameArr map contains player as a key, so retrieve board position
        //associated with that player
        if(GameArr.containsKey(player)) {
            GameArr.get(player).add(marker);
            return;
        }
        //if GameArr map does not contain player as a key
        else if(!(GameArr.containsKey(player))){
            //create empty list
            List<BoardPosition> listTest = new ArrayList<>();
            //update by adding position where marker should be
            listTest.add(marker);
            GameArr.put(player, listTest);
            return;
        }
    }
    //primary
    public char whatsAtPos(BoardPosition pos) {
        //iterate over entries using entrySet
        for(Map.Entry<Character, List<BoardPosition>> entry : GameArr.entrySet()) {
            //loop through list
            for(int i = 0; i < entry.getValue().size(); i++) {
                //if index = position return key value
                if(entry.getValue().get(i).equals(pos))
                {
                    //return value at pos
                    return entry.getKey();
                }
            }
        }
        //default
        return space;
    }
    //override this function to create a more efficient implementation
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        return GameArr.get(player).contains(pos);
    }
    //primary, needs access to private data
    public int getNumRows() {
        return row_size;
    }
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

