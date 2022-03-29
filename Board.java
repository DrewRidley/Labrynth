import java.util.ArrayList;

///------------------
/// Class: Board
/// Author: Drew Ridley
/// Purpose: To represent a boaed with methods to manipulate it according to the game's rules.
/// Date Modified: 3/25/22.
/// Methods: getTile(Vec2): Tile, getLen(): int, getWidth(): int, getHeight(): int, getTiles(): Tile[][]
public class Board implements java.io.Serializable {
    private Tile[][] map;

    //The piece currently outside of the board
    private Tile activePiece;

    public Tile getTile(Vec2 pos) {
        return map[pos.getX()][pos.getY()];
    }
    
    //Should rarely be used 
    public void setTile(Vec2 pos, Tile t) {
        map[pos.getX()][pos.getY()] = t;
    }

    public int getLen() {
        return this.map.length;
    }

    //Insert the tile into 'row' from the top.
    public void insertRowTop(int row) {
        Tile newActiveTile = map[row][this.map.length - 1];

        for (int i = 0; i < this.map.length; i++) {
            map[row][i] = map[row][i - 1 ];
        }

        for (int i = this.map.length - 1; i >= 0; i++) {
            this.map[row][i] = this.map[row][i - 1];
        }

        activePiece = newActiveTile;
    }

    public void insertRowBottom(int row) {
        Tile newActiveTile = map[row][0];
        
        for (int i = 0; i < this.map.length - 1; i++) {
            this.map[row][i] = this.map[row][i + 1];
        }

        //Tile newActiveTile = map[row][this.map.length - 1];
        this.map[row][this.map.length - 1] = activePiece;
        activePiece = newActiveTile;
    }

    public void insertColLeft(int col) {

    }

    public void insertColRight(int col) {

    }

    //Utility and debug methods below -----------------------------------

    public void random(int size) {
        Tile[][] board = new Tile[size][size];
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

        this.map = board;
    }


    //Generates a static board of rows (for testing)
    public static Board rows(int size) {
        Tile[][] board = new Tile[size][size]; 
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile(true, false, true, false);
            }
        }

        return new Board(board);
    }

    //Generates a static board of columns (for testing)
    public static Board cols(int size) {
        Tile[][] board = new Tile[size][size]; 
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile(false, true, false, true);
            }
        }

        return new Board(board);
    }

    private static int getRandom(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                sb.append(map[x][y].toString());
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    //Prints the board, hightlighting the specified tiles with green.
    public void printHighlight(ArrayList<Vec2> highlights) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if(highlights.contains(new Vec2(x, y))) {
                    System.out.print("\u001B[32m" + map[x][y].toString() + "\u001B[0m");
                }
                else {
                    System.out.print(map[x][y].toString());
                }
            }
            System.out.println();
        }
    }

}
