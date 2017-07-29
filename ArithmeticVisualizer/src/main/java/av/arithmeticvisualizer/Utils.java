package av.arithmeticvisualizer;

public class Utils {

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

}
