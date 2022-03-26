abstract class Pawn implements java.io.Serializable
{
    private Vec2 position;
    private Color color;

    public Vec2 getPos() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void setPos(Vec2 pos) {
        position = pos;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    //An optional initialization method to ensure the pawn is configured before the game begins.
    //Primarily implemented for the possible use of networking.
    public void init() 
    {
        //Do nothing.
    }

    //To be implemented by the AIPawn or PlayerPawn, will be executed to simulate a players entire turn.
    public abstract void move(Pathfinder pf, Tile[][] board);
}