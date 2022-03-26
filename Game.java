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
        this.board = Board.random(50);
        this.pf = new Pathfinder(board);
        Vec2 startPos = new Vec2(1, 1);

        //System.out.println(board);

        ArrayList<Vec2> highlights = pf.getValidTiles(startPos);
        highlights.add(startPos);
        this.board.printHighlight(highlights);
    }
}