package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.evaluation.DoubleArray;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Objects of the Operand class represent the operands of expressions entered in
 * the entry scene.
 */
public class Operand {

    private final EntrySceneController controller;
    private DoubleArray array;
    private final GridPane grid;
    private final TextField entryField;

    /**
     * Constructs a new Operand.
     *
     * @param controller the EntrySceneController
     * @param array the value of the operand
     * @param grid a GridPane to display the operand
     * @param entryField a TextField to enter values as Strings
     */
    public Operand(EntrySceneController controller, double[][] array, GridPane grid, TextField entryField) {
        this.controller = controller;
        this.array = array == null ? null : new DoubleArray(array);
        this.grid = grid;
        this.entryField = entryField;
    }

    protected boolean createArray() {
        try {
            array = new DoubleArray(entryField.getText());
            drawOperandArray();
        } catch (BadArrayException ex) {
            controller.setErrorMessage("Bad array: " + ex.getMessage());
            return false;
        }
        return true;
    }

    protected DoubleArray getArrayValue() {
        return array;
    }

    protected void drawOperandArray() {
        GraphicArray graphicArray = new GraphicArray(grid, array);
        graphicArray.draw();
    }

    protected void setEntryFieldText(String string) {
        entryField.setText(string);
    }
}
