package fi.arithmeticvisualizer.gui;

/**
 * A Frame object represents a single frame in a visualization animation.
 * It contains a BooleanMask to indicate which elements of the result array
 * are to be displayed, and a String representation of the suboperation (e.g.
 * a single dot product in the case of matrix multiplication).
 */
public class Frame {

    private final int row;
    private final int column;
    private final String frameString;

    public Frame(int row, int column, String frameString) {
        this.row = row;
        this.column = column;
        this.frameString = frameString;
    }

    public String getFrameString() {
        return frameString;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    
}
