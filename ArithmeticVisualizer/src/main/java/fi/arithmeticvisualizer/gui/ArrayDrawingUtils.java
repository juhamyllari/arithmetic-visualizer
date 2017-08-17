package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.nodes.BooleanMask;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToArray;
import fi.arithmeticvisualizer.logic.utils.BadArrayException;
import fi.arithmeticvisualizer.logic.utils.WrongShapeException;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ArrayDrawingUtils {

    public static final String ARRAYBACKGROUNDSTYLE = "-fx-background-color: beige";
    public static final String ELEMENTSTRINGFORMAT = "%.2f";
    public static final int ARRAYCOLUMNWIDTH = 60;
    public static final int ARRAYROWHEIGHT = 30;
    
    public static final Color ACTIVATIONRECTANGLECOLOUR = Color.CRIMSON;
    public static final int ACTIVATIONRECTANGLEWIDTH = 60;
    public static final int ACTIVATIONRECTANGLEHEIGHT = 30;
    public static final double ACTIVATIONRECTANGLEOPACITY = .3;

    public static void drawArray(GridPane grid, double[][] array) {

        BooleanMask activation = new BooleanMask(array.length, array[0].length);
        drawArrayWithMasks(grid, array, activation);

    }

    public static void drawArrayWithMasks(GridPane grid, double[][] array, BooleanMask activationMask) {

        BooleanMask showMask = new BooleanMask(array.length, array[0].length);
        showMask.setAll();
        drawArrayWithMasks(grid, array, activationMask, showMask);

    }

    public static void drawArrayWithMasks(GridPane grid, double[][] array, BooleanMask activationMask, BooleanMask showMask) {

        boolean[][] activation = activationMask.getMask();
        boolean[][] show = showMask.getMask();

        setUpGrid(array, grid);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                double value = array[i][j];
                Text element = new Text(String.format(ELEMENTSTRINGFORMAT, value));
                element.setVisible(show[i][j]);
                StackPane stack = new StackPane(element);
                if (activation[i][j]) {
                    Rectangle rect = new Rectangle(ACTIVATIONRECTANGLEWIDTH, ACTIVATIONRECTANGLEHEIGHT);
                    rect.fillProperty().set(ACTIVATIONRECTANGLECOLOUR);
                    rect.opacityProperty().set(ACTIVATIONRECTANGLEOPACITY);
                    stack.getChildren().add(rect);
                }
                grid.add(stack, j, i);
            }
        }
    }

    private static void setUpGrid(double[][] array, GridPane grid) {

        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.setStyle(ARRAYBACKGROUNDSTYLE);

        for (int i = 0; i < array.length; i++) {
            grid.getRowConstraints().add(new RowConstraints(ARRAYROWHEIGHT));
        }

        for (int i = 0; i < array[0].length; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(ARRAYCOLUMNWIDTH));
        }
    }

    public static void drawNodeRepresentation(BinaryNode node, GridPane leftArrayGrid, GridPane rightArrayGrid, Text symbol, double[][] leftArray, double[][] rightArray) {

        node.evaluate();
        symbol.setText(node.getSymbol());
        drawArray(leftArrayGrid, leftArray);
        drawArray(rightArrayGrid, rightArray);

    }

}
