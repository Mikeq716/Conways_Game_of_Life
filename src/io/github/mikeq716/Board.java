package io.github.mikeq716;

/**
 * Represents the board for the game of life by means of a 2d toroidal array of integers
 * of an adjustable array size.
 *
 * @author Michael T. Quinn
 */
public class Board {
    
    private int boardSize = 25;
    
    private final int[][] currentBoard = new int[boardSize][boardSize];
    private final int[][] nextBoard = new int[boardSize][boardSize];
    
    /**
     * Constructor fills a creates and populates a board of gridSize with dead cells.
     */
    public Board() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                currentBoard[i][j] = 0;
            }
        }
    }
    
    /**
     * Returns the current board size.
     */
    public int getBoardSize() {
        return boardSize;
    }
    
    /**
     * Sets the current board size to the input size.
     */
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    
    /**
     * Set the selected cell alive.
     */
    public void setAlive(int i, int j) {
        currentBoard[i][j] = 1;
    }
    
    /**
     * Set the selected cell dead.
     */
    public void setDead(int i, int j) {
        currentBoard[i][j] = 0;
    }
    
    /**
     * Return the requested element as if the array is toroidal.
     */
    public int getElement(int i, int j) {
        i = (i + boardSize) % boardSize;
        j = (j + boardSize) % boardSize;
        return currentBoard[i][j];
    }
    
    /**
     * Update the board according to the rules of life.
     */
    public void updateBoard() {
        calculateNextBoard();
        
        // Set the current board equal to the newly calculated board.
        for (int i = 0; i < boardSize; i++) {
            System.arraycopy(nextBoard[i], 0, currentBoard[i], 0, boardSize);
        }
    }
    
    /**
     * Calculate the next board based on the current board.
     */
    private void calculateNextBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                // Check if the cell is alive.
                if (getElement(i, j) == 1) {
                    // Check if the cell has either 2 or 3 live numbers, otherwise set it
                    // to be dead.
                    if (getCount(i, j) == 2 || getCount(i, j) == 3) {
                        nextBoard[i][j] = 1;
                    } else {
                        nextBoard[i][j] = 0;
                    }
                    // Check if the cell is dead.
                } else if (getElement(i, j) == 0) {
                    // If the cell has exactly 3 live neighbors then set it to be alive.
                    if (getCount(i, j) == 3) {
                        nextBoard[i][j] = 1;
                    }
                }
            }
        }
    }
    
    /**
     * Return the number of live neighbors the current cell has.
     */
    private int getCount(int row, int col) {
        int count = 0;
        
        if (getElement(row - 1, col - 1) == 1) { count++; }
        if (getElement(row - 1, col ) == 1) { count++; }
        if (getElement(row - 1, col + 1) == 1) { count++; }
        if (getElement(row, col + 1) == 1) { count++; }
        if (getElement(row + 1, col + 1) == 1) { count++; }
        if (getElement(row + 1, col) == 1) { count++; }
        if (getElement(row + 1, col - 1) == 1) { count++; }
        if (getElement(row, col - 1) == 1) { count++; }
        
        return count;
    }
}

