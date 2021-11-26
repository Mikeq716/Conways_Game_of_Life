package io.github.mikeq716;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Driver program for a simple implementation of Conway's Game of Life
 *
 * @author Michael T. Quinn
 */
 public class GameDriver extends Application {
    
    private AnimationTimer gameLoop;
    private final Board board = new Board();
    
    @Override
     public void start(Stage stage) {
         BorderPane root = new BorderPane();
         Scene scene = new Scene(root);

         createTopControls(root);
         
         GridPane center = new GridPane();
         root.setCenter(center);
         
         createBottomControls(root);
         
         drawBoard(board, center);
         
         gameLoop = new AnimationTimer() {
             long prevTime = 0;
             
             @Override
             public void handle(long now) {
                 if ((now - prevTime) < 500_000_000) {
                     return;
                 }
                 prevTime = now;
                 
                 drawBoard(board, center);
                 board.updateBoard();
                 
             }
         };
         
         stage.setScene(scene);
         stage.setTitle("Game of Life");
         stage.setAlwaysOnTop(true);
         stage.show();
     }
    
    /**
     * Create the flow pane and controls that populate the top area of the border pane.
     * */
     private void createTopControls(BorderPane root) {
         // Create top FlowPane and add it to the root node.
         FlowPane top = new FlowPane();
         top.setVgap(10);
         top.setHgap(50);
         top.setMinHeight(50);
         top.setAlignment(Pos.CENTER);
         root.setTop(top);
     }
    
    /**
     * Create the flow pane and buttons that populate the bottom area of the borderpane.
     */
     private void createBottomControls(BorderPane root) {
         FlowPane bottom = new FlowPane();
         bottom.setMinHeight(50);
         bottom.setVgap(10);
         bottom.setHgap(50);
         bottom.setAlignment(Pos.CENTER);
         root.setBottom(bottom);
    
         Button play = new Button("Play");
         Button pause = new Button("Pause");
         
         // Create buttons for the bottom FlowPane and add them to it. Also set the
         // functions for the buttons.
         play.setOnMouseClicked(e -> gameLoop.start());
         pause.setOnMouseClicked(e -> gameLoop.stop());
         bottom.getChildren().addAll(play, pause);
     }
    
    /**
     * Clears the board and redraws it according to the newly calculated board.
     */
     private void drawBoard(Board board, GridPane center) {
         center.getChildren().clear();
         for (int i = 0; i < board.getBoardSize(); i++) {
             for (int j = 0; j < board.getBoardSize(); j++) {
                 Cell cell = new Cell(board.getElement(i, j), i, j);
                 cell.setOnMouseClicked(e -> cell.changeState(board));
                 center.add(cell, i, j);
             }
         }
     }
     
    public static void main(String[] args) { launch(args); }
}
