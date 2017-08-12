package fi.arithmeticvisualizer.logic.utils;

import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;
import java.util.function.BinaryOperator;

public class Utils {

    private static boolean isScalar(double[][] array) {
        return array.length == 1 && array[0].length == 1;
    }

    private static String dims(double[][] array) {
        return "(" + array.length + ", " + array[0].length + ")";
    }
    
    public static double[][] addArrays(double[][] left, double[][] right) throws WrongShapeException {

        int m = left.length;
        int n = left[0].length;
        
        if (right.length != m || right[0].length != n) {
            throw new WrongShapeException("Cannot add arrays of shape " + dims(left) + " and " + dims(right));
        }

        double[][] result = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = left[i][j] + right[i][j];
            }
        }

        return result;
    }

    public static double[][] multiplyArrays(double[][] left, double[][] right) throws WrongShapeException {

        if (isScalar(left)) {
            return Utils.scalarMultiply(left[0][0], right);
        } else if (isScalar(right)) {
            return Utils.scalarMultiply(right[0][0], left);
        }

        return matrixMultiply(left, right);
    }

    public static double[][] matrixMultiply(double[][] left, double[][] right) throws WrongShapeException {

        if (left[0].length != right.length) {
            throw new WrongShapeException("Cannot multiply matrices of shape " + dims(left) + " and " + dims(right));
        }
        
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
