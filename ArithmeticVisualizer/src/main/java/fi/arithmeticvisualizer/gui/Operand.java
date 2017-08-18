package fi.arithmeticvisualizer.gui;

import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.arrayToInputString;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToArray;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.transposeArray;
import fi.arithmeticvisualizer.logic.utils.BadArrayException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Operand {

    private EntrySceneController controller;
    private double[][] array;
    private GridPane grid;
    private TextField entryField;
    private Button transposeButton;

    public Operand(EntrySceneController controller, double[][] array, GridPane grid, TextField entryField, Button transposeButton) {
        this.controller = controller;
        this.array = array;
        this.grid = grid;
        this.entryField = entryField;
        this.transposeButton = transposeButton;
    }

    private void transpose() {
        if (createArray()) {
            entryField.setText(arrayToInputString(transposeArray(array)));
            createArray();
            drawOperandArray();
        }
    }

    public boolean createArray() {
        try {
            array = stringToArray(entryField.getText());
            drawOperandArray();
            return true;
        } catch (BadArrayException ex) {
            controller.setErrorMessage("Bad array: " + ex.getMessage());
            return false;
        }
    }

    public double[][] getArray() {
        return array;
    }
    
    public void drawOperandArray() {
        ArrayDrawingUtils.drawArray(grid, array);
    }
    
}

