package fi.arithmeticvisualizer.logic.evaluation;

import fi.arithmeticvisualizer.logic.utils.Dimensions;

/**
 * Expressions in the Arithmetic Visualizer evaluate to ArrayValue objects.
 * An ArrayValue object is essentially an array of type double[][].
 * In the future the class may accommodate arrays of more general scalars
 * such as complex numbers.
 */
public class ArrayValue {
    
    private final double[][] array;
    private final int m;
    private final int n;

    public ArrayValue(double[][] value) {
        this.array = value;
        this.m = value.length;
        this.n = value[0].length;
    }

    public ArrayValue(double dbl) {
        this.array = new double[1][1];
        this.array[0][0] = dbl;
        this.m = 1;
        this.n = 1;
    }

    public double[][] getValue() {
        return array;
    }

    public Dimensions getDims() {
        return new Dimensions(m, n);
    }
    
    public boolean isScalar() {
        return m == 1 && n == 1;
    }
    
    public String dimsString() {
        return "(" + m + ", " + n + ")";
    }
    
}
