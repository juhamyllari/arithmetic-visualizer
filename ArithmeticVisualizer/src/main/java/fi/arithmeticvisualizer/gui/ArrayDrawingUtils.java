package fi.arithmeticvisualizer.gui;

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
}
