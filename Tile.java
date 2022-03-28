//Tile class represents a tile in the game and contains all state unique to that tile. 
//The class implements the interface Serializable so that the JVM serializes it correctly to the disk.
public class Tile implements java.io.Serializable
{
    private boolean leftOpen;
    private boolean topOpen;
    private boolean rightOpen;
    private boolean bottomOpen;

    public Tile(boolean leftOpen, boolean topOpen, boolean rightOpen, boolean bottomOpen) {
        this.leftOpen = leftOpen;
        this.topOpen = topOpen;
        this.rightOpen = rightOpen;
        this.bottomOpen = bottomOpen;
    }

    //All of the players currently residing on this tile.
    private Pawn[] players;

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

    //Rotates this tile right by 90 degrees.
    public void rotateRight() {
        boolean temp = leftOpen;
        leftOpen = bottomOpen;
        bottomOpen = rightOpen;
        rightOpen = topOpen;
        topOpen = temp;
    }

    //Rotates this tile left by 90 degrees.
    public void rotateLeft() {
        boolean temp = rightOpen;
        rightOpen = bottomOpen;
        bottomOpen = leftOpen;
        leftOpen = topOpen;
        topOpen = temp;
    }

    //Prints the tile as a ASCII character that can cleanly be displayed to the console.
    @Override
    public String toString() {
        //T-Piece conditions.
        if (topOpen() && bottomOpen() && rightOpen()) {
            return "╠";
        }
        if (topOpen() && bottomOpen() && leftOpen()) {
            return "╣";
        }   
        if (topOpen() && rightOpen() && leftOpen()) {
            return "╩";
        }
        if (bottomOpen() && rightOpen() && leftOpen()) {
            return "╦";
        }

        //Straight-Piece conditions.
        if (topOpen() && bottomOpen()) {
            return "║";
        }
        if(leftOpen() && rightOpen()) {
            return "═";
        }


        //Corner-piece conditions.
        if (bottomOpen() && rightOpen()) {
            return "╔";
        }
        if (bottomOpen() && leftOpen()) {
            return "╗";
        }
        if (topOpen() && rightOpen()) {
            return "╚";
        }
        if (topOpen() && leftOpen()) {
            return "╝";
        }

        return "";
    }

}