package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.gui.EntrySceneController;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.BadArrayException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Objects of the Operand class represent the operands of expressions entered
 * in the entry scene.
 */
public class Operand {

    private EntrySceneController controller;
    private ArrayValue array;
    private GridPane grid;
    private TextField entryField;
    private Button transposeButton;

    /**
     * Constructs a new Operand.
     * 
     * @param controller the EntrySceneController
     * @param array the value of the operand
     * @param grid a GridPane to display the operand
     * @param entryField a TextField to enter values as Strings
     * @param transposeButton a Button for transposing the operand
     */
    public Operand(EntrySceneController controller, double[][] array, GridPane grid, TextField entryField, Button transposeButton) {
        this.controller = controller;
        this.array = array == null ? null : new ArrayValue(array);
        this.grid = grid;
        this.entryField = entryField;
        this.transposeButton = transposeButton;
    }

    private void transpose() {
        if (createArray()) {
            entryField.setText(array.transpose().toInputString());
            createArray();
            drawOperandArray();
        }
    }

    protected boolean createArray() {
        try {
            array = new ArrayValue(entryField.getText());
            drawOperandArray();
            return true;
        } catch (BadArrayException ex) {
            controller.setErrorMessage("Bad array: " + ex.getMessage());
            return false;
        }
    }

    protected ArrayValue getArrayValue() {
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

