package fi.arithmeticvisualizer.logic.utils;

import java.util.Arrays;
import java.util.List;

public class ArrayIOUtils {

    public static double[][] stringToArray(String str) throws BadArrayException {

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

    public static double[] stringToRow(String str) throws BadArrayException {

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

}
