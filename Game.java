import java.util.ArrayList;
import java.util.NavigableMap;

class Game implements java.io.Serializable 
{
    public Game() {
        //Since java arrays are references, the pathfinder does not need to be informed on updates to the game board.
        pf = new Pathfinder(board);
    }

    //Fields:
    
    private Tile[][] board;

    private Pawn[] pawns;

    private Pathfinder pf;

    //Methods:

    //Creates a fresh game board.
    private void newBoard() {
        board = new Tile[7][7];
        
    }
}