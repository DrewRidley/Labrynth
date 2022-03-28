import java.util.ArrayList;

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
        this.board = Board.random(20);
        this.pf = new Pathfinder(board);
        Vec2 startPos = new Vec2(0, 0);

        //System.out.println(board);

        //Get an array of all navigable tiles from (0,0).
        ArrayList<Vec2> highlights = pf.getValidTiles(startPos);

        highlights.add(startPos);
        //Print the entire board, highlighting the navigable tiles in green.
        this.board.printHighlight(highlights);
    }
}