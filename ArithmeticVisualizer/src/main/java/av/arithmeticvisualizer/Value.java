package av.arithmeticvisualizer;

public class Value {
    
    private double[][] valueArray;
    private int m;
    private int n;

    public Value(double[][] value) {
        this.valueArray = value;
        this.m = value.length;
        this.n = value[0].length;
    }

    public double[][] getValue() {
        return valueArray;
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
