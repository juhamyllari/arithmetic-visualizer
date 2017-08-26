package fi.arithmeticvisualizer.gui;

/**
 * A Frame object represents a single frame in a visualization animation.
 * It contains a String representation of the suboperation (e.g. a single
 * dot product in the case of matrix multiplication), a row index and a
 * column index. The indices in conjunction with the FramePattern instance
 * stored in the containing FrameSequence determine which elements of each
 * array are active, and which elements of the result array are shown, in
 * each frame of the animation.
 */
public class Frame {

    private final int row;
    private final int column;
    private final String frameString;

    /**
     * Constructs a Frame.
     * 
     * @param row
     * @param column
     * @param frameString
     */
    public Frame(int row, int column, String frameString) {
        this.row = row;
        this.column = column;
        this.frameString = frameString;
    }

    /**
     * Returns a String representation of the operation pertaining to that
     * Frame.
     * 
     * @return a String representing the operation in the Frame
     */
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
