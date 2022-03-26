import java.util.ArrayList;

class Game implements java.io.Serializable 
{
    private Board board;

    private Pawn[] pawns;

    //Marked as transient so the JVM doesn't attempt serialization.
    private transient Pathfinder pf;

    public void startGame(int players, int ai) {
        //Generate a fresh board.
        this.board = Board.random(100);
        this.pf = new Pathfinder(board);
        
        System.out.println(this.board);

        Vec2 startPos = new Vec2(3, 0);

        ArrayList<Vec2> highlights = pf.getValidTiles(startPos);
        highlights.add(startPos);
        this.board.printHighlight(highlights);
    }
}