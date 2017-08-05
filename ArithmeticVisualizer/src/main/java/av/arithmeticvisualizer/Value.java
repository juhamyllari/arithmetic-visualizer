package av.arithmeticvisualizer;

public class Value {
    
    private double[][] value;
    private int m;
    private int n;

    public Value(double[][] value) {
        this.value = value;
        this.m = value.length;
        this.n = value[0].length;
        
        for (double[] row : value) {
            if (row.length != n) {
//                throw new WrongShapeException("Jagged arrays are not allowed");
                System.out.println("Jagged arrays are not allowed");
//                Shape checking will be moved out of the evaluation phase!
            }
        }
    }

    public double[][] getValue() {
        return value;
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
