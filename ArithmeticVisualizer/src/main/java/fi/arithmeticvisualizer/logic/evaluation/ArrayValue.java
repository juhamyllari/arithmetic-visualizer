package fi.arithmeticvisualizer.logic.evaluation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Expressions in the Arithmetic Visualizer evaluate to ArrayValue objects. An
 * ArrayValue object mainly consists an array of type double[][]. In the future
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

    public ArrayValue(String inputString) throws BadArrayException {
        this.array = arrayFromString(inputString);
        this.m = array.length;
        this.n = array[0].length;
    }

    private double[][] arrayFromString(String str) throws BadArrayException {

        String[] rows = str.split("\\s*;\\s*");
        int m = rows.length;

        if (m == 0) {
            throw new BadArrayException("Array must have at least one element");
        }

        int n = stringToRow(rows[0]).length;
        double[][] array = new double[m][n];

        for (int row = 0; row < m; row++) {
            double[] rowAsDoubles = stringToRow(rows[row]);
            if (rowAsDoubles.length != n) {
                throw new BadArrayException("Expected row " + row + " to have length " + n);
            }
            array[row] = rowAsDoubles;
        }
        return array;
    }

    private static double[] stringToRow(String str) throws BadArrayException {

        List<String> strings = Arrays.asList(str.split("\\s+"));

        if (strings.isEmpty()) {
            throw new BadArrayException("Empty row");
        }

        try {
            return strings
                    .stream()
                    .mapToDouble((s) -> Double.parseDouble(s))
                    .toArray();
        } catch (NumberFormatException e) {
            throw new BadArrayException(e.getMessage());
        }
    }

    public double[][] getValue() {
        return array;
    }

    public Dimensions getDimensions() {
        return new Dimensions(m, n);
    }

    public boolean isScalar() {
        return m == 1 && n == 1;
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

    public ArrayValue scalarMultiply(double d) {
        double[][] newArray = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newArray[i][j] = d * array[i][j];
            }
        }
        return new ArrayValue(newArray);
    }

    private ArrayValue matrixMultiply(ArrayValue that) {
        double[][] newMatrix = new double[this.m][that.n];

        for (int row = 0; row < this.m; row++) {
            for (int column = 0; column < that.n; column++) {
                newMatrix[row][column] = dotVectors(this.getRow(row), that.getColumn(column));
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

    public double getElement(int row, int column) {
        return array[row][column];
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

    public ArrayValue transpose() {

        double[][] newArray = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newArray[i][j] = array[j][i];
            }
        }

        return new ArrayValue(newArray);
    }

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

        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
