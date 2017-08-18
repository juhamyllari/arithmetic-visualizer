package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.nodes.BooleanMask;
import java.util.Arrays;
import java.util.Collections;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ArrayDrawingUtils {

    public static final String ARRAYBACKGROUNDSTYLE = "-fx-background-color: beige";
    public static final String ELEMENTSTRINGFORMAT = "%5.1f";
    public static final int ELEMENTFONTSIZE = 16;
    public static final int DEFAULTCOLUMNWIDTH = 65;
    public static final int ROWHEIGHT = 30;
    
    public static final Color ACTIVATIONRECTANGLECOLOUR = Color.CRIMSON;
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
        int activationRectangleWidth = getColumnWidth(array);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                double value = array[i][j];
                Text element = new Text(String.format(ELEMENTSTRINGFORMAT, value));
                element.fontProperty().set(new Font(ELEMENTFONTSIZE));
                element.setVisible(show[i][j]);
                StackPane stack = new StackPane(element);
                if (activation[i][j]) {
                    Rectangle rect = new Rectangle(activationRectangleWidth, ACTIVATIONRECTANGLEHEIGHT);
                    rect.fillProperty().set(ACTIVATIONRECTANGLECOLOUR);
                    rect.opacityProperty().set(ACTIVATIONRECTANGLEOPACITY);
                    stack.getChildren().add(rect);
                }
                grid.add(stack, j, i);
            }
        }
    }

    private static void setUpGrid(double[][] array, GridPane grid) {

        int columnWidth = Math.max(getColumnWidth(array), DEFAULTCOLUMNWIDTH);
        
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.setStyle(ARRAYBACKGROUNDSTYLE);

        for (int i = 0; i < array.length; i++) {
            grid.getRowConstraints().add(new RowConstraints(ROWHEIGHT));
        }

        for (int i = 0; i < array[0].length; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(columnWidth));
        }
    }

    private static int getColumnWidth(double[][] array) {
        
        int maxLength = getMaxStringLength(array);
        String testString = String.join("", Collections.nCopies(maxLength, "0"));
        
        Text testText = new Text(testString);
        testText.fontProperty().set(new Font(ELEMENTFONTSIZE));
        
        int empiricalWidth = (int) Math.ceil(testText.getLayoutBounds().getWidth());
        
        return Math.max(DEFAULTCOLUMNWIDTH, empiricalWidth);
    }

    private static int getMaxStringLength(double[][] array) {
        
        int maxLength = Arrays.stream(array)
                .flatMapToDouble(Arrays::stream)
                .mapToInt((d) -> String.format(ELEMENTSTRINGFORMAT, (Double) d).length())
                .max()
                .getAsInt();
        
        return maxLength;
    }

}
