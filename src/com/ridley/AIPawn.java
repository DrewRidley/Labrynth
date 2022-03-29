package com.ridley;

public class AIPawn extends Pawn {

    private final boolean skipTurn = false;

    public AIPawn(Color clr) {
        super(clr);
    }

    public void move(Pathfinder pf, Tile[][] map) {
        //To satisfy user story 4.
        if(skipTurn) {
            return;
        }

        //The AIPawn will now shift a row or column of the map, and then will proceed to move afterwards.
    }
}