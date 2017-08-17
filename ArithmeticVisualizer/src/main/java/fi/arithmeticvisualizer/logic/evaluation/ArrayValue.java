package fi.arithmeticvisualizer.logic.evaluation;

import fi.arithmeticvisualizer.logic.utils.ArrayIOUtils;
import fi.arithmeticvisualizer.logic.utils.Dims;
import fi.arithmeticvisualizer.logic.utils.WrongShapeException;

public class ArrayValue {
    
    private double[][] array;
    private int m;
    private int n;

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

    public Dims getDims() {
        return new Dims(m, n);
    }
    
    public boolean isScalar() {
        return m == 1 && n == 1;
    }
    
    public String dimsString() {
        return "(" + m + ", " + n + ")";
    }
    
}
