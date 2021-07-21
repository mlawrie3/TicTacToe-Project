package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;
    //The screen that provides our view
    private TicTacToeView screen;
    private int playerNum;
    private boolean r = false;
    //player X starts
    private char player = 'X';

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        playerNum = np;
        //The screen should say who’s turn it is
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    // each time button is clicked, run through this code
    public void processButtonClick(int row, int col) {
        //if player chooses to play again
        if(r) {
            newGame();
            r = false;
            return;
        }
        //controller already checked if in bounds/valid
        BoardPosition pos = new BoardPosition(row, col);
        if (this.curGame.checkSpace(pos)) {
            //call placemarker, pass in marker and player
            this.curGame.placeMarker(pos, player);
            //set marker on screen
            this.screen.setMarker(row, col, player);
        }
        //The screen should give an error message if the user selects a spot that is not available
        else {
            this.screen.setMessage("That space is unavailable, please pick again");
            return;
        }
        //In the event of a tie game or a win, the next time the player clicks a button should start
        //a new game.
        if(this.curGame.checkForWinner(pos)) {
            //display who won
            this.screen.setMessage("");
            this.screen.setMessage("Player " + player + " wins!" + "\n" + "Would you like to play again?");
            //tell them to click button to start new game
            r = true;
            return;
        }
        else if(this.curGame.checkForDraw()) {
            //display who won
            this.screen.setMessage("Game has ended in a tie" + "\n" + "Would you like to play again?");
            //tell them to click button to start new game
            r = true;
            return;
            //return to top of method
        }
        //Possible characters start at X, then loop to A when reaching "["
        //So: XYZABCDEFG if players = 10
        else {
            //switch characters
            //If number of players is > 3, so starting at A
            if(player != 'X' && player != 'Y' && player != 'Z') {
                if(player + 1 < 'A' + playerNum - 3) {
                    player += 1;
                }
                else {
                    player = 'X';
                }
            }
            else {
                if(player + 1 < 'X' + playerNum) {
                    player += 1;
                    //If number of players passes Z, start from top of ASCII
                    if(player == '[') {
                        player = 'A';
                    }
                }
                else {
                    player = 'X';
                }
            }
            this.screen.setMessage("It is " + player + "'s turn");
            return;
        }
    }
    //start a new game
    private void newGame() {
        // You do not need to make any changes to this code.
        this.screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
        player = 'X';
    }
}