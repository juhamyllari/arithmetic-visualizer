package fi.arithmeticvisualizer.logic.evaluation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A RealArray is a 2D real valued array.
 */
public interface RealArray {

    /**
     * Returns the row and column dimensions of the array.
     *
     * @return the dimensions of the array
     */
    public Dimensions getDimensions();

    /**
     * Returns {@code true} if and only if the RealArray has dimensions (1, 1).
     *
     * @return {@code true} if and only if the instance is a scalar
     */
    public boolean isScalar();

    /**
     * Returns the specified element.
     *
     * @param row the index of the row
     * @param column the index of the column
     * @return the specified element
     */
    public double getElement(int row, int column);

    /**
     * Returns the specified row.
     *
     * @param row the index of the row
     * @return the specified row
     */
    public double[] getRow(int row);

    /**
     * Returns the specified column.
     *
     * @param column the index of the column
     * @return the specified column
     */
    public double[] getColumn(int column);

    /**
     * Returns a list of the values of the elements of the array
     * in row-major order.
     *
     * @return the element values as a list
     */
    public List<Double> getValues();

    /**
     * Returns the sum of the two RealArrays.
     *
     * @param that the RealArray to add
     * @return a RealArray whose value is (this + that)
     */
    public RealArray add(RealArray that);

    /**
     * Returns the difference of this and the specified RealArray.
     *
     * @param that RealArray to subtract from this
     * @return a RealArray whose value is (this - that)
     */
    public RealArray subtract(RealArray that);

    /**
     * Returns the product of this RealArray with the specified RealArray.
     *
     * @param that the RealArray to be left-multiplied
     * @return a RealArray whose value is (this * that)
     */
    public RealArray multiply(RealArray that);

    /**
     * Returns the product of this RealArray with the specified scalar.
     *
     * @param d the multiplier
     * @return a RealArray whose value is (d * this)
     */
    public RealArray scalarMultiply(double d);

    /**
     * Returns the product of this RealArray with the specified RealArray where
     * both operands are matrix valued.
     *
     * @param that the right-side operand
     * @return a new RealArray whose value is (this * that)
     */
    public DoubleArray matrixMultiply(RealArray that);

    /**
     * Returns a String that can be used to produce an equivalent DoubleArray
     * using the constructor that takes a String argument.
     *
     * @return a String representation of the DoubleArray that is accepted by
     * the constructor.
     */
    public String toInputString();

    /**
     * Returns a double[][] with the values and dimensions specified in the
     * input String. The input String may consist of real numbers (use "." as
     * the decimal point), whitespace as column separators and semicolons as row
     * separators. Jagged arrays are disallowed: all rows must have the same
     * number of elements.
     *
     * @param string a String representation of the array
     * @return the corresponding array
     * @throws BadArrayException if the input is invalid
     */
    public static double[][] arrayFromString(String string) throws BadArrayException {
        String[] rows = string.split("\\s*;\\s*");
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

    /**
     * Creates a vector of doubles from a String representation of an array row.
     * Columns must be separated by whitespace.
     *
     * @param string a String representation of an array row
     * @return a vector of doubles
     * @throws BadArrayException if the input is invalid
     */
    public static double[] stringToRow(String string) throws BadArrayException {
        List<String> strings = Arrays.asList(string.split("\\s+"));
        try {
            return strings
                    .stream()
                    .mapToDouble((s) -> Double.parseDouble(s))
                    .toArray();
        } catch (NumberFormatException e) {
            throw new BadArrayException(e.getMessage());
        }
    }

    /**
     * Returns the dot product of two vectors.
     *
     * @param leftVector the left vector operand
     * @param rightVector the right vector operand
     * @return the dot product of the vectors
     */
    public static double dotVectors(double[] leftVector, double[] rightVector) {
        return IntStream
                .range(0, leftVector.length)
                .mapToDouble(i -> leftVector[i] * rightVector[i])
                .sum();
    }
}
