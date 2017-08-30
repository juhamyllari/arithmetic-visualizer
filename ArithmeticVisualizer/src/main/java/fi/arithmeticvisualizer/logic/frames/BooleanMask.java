package fi.arithmeticvisualizer.logic.frames;

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

    /**
     * Constructs a BooleanMask with the specified dimensions.
     *
     * @param rows the number of rows
     * @param columns the number of columns
     */
    public BooleanMask(int rows, int columns) {
        this.mask = new boolean[rows][columns];
        this.m = rows;
        this.n = columns;
    }

    /**
     * Constructs a BooleanMask with the dimensions specified in the
     * {@code Dimensions} instance.
     *
     * @param dims the dimensions of the mask to be created
     */
    public BooleanMask(Dimensions dims) {
        this(dims.getM(), dims.getN());
    }

    private void setActivation(BiPredicate<Integer, Integer> predicate) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mask[i][j] = predicate.test(i, j);
            }
        }
    }

    /**
     * Sets the specified row to {@code true}. All other elements are set to
     * {@code false}.
     *
     * @param row the index of the row to be set to {@code true}
     */
    public void setRow(int row) {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> (i == row);
        setActivation(predicate);
    }

    /**
     * Sets the specified column to {@code true}. All other elements are set to
     * {@code false}.
     *
     * @param column the index of the column to be set to {@code true}
     */
    public void setColumn(int column) {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> (j == column);
        setActivation(predicate);
    }

    /**
     * Sets the specified element to {@code true}. All other elements are set to
     * {@code false}.
     *
     * @param row the index of the row to be set to {@code true}
     * @param column the index of the column to be set to {@code true}
     */
    public void setElement(int row, int column) {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> (i == row && j == column);
        setActivation(predicate);
    }

    /**
     * Traverses the mask in row-major order and sets all values to true up to
     * and including the specified element.
     *
     * @param row the row index of the element
     * @param column the column index of the element
     */
    public void setUpToByRow(int row, int column) {
        outerLoop:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mask[i][j] = true;
                if (i == row && j == column) {
                    break outerLoop;
                }
            }
        }
    }

    /**
     * Traverses the mask in column-major order and sets all values to true
     * up to and including the specified element.
     *
     * @param row the row index of the element
     * @param column the column index of the element
     */
    public void setUpToByColumn(int row, int column) {
        outerLoop:
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                mask[i][j] = true;
                if (i == row && i == column) {
                    break outerLoop;
                }
            }
        }
    }

    /**
     * Sets the specified element to {@code true}. All other elements retain
     * their truth value.
     *
     * @param row the row index of the element to be set to {@code true}
     * @param column the column index of the element to be set to {@code true}
     */
    public void setAdditionalElement(int row, int column) {
        mask[row][column] = true;
    }

    /**
     * Sets all elements to {@code true}.
     */
    public void setAll() {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> true;
        setActivation(predicate);
    }

    /**
     * Sets all elements to {@code false}.
     */
    public void clearAll() {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> false;
        setActivation(predicate);
    }

    /**
     * Returns the boolean 2D array representing the BooleanMask.
     *
     * @return the mask
     */
    public boolean[][] getMask() {
        return mask;
    }

    /**
     * Returns a copy of the BooleanMask.
     *
     * @return a copy of the BooleanMask
     */
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
