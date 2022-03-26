import java.util.ArrayList;


///------------------
/// Class: Pathfinder
/// Author: Drew Ridley
/// Purpose: To find valid paths in the Labrynth game board.
/// Date Modified: 3/25/22.
/// Methods: getValidTiles(Vec2): ArrayList<Vec2>, validatePath(Vec2, Vec2): boolean
public class Pathfinder {
    public Pathfinder(Board brd) {
        gameBoard = brd;
    }

    private Board gameBoard;
    private ArrayList<Vec2> visitedPositions;
    private ArrayList<Vec2> navigablePositions;


    private void checkTile(Vec2 pos) {
        visitedPositions.add(pos);
        //Will visit the tile, investigate possible paths to neighbors, and invoke again from those neighbors.
        Tile tile = gameBoard.getTile(pos);

        //The current tile permits movement to a left tile and such tile exists,
        if(pos.getX() > 0 && tile.leftOpen()) {
            Vec2 targetPos = new Vec2(pos.getX() - 1, pos.getY());
            Tile target = gameBoard.getTile(targetPos);

            if(target.rightOpen()) {
                //Only visit a tile if it has not been visited before to prevent infinite recursion.
                if(!visitedPositions.contains(targetPos))
                {
                    navigablePositions.add(targetPos);
                    checkTile(targetPos);
                }
                    
            }
        }

        //A tile above this tile exists and our tile permits movement to it.
        if(pos.getY() < gameBoard.getLen() - 1 && tile.topOpen()) {
            Vec2 targetPos = new Vec2(pos.getX(), pos.getY() + 1);
            Tile target = gameBoard.getTile(targetPos);

            if(target.bottomOpen()) {
                //Only visit a tile if it has not been visited before to prevent infinite recursion.
                if(!visitedPositions.contains(targetPos))
                {
                    navigablePositions.add(targetPos);
                    checkTile(targetPos);
                }
            }
        }

        //A tile to the right of this tile exists and our tile permits movement to this tile.
        if(pos.getX() < gameBoard.getLen() - 1 && tile.rightOpen()) {
            Vec2 targetPos = new Vec2(pos.getX() + 1, pos.getY());
            Tile target = gameBoard.getTile(targetPos);

            if(target.leftOpen()) {
                //Only visit a tile if it has not been visited before to prevent infinite recursion.
                if(!visitedPositions.contains(targetPos))
                {
                    navigablePositions.add(targetPos);
                    checkTile(targetPos);
                }
            }
        }

        //A tile below us exists and we can move to it,
        if(pos.getY() > 0 && tile.bottomOpen()) {
            Vec2 targetPos = new Vec2(pos.getX(), pos.getY() - 1);
            Tile target = gameBoard.getTile(targetPos);

            //The target allows us to enter.
            if(target.topOpen()) {
                //Only visit a tile if it has not been visited before to prevent infinite recursion.
                if(!visitedPositions.contains(targetPos))
                {
                    navigablePositions.add(targetPos);
                    checkTile(targetPos);
                }
            }
        }

        //If the code reaches this point; there are two possibilities.
        //Either the recursion is complete (all tiles have been visited),
        //Or the tile in question is not a member of the board (is currently being played).
    }

    //Returns all navigable tiles from the origin.
    public ArrayList<Vec2> getValidTiles(Vec2 origin) {
        visitedPositions = new ArrayList<Vec2>();
        navigablePositions = new ArrayList<Vec2>();

        checkTile(origin);
        return navigablePositions;
    }

    //Returns whether navigation between the origin and destination is possible
    public boolean validatePath(Vec2 origin, Vec2 dest) {
        return getValidTiles(origin).contains(dest);
    }

    //Returns the closest navigable tile to the destination. Will return the destination if there is a path.
    public Vec2 minimizeDist(Vec2 origin, Vec2 dest) {
        return Vec2.nullVec();
    }
}
