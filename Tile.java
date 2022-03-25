public class Tile 
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

    //Rotates this tile right by 90 degrees.
    public void rotateRight() {
        boolean temp = leftOpen;
        leftOpen = bottomOpen;
        bottomOpen = rightOpen;
        rightOpen = topOpen;
        topOpen = temp;
    }

    public void rotateLeft() {
        boolean temp = rightOpen;
        rightOpen = bottomOpen;
        bottomOpen = leftOpen;
        leftOpen = topOpen;
        topOpen = temp;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public String toGreenString() {
        return ANSI_GREEN + this.toString() + ANSI_RESET;
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