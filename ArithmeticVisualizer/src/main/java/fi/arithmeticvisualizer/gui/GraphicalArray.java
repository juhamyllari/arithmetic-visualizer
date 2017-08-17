package fi.arithmeticvisualizer.gui;

import java.util.function.BiPredicate;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GraphicalArray {

    static final int COLUMNWIDTH = 50;
    static final String ARRAYSTYLE = "-fx-background-color: azure";

    private GridPane grid;
    private int m;
    private int n;
    private ArrayElement[][] elements;

    public GraphicalArray(double[][] array, GridPane grid, boolean showAll) {
        this.grid = grid;
        this.m = array.length;
        this.n = array[0].length;
        this.elements = initializeElements(array, grid, showAll);

        gridSetup(grid, n);
        initializeElements(array, grid, showAll);
    }

    private ArrayElement[][] initializeElements(double[][] array, GridPane grid, boolean showAll) {
        
        int m = array.length;
        int n = array[0].length;
        int elementId = 0;
        ArrayElement[][] elementArray = new ArrayElement[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ArrayElement element = new ArrayElement(elementId, array[i][j], grid, showAll);
                elementArray[i][j] = element;
                grid.add(element.getInitialText(), j, i);
                elementId++;
            }
        }
        return elementArray;
    }

    private void gridSetup(GridPane grid, int columns) {
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();

        for (int i = 0; i < columns; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(COLUMNWIDTH));
        }
    }

    private void setActivation(BiPredicate<Integer, Integer> predicate) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                elements[i][j].setActive(predicate.test(i, j));
            }
        }
    }

    public void activateRow(int row) {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> (i == row);
        setActivation(predicate);
    }

    public void activateColumn(int column) {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> (j == column);
        setActivation(predicate);
    }

    public void activateElement(int row, int column) {
        elements[row][column].setActive(true);
    }

    public void clearActivation() {
        BiPredicate<Integer, Integer> predicate = (Integer i, Integer j) -> false;
        setActivation(predicate);
    }

    public void setShow(int row, int column) {
        elements[row][column].setShow(true);
    }

    public void setShow(boolean setOn) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                elements[i][j].setShow(setOn);
            }
        }
    }

    public void setElement(int row, int column, double value) {
        elements[row][column].setValue(value);
    }

    public double getElementValue(int row, int column) {
        return elements[row][column].getValue();
    }

    public ArrayElement getElement(int row, int column) {
        return elements[row][column];
    }

    public double[] getRow(int row) {
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = elements[row][i].getValue();
        }
        return result;
    }

    public double[] getColumn(int column) {
        double[] result = new double[m];
        for (int i = 0; i < m; i++) {
            result[i] = elements[i][column].getValue();
        }
        return result;
    }

    public double[][] getArray() {
        double[][] array = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = elements[i][j].getValue();
            }
        }
        return array;
    }

    public GridPane getGrid() {
        return grid;
    }
    
}
