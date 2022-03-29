import java.util.ArrayList;

///------------------
/// Class: Game
/// Author: Drew Ridley
/// Purpose: To represent a serializable game state.
/// Date Modified: 3/25/22.
/// Methods: main(string[] args), load
class Game implements java.io.Serializable 
{
    //The board itself.
    private Board board;

    //A list of pawns in the game.
    private Pawn[] pawns;

    //Marked as transient so the JVM doesn't attempt serialization.
    private transient Pathfinder pf;

    public void startGame(int players, int ai) {
        //Generate a fresh board.
        this.board = new Board();
        this.board.random(7);
        
        this.pf = new Pathfinder(board);
        Vec2 startPos = new Vec2(10, 10);

        //System.out.println(board);

        //Get an array of all navigable tiles from (0,0).
        ArrayList<Vec2> highlights = pf.getValidTiles(startPos);

        highlights.add(startPos);
        //Print the entire board, highlighting the navigable tiles in green.
        this.board.printHighlight(highlights);
    }
}