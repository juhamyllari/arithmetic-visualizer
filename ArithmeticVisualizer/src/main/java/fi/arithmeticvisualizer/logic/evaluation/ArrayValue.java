package fi.arithmeticvisualizer.logic.evaluation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Expressions in the Arithmetic Visualizer evaluate to ArrayValue objects. An
 * ArrayValue object consists mainly of an array of type double[][]. In the
 * future the class may accommodate arrays of more general scalars such as
 * complex numbers.
 */
public class ArrayValue {

    private double[][] array;
    private final int m;
    private final int n;

    /**
     * Constructs an ArrayValue with the specified value.
     *
     * @param value
     */
    public ArrayValue(double[][] value) {
        this.array = value;
        this.m = value.length;
        this.n = value[0].length;
    }

    /**
     * Constructs an ArrayValue with the specified scalar value.
     *
     * @param scalar
     */
    public ArrayValue(double scalar) {
        this.array = new double[1][1];
        this.array[0][0] = scalar;
        this.m = 1;
        this.n = 1;
    }

    /**
     * Constructs an ArrayValue from a String. Separate columns with whitespace
     * and rows with a semicolon.
     *
     * @param inputString
     * @throws BadArrayException
     */
    public ArrayValue(String inputString) throws BadArrayException {
        this.array = arrayFromString(inputString);
        this.m = array.length;
        this.n = array[0].length;
    }

    private double[][] arrayFromString(String str) throws BadArrayException {
        String[] rows = str.split("\\s*;\\s*");
        int m = rows.length;
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

    /**
     * Returns {@code true} if the ArrayValue has dimensions (1, 1) and
     * {@code true} otherwise.
     *
     * @return {@code true} if and only if the instance is a scalar
     */
    public boolean isScalar() {
        return m == 1 && n == 1;
    }

    /**
     * Returns the product of this ArrayValue with the specified ArrayValue.
     *
     * @param that the ArrayValue to be multiplied
     * @return the product of the two ArrayValues
     */
    public ArrayValue multiply(ArrayValue that) {
        if (this.isScalar()) {
            return that.scalarMultiply(this.getValue()[0][0]);
        } else if (that.isScalar()) {
            return this.scalarMultiply(that.getValue()[0][0]);
        } else {
            return this.matrixMultiply(that);
        }
    }

    /**
     * Returns the product of this ArrayValue with the specified scalar valued
     * ArrayValue as a new ArrayValue.
     *
     * @param d the ArrayValue to be multiplied
     * @return the product of the two ArrayValues
     */
    public ArrayValue scalarMultiply(double d) {
        double[][] newArray = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newArray[i][j] = d * array[i][j];
            }
        }
        return new ArrayValue(newArray);
    }

    /**
     * Returns as a new ArrayValue the product of this ArrayValue with the
     * specified ArrayValue where both operands are matrix valued.
     *
     * @param that the ArrayValue to be multiplied
     * @return the product of the two ArrayValues
     */
    private ArrayValue matrixMultiply(ArrayValue that) {
        double[][] newMatrix = new double[this.m][that.n];

        for (int row = 0; row < this.m; row++) {
            for (int column = 0; column < that.n; column++) {
                newMatrix[row][column] = dotVectors(this.getRow(row), that.getColumn(column));
            }
        }
        return new ArrayValue(newMatrix);
    }

    /**
     * Returns the specified row.
     *
     * @param row the index of the row
     * @return the specified row
     */
    public double[] getRow(int row) {
        return array[row];
    }

    /**
     * Returns the specified column.
     *
     * @param column the index of the column
     * @return the specified column
     */
    public double[] getColumn(int column) {
        return Arrays.stream(array).mapToDouble(row -> row[column]).toArray();
    }

    /**
     * Returns the specified element.
     *
     * @param row the index of the row
     * @param column the index of the column
     * @return the specified element
     */
    public double getElement(int row, int column) {
        return array[row][column];
    }

    /**
     * Returns the dot product of two vectors.
     *
     * @param leftVector
     * @param rightVector
     * @return the dot product of the vectors
     */
    public static double dotVectors(double[] leftVector, double[] rightVector) {
        return IntStream
                .range(0, leftVector.length)
                .mapToDouble(i -> leftVector[i] * rightVector[i])
                .sum();
    }

    /**
     * Returns the sum of the two ArrayValues.
     *
     * @param that ArrayValue to add
     * @return the sum of the two values
     */
    public ArrayValue add(ArrayValue that) {
        double[][] result = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = this.array[i][j] + that.array[i][j];
            }
        }
        return new ArrayValue(result);
    }

    /**
     * Returns the difference of this and the specified ArrayValue.
     *
     * @param that ArrayValue to subtract
     * @return the difference of the two values
     */
    public ArrayValue subtract(ArrayValue that) {
        return this.add(that.scalarMultiply(-1));
    }

    /**
     * Returns the transpose of this ArrayValue.
     * 
     * @return the transpose of this ArrayValue
     */
    public ArrayValue transpose() {
        double[][] newArray = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newArray[i][j] = array[j][i];
            }
        }
        return new ArrayValue(newArray);
    }

    /**
     * Returns a String that can be used to produce an equivalent
     * ArrayValue using the constructor that takes a String argument.
     * 
     * @return a String representation of the ArrayValue that is
     * accepted by the constructor.
     */
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
