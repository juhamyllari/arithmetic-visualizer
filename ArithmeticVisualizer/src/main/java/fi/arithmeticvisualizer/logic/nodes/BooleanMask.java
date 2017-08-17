package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.utils.Dims;
import java.util.function.BiPredicate;

public class BooleanMask {

    private final boolean[][] mask;
    private final int m;
    private final int n;

    public BooleanMask(int rows, int columns) {
        this.mask = new boolean[rows][columns];
        this.m = rows;
        this.n = columns;
    }

    public BooleanMask(Dims dims, String pattern, int row, int column) {
        this(dims.getM(), dims.getN());
        switch (pattern) {
            case "row":
                this.setRow(row);
                break;
            case "column":
                this.setColumn(column);
                break;
            case "element":
                this.setElement(row, column);
                break;
            case "all":
                this.setAll();
        }
    }

    public void setActivation(BiPredicate<Integer, Integer> predicate) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mask[i][j] = predicate.test(i, j);
            }
        }
    }

    public void setRow(int row) {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> (i == row);
        setActivation(predicate);
    }

    public void setColumn(int column) {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> (j == column);
        setActivation(predicate);
    }

    public void setElement(int row, int column) {
        mask[row][column] = true;
    }

    public void setAll() {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> true;
        setActivation(predicate);
    }

    public void clearActivation() {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> false;
        setActivation(predicate);
    }

    public boolean[][] getMask() {
        return mask;
    }

    public BooleanMask clone() {
        BooleanMask clone = new BooleanMask(m, n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.getMask()[i][j]) {
                    clone.setElement(i, j);
                }
            }
        }

        return clone;
    }

}
