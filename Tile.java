public class Tile 
{
    private boolean leftOpen;
    private boolean topOpen;
    private boolean rightOpen;
    private boolean bottomOpen;

    private Pawn[] players;

    //State getters.

    public boolean leftOpen() {
        return leftOpen;
    }

    public boolean topOpen() {
        return topOpen;
    }

    public boolean rightOpen() {
        return rightOpen;
    }

    public boolean bottomOpen() {
        return bottomOpen;
    }
}