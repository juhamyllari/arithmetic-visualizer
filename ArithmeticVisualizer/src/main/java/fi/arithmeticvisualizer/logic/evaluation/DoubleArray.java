package fi.arithmeticvisualizer.logic.evaluation;

import static fi.arithmeticvisualizer.logic.evaluation.RealArray.dotVectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Expressions in the Arithmetic Visualizer evaluate to DoubleArray objects.
 */
public class DoubleArray implements RealArray {

    private final double[][] array;
    private final int m;
    private final int n;

    /**
     * Constructs a DoubleArray with the specified value.
     *
     * @param value the value represented
     */
    public DoubleArray(double[][] value) {
        this.array = value;
        this.m = value.length;
        this.n = value[0].length;
    }

    /**
     * Constructs a DoubleArray from a String. Separate columns with whitespace
     * and rows with a semicolon. Use "." as the decimal point.
     *
     * @param inputString a String defining the value
     * @throws BadArrayException indicating an invalid input string
     */
    public DoubleArray(String inputString) throws BadArrayException {
        this(RealArray.stringToArray(inputString));
    }

    @Override
    public List<Double> getValues() {
        return Arrays.stream(array)
                .flatMapToDouble(Arrays::stream)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Dimensions getDimensions() {
        return new Dimensions(m, n);
    }

    @Override
    public boolean isScalar() {
        return m == 1 && n == 1;
    }

    @Override
    public RealArray multiply(RealArray that) {
        if (this.isScalar()) {
            return that.scalarMultiply(this.getElement(0, 0));
        } else if (that.isScalar()) {
            return this.scalarMultiply(that.getElement(0, 0));
        } else {
            return this.matrixMultiply(that);
        }
    }

    @Override
    public DoubleArray scalarMultiply(double d) {
        double[][] newArray = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newArray[i][j] = d * array[i][j];
            }
        }
        return new DoubleArray(newArray);
    }

    @Override
    public DoubleArray matrixMultiply(RealArray that) {
        double[][] newMatrix = new double[this.m][that.getDimensions().getN()];
        for (int row = 0; row < this.m; row++) {
            for (int column = 0; column < that.getDimensions().getN(); column++) {
                newMatrix[row][column] = dotVectors(this.getRow(row), that.getColumn(column));
            }
        }
        return new DoubleArray(newMatrix);
    }

    @Override
    public double[] getRow(int row) {
        return array[row];
    }

    @Override
    public double[] getColumn(int column) {
        return Arrays.stream(array).mapToDouble(row -> row[column]).toArray();
    }

    @Override
    public double getElement(int row, int column) {
        return array[row][column];
    }

    @Override
    public DoubleArray add(RealArray that) {
        double[][] result = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = this.getElement(i, j) + that.getElement(i, j);
            }
        }
        return new DoubleArray(result);
    }

    @Override
    public DoubleArray subtract(RealArray that) {
        return this.add(that.scalarMultiply(-1));
    }

    /**
     * Returns the transpose of this DoubleArray.
     *
     * @return the transpose of this DoubleArray
     */
    public DoubleArray transpose() {
        double[][] newArray = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newArray[i][j] = array[j][i];
            }
        }
        return new DoubleArray(newArray);
    }

    @Override
    public String toInputString() {
        StringBuilder sb = new StringBuilder();
        for (double[] row : array) {
            String rowAsString = Arrays.stream(row)
                    .boxed()
                    .map(d -> d.toString())
                    .collect(Collectors.joining(" "));
            sb.append(rowAsString);
            sb.append("; ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
