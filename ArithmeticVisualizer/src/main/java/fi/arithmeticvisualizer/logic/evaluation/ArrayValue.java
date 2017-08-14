package fi.arithmeticvisualizer.logic.evaluation;

public class ArrayValue {
    
    private double[][] array;
    private int m;
    private int n;

    public ArrayValue(double[][] value) {
        this.array = value;
        this.m = value.length;
        this.n = value[0].length;
    }

    public double[][] getValue() {
        return array;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }
    
    public boolean isScalar() {
        return m == 1 && n == 1;
    }
    
    public String dimsString() {
        return "(" + m + ", " + n + ")";
    }
    
}
