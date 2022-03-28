///------------------
/// Class: Tile
/// Author: Drew Ridley
/// Purpose: To represent a tile on the game board.
/// Date Modified: 3/25/22.
/// Methods: leftOpen(): boolean, rightOpen(): boolean, topOpen(): boolean, bottomOpen(): boolean
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
            return "╠ ";
        }
        if (topOpen() && bottomOpen() && leftOpen()) {
            return "╣ ";
        }   
        if (topOpen() && rightOpen() && leftOpen()) {
            return "╩ ";
        }
        if (bottomOpen() && rightOpen() && leftOpen()) {
            return "╦ ";
        }

        //Straight-Piece conditions.
        if (topOpen() && bottomOpen()) {
            return "║ ";
        }
        if(leftOpen() && rightOpen()) {
            return "═ ";
        }


        //Corner-piece conditions.
        if (bottomOpen() && rightOpen()) {
            return "╔ ";
        }
        if (bottomOpen() && leftOpen()) {
            return "╗ ";
        }
        if (topOpen() && rightOpen()) {
            return "╚ ";
        }
        if (topOpen() && leftOpen()) {
            return "╝ ";
        }

        return "";
    }

}