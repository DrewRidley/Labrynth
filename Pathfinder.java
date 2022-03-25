import java.util.ArrayList;


///------------------
/// Class: Pathfinder
/// Author: Drew Ridley
/// Purpose: To find valid paths in the Labrynth game board.
/// Date Modified: 3/25/22.
/// Methods: getValidTiles(Vec2): ArrayList<Vec2>, validatePath(Vec2, Vec2): boolean
public class Pathfinder {
    public Pathfinder(Tile[][] tileMap) {
        gameBoard = tileMap;
    }

    private Tile[][] gameBoard;
    private ArrayList<Vec2> visitedPositions;
    private ArrayList<Vec2> navigablePositions;


    private void checkTile(Vec2 pos) {
        visitedPositions.add(pos);
        //Will visit the tile, investigate possible paths to neighbors, and invoke again from those neighbors.
        Tile tile = gameBoard[pos.getX()][pos.getY()];

        //The current tile permits movement to a left tile and such tile exists,
        if(pos.getX() > 0 && tile.leftOpen()) {
            Tile target = gameBoard[pos.getX() - 1][pos.getY()];
            Vec2 targetPos = new Vec2(pos.getX() - 1, pos.getY());

            if(target.rightOpen()) {
                //Both this tile and the target tile permit the move, so it exists.
                navigablePositions.add(targetPos);

                //Only visit a tile if it has not been visited before to prevent infinite recursion.
                if(!visitedPositions.contains(pos))
                    checkTile(targetPos);
            }
        }

        //A tile above this tile exists and our tile permits movement to it.
        if(pos.getY() < gameBoard[0].length - 1 && tile.topOpen()) {
            Tile target = gameBoard[pos.getX()][pos.getY() + 1];
            Vec2 targetPos = new Vec2(pos.getX(), pos.getY() + 1);

            if(target.bottomOpen()) {
                //Both this tile and the target tile permit the move, so it exists.
                navigablePositions.add(targetPos);

                //Start navigating from this tile.
                if(!visitedPositions.contains(pos))
                    checkTile(targetPos);
            }
        }

        //A tile to the right of this tile exists and our tile permits movement to this tile.
        if(pos.getX() < gameBoard.length - 1 && tile.rightOpen()) {
            Tile target = gameBoard[pos.getX() + 1][pos.getY()];
            Vec2 targetPos = new Vec2(pos.getX() + 1, pos.getY());

            if(target.leftOpen()) {
                //Both this tile and the target tile permit the move, so it exists.
                navigablePositions.add(targetPos);

                //Start navigating from this tile.
                if(!visitedPositions.contains(pos))
                    checkTile(targetPos);
            }
        }

        //A tile below us exists and we can move to it,
        if(pos.getY() > 0 && tile.bottomOpen()) {
            Tile target = gameBoard[pos.getX()][pos.getY() - 1];
            Vec2 targetPos = new Vec2(pos.getX(), pos.getY() - 1);

            //The target allows us to enter.
            if(target.topOpen()) {
                navigablePositions.add(targetPos);

                //Start navigating from this tile.
                if(!visitedPositions.contains(pos))
                    checkTile(targetPos);
            }
        }

        //If the code reaches this point; there are two possibilities.
        //Either the recursion is complete (all tiles have been visited),
        //Or the tile in question is not a member of the board (is currently being played).
    }

    //Returns all navigable tiles from the origin.
    public ArrayList<Vec2> getValidTiles(Vec2 origin) {
        checkTile(origin);
        return navigablePositions;
    }

    //Returns whether navigation between the origin and destination is possible
    public boolean validatePath(Vec2 origin, Vec2 dest) {
        checkTile(origin);

        //Should function because Vec2 overrides the toEquals(o) method.
        return navigablePositions.contains(dest);
    }
}
