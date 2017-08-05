package av.arithmeticvisualizer;

import java.util.function.BinaryOperator;

public class Utils {

    private static boolean isScalar(double[][] array) {
        return array.length == 1 && array[0].length == 1;
    }

    public static double[][] addArrays(double[][] left, double[][] right) {

        int m = left.length;
        int n = left[0].length;

        double[][] result = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
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

    protected static double[][] multiplyArrays(double[][] left, double[][] right) {

        if (isScalar(left)) {
            return Utils.scalarMultiply(left[0][0], right);
        } else if (isScalar(right)) {
            return Utils.scalarMultiply(right[0][0], left);
        }

        return matrixMultiply(left, right);
    }

    protected static double[][] matrixMultiply(double[][] left, double[][] right) {

        int m = left.length;
        int p = right[0].length;

        double[][] result = new double[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                result[i][j] = Utils.dotProduct(left, right, i, j);
            }
        }

        return result;
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
