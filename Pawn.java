abstract class Pawn 
{
    private Vec2 position;
    private Color color;

    //Getters:

    public Vec2 getPos() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    //Setters

    public void setPos(Vec2 pos) {
        position = pos;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    //Methods
    
    //To be implemented by the AIPawn or PlayerPawn, will be executed to simulate a players entire turn.
    public abstract void move(Pathfinder pf, Tile[][] board);
}