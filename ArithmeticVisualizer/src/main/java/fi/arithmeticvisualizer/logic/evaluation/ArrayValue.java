package fi.arithmeticvisualizer.logic.evaluation;

import fi.arithmeticvisualizer.logic.utils.Dimensions;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Expressions in the Arithmetic Visualizer evaluate to ArrayValue objects. An
 * ArrayValue object is essentially an array of type double[][]. In the future
 * the class may accommodate arrays of more general scalars such as complex
 * numbers.
 */
public class ArrayValue {

    private double[][] array;
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

    private ArrayValue(Dimensions dims) {
        this.m = dims.getM();
        this.n = dims.getN();
        this.array = new double[m][n];
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

    public ArrayValue multiply(ArrayValue that) {

        if (this.isScalar()) {
            return that.scalarMultiply(this.getValue()[0][0]);
        } else if (that.isScalar()) {
            return this.scalarMultiply(that.getValue()[0][0]);
        } else {
            return this.matrixMultiply(that);
        }
    }

    private ArrayValue scalarMultiply(double d) {

        double[][] newArray = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newArray[m][n] = d * array[m][n];
            }
        }

        return new ArrayValue(newArray);
    }

    private double[][] cloneArray() {
        return Arrays.stream(array).map(double[]::clone).toArray(double[][]::new);
    }

    private ArrayValue matrixMultiply(ArrayValue that) {

        double[][] newMatrix = new double[this.m][that.n];

        for (int row = 0; row < this.m; row++) {
            for (int column = 0; column < that.n; column++) {
                double[] leftRow = this.getRow(row);
                double[] rightColumn = this.getColumn(column);
                newMatrix[row][column] = dotVectors(leftRow, rightColumn);
            }
        }

        return new ArrayValue(newMatrix);
    }

    public double[] getRow(int row) {
        return array[row];
    }

    public double[] getColumn(int column) {
        return Arrays.stream(array).mapToDouble(row -> row[column]).toArray();
    }

    public static double dotVectors(double[] leftVector, double[] rightVector) {
        return IntStream
                .range(0, leftVector.length)
                .mapToDouble(i -> leftVector[i] * rightVector[i])
                .sum();
    }

    public ArrayValue addArray(ArrayValue that) {

        double[][] result = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = this.array[i][j] + that.array[i][j];
            }
        }

        return new ArrayValue(result);
    }
    
    public ArrayValue subtractArray(ArrayValue that) {
        return this.addArray(that.scalarMultiply(-1));
    }

}
