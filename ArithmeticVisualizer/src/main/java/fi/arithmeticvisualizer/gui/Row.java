package fi.arithmeticvisualizer.gui;

public class Row extends SubOperand {

    private int rowIndex;
    private double[] rowVector;

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
