import java.io.Serializable;

public final class Vec2 implements Serializable
{
    private int x;
    private int y;

    public Vec2(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    //Overriding this will ensure use of ArrayList.contains(e) in Pathfinder correctly compares values.
    @Override
    public boolean equals(Object o) {
        Vec2 other = (Vec2) o;
        return other.getX() == this.getX() && other.getY() == this.getY();
    }
}
