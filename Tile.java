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