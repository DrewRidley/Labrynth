package com.ridley;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Labrynth extends Application {
    private BorderPane rootPane;
    private GridPane gridPane;

    private Game currentGame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set the title of the window.
        primaryStage.setTitle("Labrynth v0.0.1");

        //Initialize the Panes.
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        rootPane = new BorderPane();
        rootPane.setCenter(gridPane);

        primaryStage.setScene(new Scene(rootPane, 1000, 600));
        primaryStage.show();

        Game currentGame = new Game();
        currentGame.newGame();



        //Populate the gridPane with the Tiles.
        for(int x = 0; x < currentGame.getBoard().getLen(); x++) {
            for (int y = 0; y < currentGame.getBoard().getLen(); y++) {
                //Get the tile in the given position,
                Tile tile = currentGame.getBoard().getTile(new Vec2(x, y));

                //Get the tile's imageview (this permits rotation).
                StackPane stack = tile.getStack();
                stack.setOnDragOver((drag) -> {
                    System.out.println("Accept transfer of a circle onto this tile.");
                    drag.acceptTransferModes(TransferMode.MOVE);
                });
                stack.setOnDragDropped((e) -> {
                    Dragboard db = e.getDragboard();
                    stack.getChildren().add((Circle )e.getGestureSource());
                    e.setDropCompleted(true);
                });
                stack.setOnDragEntered((dragEnter) -> {
                    stack.setStyle("-fx-effect: innershadow(gaussian, #FFD700, 10, 10, 10, 10);");
                    System.out.println("Circle dragged into!");

                    dragEnter.consume();
                });

                //Highlight navigable tiles initially.
                ArrayList<Vec2> paths = currentGame.getPaths(new Vec2(0, 0));
                /*
                if (paths.contains(new Vec2(x, y))) {
                    stack.setStyle("-fx-effect: innershadow(gaussian, #FFD700, 10, 10, 10, 10);");
                }
                 */

                /*
                stack.setOnMouseClicked((event) -> {
                    if(event.getButton().equals(MouseButton.PRIMARY)) {
                        tile.rotateRight();
                    }
                    if (event.getButton().equals(MouseButton.SECONDARY)) {
                        tile.rotateLeft();
                    }

                    ArrayList<Vec2> reachable = currentGame.getPaths(new Vec2(0, 0));
                    for(int xTemp = 0; xTemp < currentGame.getBoard().getLen(); xTemp++) {
                        for (int yTemp = 0; yTemp < currentGame.getBoard().getLen(); yTemp++) {
                            try {
                                if(reachable.contains(new Vec2(xTemp, yTemp))) {
                                    currentGame.getBoard().getTile(new Vec2(xTemp, yTemp)).getStack().setStyle("-fx-effect: innershadow(gaussian, #FFD700, 1, 2.0, 0, 0);");
                                }
                                else {
                                    currentGame.getBoard().getTile(new Vec2(xTemp, yTemp)).getStack().setStyle("");
                                }
                            }
                            catch (Exception ex) {

                            }

                        }
                    }
                    //currentGame.getBoard().printHighlight(currentGame.getPaths(new Vec2(0, 0)));
                });
                 */


                //Add the stackPane to the gridpane.
                gridPane.add(stack, x, y);
            }
        }

        Circle pawn = new Circle();
        pawn.setCenterX(250f);
        pawn.setCenterY(250f);
        pawn.setRadius(30f);
        pawn.setFill(Color.rgb(255, 255, 0));
        pawn.setStroke(Color.rgb(255, 255, 0));
    }
}
