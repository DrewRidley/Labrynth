package com.ridley;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Tile implements java.io.Serializable
{
    //States of the board.
    private boolean leftOpen = false;
    private boolean topOpen = false;
    private boolean rightOpen = false;
    private boolean bottomOpen = false;

    //The image view used to render the tile.
    private transient ImageView imageView;

    //The StackPane to be rendered on the grid.
    private transient StackPane stack;

    //Used to load the correct image.
    private transient TileType type;

    //The pawn currently residing on this board.
    private Pawn pawn;

    public Tile(TileType tt) throws FileNotFoundException {
        type = tt;
        if(type == TileType.Straight) {
            leftOpen = true;
            rightOpen = true;
        }
        if(type == TileType.Corner) {
            leftOpen = true;
            bottomOpen = true;
        }
        if (type == TileType.Junction) {
            leftOpen = true;
            rightOpen = true;
            bottomOpen = true;
        }

        imageView = new ImageView();
        stack = new StackPane();
        stack.getChildren().add(getImgView());
    }

    public StackPane getStack() {
        return this.stack;
    }

    public Pawn getPawn() {
        return this.pawn;
    }

    public void setPawn(Pawn pwn) {
        this.stack.getChildren().remove(this.pawn);
        this.pawn = pwn;
        this.stack.getChildren().add(pwn.getCircle());
    }

    //Returns the image view, after loading an image.
    private ImageView getImgView() throws FileNotFoundException {
        if(imageView.getImage() == null) {
            //The image has to be loaded.
            if (type == TileType.Straight) {
                InputStream imgS = new FileInputStream("./straight.png");
                imageView.setImage(new Image(imgS));
            }
            if (type == TileType.Corner) {
                InputStream imgS = new FileInputStream("./corner.png");
                imageView.setImage(new Image(imgS));
            }
            if (type == TileType.Junction) {
                InputStream imgS = new FileInputStream("./junction.png");
                imageView.setImage(new Image(imgS));
            }
        }

        imageView.setFitHeight(80);
        imageView.setFitWidth(80);

        return imageView;
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

        //After another rotation it will be 360 degrees of rotation, so simply set it to 0.
        if(imageView.getRotate() == 270) {
            imageView.setRotate(0);
        }
        else {
            imageView.setRotate(imageView.getRotate() + 90);
        }
    }

    //Rotates this tile left by 90 degrees.
    public void rotateLeft() {
        boolean temp = rightOpen;
        rightOpen = bottomOpen;
        bottomOpen = leftOpen;
        leftOpen = topOpen;
        topOpen = temp;
        if(imageView.getRotate() == 0) {
            imageView.setRotate(270);
        }
        else {
            imageView.setRotate(imageView.getRotate() - 90);
        }
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