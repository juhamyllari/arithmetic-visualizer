package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
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

public class GraphicArray {

    public static final String ARRAYBACKGROUNDSTYLE = "-fx-background-color: beige";
    public static final String ELEMENTSTRINGFORMAT = "%5.1f";
    public static final int ELEMENTFONTSIZE = 16;
    public static final int DEFAULTCOLUMNWIDTH = 65;
    public static final int ROWHEIGHT = 30;

    public static final Color ACTIVATIONRECTANGLECOLOUR = Color.CRIMSON;
    public static final int ACTIVATIONRECTANGLEHEIGHT = 30;
    public static final double ACTIVATIONRECTANGLEOPACITY = .3;

    private GridPane grid;
    private ArrayValue array;
    private int m;
    private int n;

    public GraphicArray(GridPane grid, ArrayValue array) {
        this.grid = grid;
        this.array = array;
        this.m = array.getDims().getM();
        this.n = array.getDims().getN();
    }

    public void draw() {

        BooleanMask activation = new BooleanMask(array.getDims());
        drawWithActivation(activation);

    }

    public void drawWithActivation(BooleanMask activationMask) {

        BooleanMask showMask = new BooleanMask(array.getDims());
        showMask.setAll();
        drawWithMasks(activationMask, showMask);

    }

    public void drawWithMasks(BooleanMask activationMask, BooleanMask showMask) {

        boolean[][] activation = activationMask.getMask();
        boolean[][] show = showMask.getMask();

        setUpGrid();
        int activationRectangleWidth = getColumnWidth();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double value = array.getElement(i, j);
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

    private void setUpGrid() {

        int columnWidth = Math.max(getColumnWidth(), DEFAULTCOLUMNWIDTH);

        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.setStyle(ARRAYBACKGROUNDSTYLE);

        for (int i = 0; i < m; i++) {
            grid.getRowConstraints().add(new RowConstraints(ROWHEIGHT));
        }

        for (int i = 0; i < n; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(columnWidth));
        }
    }

    private int getColumnWidth() {

        int maxLength = getMaxStringLength();
        String testString = String.join("", Collections.nCopies(maxLength, "0"));

        Text testText = new Text(testString);
        testText.fontProperty().set(new Font(ELEMENTFONTSIZE));

        int empiricalWidth = (int) Math.ceil(testText.getLayoutBounds().getWidth());

        return Math.max(DEFAULTCOLUMNWIDTH, empiricalWidth);
    }

    private int getMaxStringLength() {

        int maxLength = Arrays.stream(array.getValue())
                .flatMapToDouble(Arrays::stream)
                .mapToInt((d) -> String.format(ELEMENTSTRINGFORMAT, (Double) d).length())
                .max()
                .getAsInt();

        return maxLength;
    }
}
