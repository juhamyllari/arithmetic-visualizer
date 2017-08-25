package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.gui.MaskState.Pattern;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.ALL;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.COLUMN;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.ELEMENT;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.ROW;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.function.BiPredicate;

/**
 * A BooleanMask represents a 2D boolean array. Methods are provided for setting
 * elements to true individually, by row, by column or all at once. It is also
 * possible to set all elements to false at once.
 */
public class BooleanMask {

    private final boolean[][] mask;
    private final int m;
    private final int n;

    public BooleanMask(int rows, int columns) {
        this.mask = new boolean[rows][columns];
        this.m = rows;
        this.n = columns;
    }

    public BooleanMask(Dimensions dims) {
        this(dims.getM(), dims.getN());
    }

    public void setByPattern(Pattern pattern, int row, int column) {
        
        switch (pattern) {
            case ROW:
                this.setRow(row);
                break;
            case COLUMN:
                this.setColumn(column);
                break;
            case ELEMENT:
                this.setElement(row, column);
                break;
            case ALL:
                this.setAll();
                break;
            case UPTOBYROW:
                outerLoop:
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        mask[i][j] = true;
                        if (i == row && j == column) {
                            break outerLoop;
                        }
                    }
                }
                break;
        }
    }

    private void setActivation(BiPredicate<Integer, Integer> predicate) {
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
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> (i == row && j == column);
        setActivation(predicate);
    }

    public void setAdditionalElement(int row, int column) {
        mask[row][column] = true;
    }

    public void setAll() {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> true;
        setActivation(predicate);
    }

    public void clearAll() {
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
                    clone.setAdditionalElement(i, j);
                }
            }
        }

        return clone;
    }

}
