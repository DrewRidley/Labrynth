import java.util.ArrayList;

class Game implements java.io.Serializable 
{
    public Game() {

    }

    
    private Tile[][] board;

    private Pawn[] pawns;

    //Which pawn is currently making their move.
    private int currentTurn;

    private Pathfinder pf;


    //Creates a fresh game board.
    public Tile[][] newBoard() {
        board = new Tile[7][7];
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                Tile tile;

                int type = getRandom(1, 3);
                if(type == 1) {
                    //This is a straight piece.
                    tile = new Tile(true, false, true, false);
                }
                if(type == 2) {
                    //This is a corner piece.
                    tile = new Tile(true, true, false, false);
                }
                else {
                    //This is a t-piece.
                    tile = new Tile(true, true, true, false);
                }

                int rots = getRandom(0,4);
                for(int i = 0; i < rots; i++) {
                    tile.rotateRight();
                }

                board[row][col] = tile;
                
            }
        }
        return board;
    }

    //To satisfy user story 2.
    public void setBoard(Tile[][] newB) {
        this.board = newB;
    }

    private int getRandom(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    public void startGame(int players, int ai) {
        //Generate a fresh board.
        this.board = newBoard();
        this.pf = new Pathfinder(board);
        ArrayList<Vec2> positions = pf.getValidTiles(new Vec2(4,4));
        positions.add(new Vec2(4, 4));
        printBoardAnnotated(positions);
    }

    private void printBoard() {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

    private void printBoardAnnotated(ArrayList<Vec2> annotatedTiles) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                if(annotatedTiles.contains(new Vec2(row, col)))
                    System.out.print(board[row][col].toGreenString());
                else
                    System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }

}