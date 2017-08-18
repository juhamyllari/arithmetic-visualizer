package fi.arithmeticvisualizer.logic.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

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
    
    public static String arrayToInputString(double[][] array) {
        
        StringBuilder sb = new StringBuilder();
        
        for (double[] row : array) {
            sb.append(DoubleStream.of(row).boxed()
                    .map(d -> ((Double) d).toString())
                    .collect(Collectors.joining(" ")));
            sb.append("; ");
        }
        
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        
        return sb.toString();
    }
    
    public static double[][] transposeArray(double[][] array) {
        
        int m = array.length;
        int n = array[0].length;
        
        double[][] newArray = new double[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newArray[i][j] = array[j][i];
            }
        }
        
        return newArray;
    }

}
