package fi.arithmeticvisualizer.gui.suboperands;

/**
 * A Row is a SubOperand representing a row vector.
 */
public class Row extends SubOperand {

    private final int rowIndex;
    private final double[] rowVector;

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

    public int getRowIndex() {
        return rowIndex;
    }

    public double[] getRowVector() {
        return rowVector;
    }
    
}
