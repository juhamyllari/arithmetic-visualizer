package fi.arithmeticvisualizer.gui;

/**
 * A Column represents a column vector as a suboperand.
 */
public class Column extends SubOperand {

    private int columnIndex;
    private double[] columnVector;

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

    protected int getColumnIndex() {
        return columnIndex;
    }

    protected double[] getColumnVector() {
        return columnVector;
    }

}
