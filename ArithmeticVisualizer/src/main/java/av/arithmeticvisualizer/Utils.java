package av.arithmeticvisualizer;

import java.util.function.BinaryOperator;

public class Utils {

    static BinaryOperator<TensorValue> add = (left, right) -> new TensorValue(
            addArrays(left.getValue(), right.getValue()));

    static BinaryOperator<TensorValue> multiply = (left, right) -> multiplyValues(left, right);

    public static double[][] addArrays(double[][] left, double[][] right) {
        int leftM = left.length;
        int leftN = left[0].length;
        int rightM = right.length;
        int rightN = right[0].length;

        double[][] result = new double[leftM][leftN];

        for (int i = 0; i < leftM; i++) {
            for (int j = 0; j < leftN; j++) {
                result[i][j] = left[i][j] + right[i][j];
            }
        }

        return result;

    }

    public static double[][] negateArray(double[][] array) {
        int m = array.length;
        int n = array[0].length;

        double[][] result = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = -array[i][j];
            }
        }

        return result;
    }

    protected static TensorValue multiplyValues(TensorValue left, TensorValue right) {

        double[][] result;

        if (left.isScalar()) {
            result = Utils.scalarMultiply(left.getValue()[0][0], right.getValue());
            return new TensorValue(result);
        } else if (right.isScalar()) {
            result = Utils.scalarMultiply(right.getValue()[0][0], left.getValue());
            return new TensorValue(result);
        }

        result = new double[left.getM()][right.getN()];

        for (int i = 0; i < left.getM(); i++) {
            for (int j = 0; j < right.getN(); j++) {
                result[i][j] = Utils.dotProduct(left.getValue(),
                        right.getValue(),
                        i, j);
            }
        }

        return new TensorValue(result);
    }

    protected static double[][] scalarMultiply(double scalar, double[][] array) {

        int m = array.length;
        int n = array[0].length;

        double[][] result = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = scalar * array[i][j];
            }
        }

        return result;
    }

    protected static double dotProduct(double[][] left, double[][] right, int row, int col) {

        int n = right.length;

        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            sum += left[row][i] * right[i][col];
        }

        return sum;
    }
}
