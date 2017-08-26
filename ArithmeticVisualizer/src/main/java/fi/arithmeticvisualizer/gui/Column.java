package fi.arithmeticvisualizer.gui;

public class Column extends SubOperand {
    
    private int columnIndex;
    private double[] columnVector;

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
