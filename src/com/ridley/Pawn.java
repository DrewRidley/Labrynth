package com.ridley;

import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

abstract class Pawn implements java.io.Serializable
{
    private Vec2 position;
    private Color color;
    private Circle circ;

    public Pawn(Color clr) {
        this.color = clr;

        if(clr == Color.Green) {
            circ = new Circle(8, Paint.valueOf("green"));
        }
        if (clr == Color.Yellow) {
            circ = new Circle(8, Paint.valueOf("yellow"));
        }
        if (clr == Color.Black) {
            circ = new Circle(8, Paint.valueOf("black"));
        }
        if (clr == Color.Red) {
            circ = new Circle(8, Paint.valueOf("red"));
        }

        circ.setOnDragDetected((event) -> {
            System.out.println("Circle dragged out!");
            Dragboard db = circ.startDragAndDrop(TransferMode.MOVE);
            event.consume();
        });

        circ.setOnDragDone((event) -> {
            System.out.println("Drag done!");
        });

        circ.setOnDragOver((event) -> {
            System.out.println("Drag over!");
        });

        circ.setOnDragExited((event) -> {
            System.out.println("Drag exit!");
        });

        circ.setOnDragDropped((event) -> {
            System.out.println("Circle has been dropped!");
        });

    }

    public Circle getCircle() {
        return circ;
    }

    public Vec2 getPos() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void setPos(Vec2 pos) {
        position = pos;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    //To be implemented by the AIPawn or PlayerPawn, will be executed to simulate a players entire turn.
    public abstract void move(Pathfinder pf, Tile[][] board);
}