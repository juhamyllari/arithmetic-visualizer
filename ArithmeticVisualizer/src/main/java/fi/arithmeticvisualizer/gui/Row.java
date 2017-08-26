package fi.arithmeticvisualizer.gui;

/**
 * A Row is a SubOperand representing a row vector.
 */
public class Row extends SubOperand {

    private int rowIndex;
    private double[] rowVector;

    /**
     * Constructs a Row from a 1D array and its row index.
     * 
     * @param rowIndex the row index
     * @param rowVector the vector
     */
    public Row(int rowIndex, double[] rowVector) {
        this.rowIndex = rowIndex;
        this.rowVector = rowVector;
    }

    protected int getRowIndex() {
        return rowIndex;
    }

    protected double[] getRowVector() {
        return rowVector;
    }
    
}
