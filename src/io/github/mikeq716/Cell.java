package io.github.mikeq716;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents an individual cell in the game of life.
 *
 * @author Michael T. Quinn
 */
public class Cell extends Rectangle {
    
    private int cellSize = 25;
    
    private final int row;
    private final int col;
    private int state;
    
    /**
     * Constructor initializes the cell and sets its color.
     */
    public Cell(int state, int row, int col) {
        super();
        this.setWidth(cellSize);
        this.setHeight(cellSize);
        this.state = state;
        this.row = row;
        this.col = col;
        setFill(getColor());
        setStroke(Color.LIGHTGRAY);
    }
    
    /**
     * Set the cell size to the given parameter.
     * */
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
    
    /**
     * Set the color depending on the state of the cell.
     */
    private Color getColor() {
        if (state == 1) { return Color.BLACK; }
        else { return Color.WHITE; }
    }
    
    /**
     * When clicked on, set the selected cell to be alive if dead or vice versa.
     */
    public void changeState(Board board) {
        if (board.getElement(row, col) == 1) {
            this.state = 0;
            setFill(Color.WHITE);
            board.setDead(row, col);
        }
        else if (board.getElement(row, col) == 0) {
            this.state = 1;
            setFill(Color.BLACK);
            board.setAlive(row, col);
        }
    }
}
