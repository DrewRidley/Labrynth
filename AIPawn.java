public class AIPawn extends Pawn {

    private final boolean skipTurn = false;

    public void move(Pathfinder pf, Tile[][] map) {
        //To satisfy user story 4.
        if(skipTurn) {
            return;
        }
    }
}