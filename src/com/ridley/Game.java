package com.ridley;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

class Game implements java.io.Serializable 
{
    //The board itself.
    private Board board;

    //A list of pawns in the game.
    private Pawn[] pawns;

    //Marked as transient so the JVM doesn't attempt serialization.
    private transient Pathfinder pf;

    //Initializes a new game.
    public void newGame() {
        board = new Board();
        board.random(7);
        pf = new Pathfinder(board);

        pawns = new Pawn[1];
        pawns[0] = new PlayerPawn(Color.Yellow);

        board.getTile(new Vec2(0,0)).setPawn(pawns[0]);
    }

    public ArrayList<Vec2> getPaths(Vec2 origin) {
        return pf.getValidTiles(origin);
    }

    //Loads a game from the filepath.
    public void fromFile(String filePath) {

    }

    //Will write this game to the specified file.
    public void toFile(String filePath) {

    }

    public Board getBoard() {
        return this.board;
    }
}