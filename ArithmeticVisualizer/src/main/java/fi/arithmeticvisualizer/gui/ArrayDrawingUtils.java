package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.nodes.BooleanMask;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToArray;
import fi.arithmeticvisualizer.logic.utils.BadArrayException;
import fi.arithmeticvisualizer.logic.utils.WrongShapeException;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ArrayDrawingUtils {

    public static void drawArray(GridPane grid, double[][] array) {

        int columnWidth = 50;

        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.setStyle("-fx-background-color: azure");

        for (int i = 0; i < array[0].length; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(columnWidth));
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                Text element = new Text(Double.toString(array[i][j]));
                element.setFill(Color.BLACK);
                grid.add(element, j, i);
            }
        }
    }

    public static void drawArrayWithMasks(GridPane grid, double[][] array, BooleanMask activationMask) {
        BooleanMask showMask = new BooleanMask(array.length, array[0].length);
        showMask.setAll();
        drawArrayWithMasks(grid, array, activationMask, showMask);
    }
    
    public static void drawArrayWithMasks(GridPane grid, double[][] array, BooleanMask activationMask, BooleanMask showMask) {

        int columnWidth = 50;
        boolean[][] activation = activationMask.getMask();
        boolean[][] show = showMask.getMask();

        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.setStyle("-fx-background-color: azure");

        for (int i = 0; i < array[0].length; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(columnWidth));
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                Text element = new Text(Double.toString(array[i][j]));
                if (activation[i][j]) {
                    element.setFill(Color.RED);
                }
                element.setVisible(show[i][j]);
                grid.add(element, j, i);
            }
        }
    }

    public static void drawNodeRepresentation(BinaryNode node, GridPane leftArrayGrid, GridPane rightArrayGrid, Text symbol, double[][] leftArray, double[][] rightArray) {
        node.evaluate();
        symbol.setText(node.getSymbol());
        drawArray(leftArrayGrid, leftArray);
        drawArray(rightArrayGrid, rightArray);
    }

}
