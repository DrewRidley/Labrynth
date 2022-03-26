import java.util.ArrayList;

public class Board implements java.io.Serializable {
    private Tile[][] map;

    public Board(Tile[][] tileMap) {
        this.map = tileMap;
    }

    public Tile getTile(Vec2 pos) {
        return map[pos.getX()][pos.getY()];
    }

    public void setTile(Vec2 pos, Tile t) {
        map[pos.getX()][pos.getY()] = t;
    }

    public int getLen() {
        return this.map.length;
    }

    public static Board random(int size) {
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

        return new Board(board);
    }

    public static Board rows(int size) {
        Tile[][] board = new Tile[size][size]; 
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile(true, false, true, false);
            }
        }

        return new Board(board);
    }

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
        for (int y = 0; y < map.length - 1; y++) {
            for (int x = 0; x < map[y].length - 1; x++) {
                sb.append(map[x][y].toString());
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    //Prints the board, hightlighting the specified tiles with green.
    public void printHighlight(ArrayList<Vec2> highlights) {
        for (int y = 0; y < map.length - 1; y++) {
            for (int x = 0; x < map[y].length - 1; x++) {
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
