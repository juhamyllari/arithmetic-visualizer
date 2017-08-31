package fi.arithmeticvisualizer.logic.suboperands;

/**
 * A Column is a SubOperand representing a column vector.
 */
public class Column extends SubOperand {

    private final int columnIndex;
    private final double[] columnVector;

    /**
     * Constructs a Row from a 1D array and its row index.
     *
     * @param columnIndex the index of the column
     * @param columnVector the vector
     */
    public Column(int columnIndex, double[] columnVector) {
        this.columnIndex = columnIndex;
        this.columnVector = columnVector;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public double[] getColumnVector() {
        return columnVector;
    }

}
