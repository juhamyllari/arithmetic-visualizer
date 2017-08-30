package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.frames.BooleanMask;
import fi.arithmeticvisualizer.logic.evaluation.DoubleArray;
import fi.arithmeticvisualizer.logic.evaluation.RealArray;
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

/**
 * A GraphicArray object is responsible for drawing a single array.
 */
public class GraphicArray {

    public static final String ARRAYBACKGROUNDSTYLE = "-fx-background-color: beige";
    public static final String ELEMENTSTRINGFORMAT = "%5.1f";
    public static final int ELEMENTFONTSIZE = 28;
    public static final int DEFAULTCOLUMNWIDTH = 65;
    public static final int ROWHEIGHT = 30;

    public static final Color ACTIVATIONRECTANGLECOLOUR = Color.CRIMSON;
    public static final int ACTIVATIONRECTANGLEHEIGHT = 30;
    public static final double ACTIVATIONRECTANGLEOPACITY = .3;

    private GridPane grid;
    private RealArray array;
    private int m;
    private int n;
    private BooleanMask activation;
    private BooleanMask shown;

    /**
     * Constructs a GraphicArray.
     * 
     * @param grid the GridPane on which the array is to be displayed
     * @param array the array to be displayed
     */
    public GraphicArray(GridPane grid, RealArray array) {
        this.grid = grid;
        this.array = array;
        this.m = array.getDimensions().getM();
        this.n = array.getDimensions().getN();
        this.activation = new BooleanMask(array.getDimensions());
        this.shown = new BooleanMask(array.getDimensions());
        this.shown.setAll();;
    }

    /**
     * Draws the array.
     */
    public void draw() {

        setUpGrid();
        int activationRectangleWidth = getColumnWidth();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double value = array.getElement(i, j);
                Text element = new Text(String.format(ELEMENTSTRINGFORMAT, value));
                element.fontProperty().set(new Font(ELEMENTFONTSIZE));
                element.setVisible(shown.getMask()[i][j]);
                StackPane stack = new StackPane(element);
                if (activation.getMask()[i][j]) {
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

        int maxLength = array.getValues()
                .stream()
                .mapToInt((d) -> String.format(ELEMENTSTRINGFORMAT, (Double) d).length())
                .max()
                .getAsInt();

        return maxLength;
    }

    /**
     * Returns the activation of the GraphicArray. Activation indicates which
     * elements of an array are involved in the operation taking place in that
     * Frame. To change the activation, use this method and modify the returned
     * {@code BooleanMask}.
     * 
     * @return the activation of the array
     */
    public BooleanMask getActivation() {
        return activation;
    }

    /**
     * Returns a {@code BooleanMask} indicating which elements of the array are
     * to be displayed. Operand arrays are typically fully displayed in each
     * Frame of an animation, while result arrays are typically revealed
     * gradually. To change which elements are shown, use this method and modify
     * the returned {@code BooleanMask}.
     * 
     * @return a {@code BooleanMask} indicating which elements are shown
     */
    public BooleanMask getShown() {
        return shown;
    }

    /**
     * Returns a String representation of the contained array. The String
 representation can be used as an input when constructing a new
 DoubleArray.
     * 
     * @return a String representation of the array
     */
    public String toInputString() {
        return array.toInputString();
    }

}
