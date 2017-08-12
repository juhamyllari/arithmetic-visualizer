package fi.arithmeticvisualizer;

import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToArray;
import fi.arithmeticvisualizer.logic.utils.BadArrayException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class FXMLController implements Initializable {

    @FXML
    private Text resultTextLeft;

    @FXML
    private Text resultTextRight;

    @FXML
    private TextField leftField;

    @FXML
    private TextField rightField;

    @FXML
    private void leftFieldKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            createLeftArray();
        }
    }

    @FXML
    private void leftArrayButton(ActionEvent event) {
        createLeftArray();
    }

    @FXML
    private void rightFieldKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            createRightArray();
        }
    }

    @FXML
    private void rightArrayButton(ActionEvent event) {
        createRightArray();
    }

    private void createLeftArray() {
        createArray(leftField, resultTextLeft);
    }

    private void createRightArray() {
        createArray(rightField, resultTextRight);
    }

    private double[][] createArray(TextField field, Text resultText) {
        try {
            double[][] array = stringToArray(field.getText());
            resultText.setText(Arrays.deepToString(array));
            return array;
        } catch (BadArrayException ex) {
            resultText.setText("Bad array: " + ex.getMessage());
            return new double[][]{{0}};
        }
    }

    @FXML
    private void createNode() {
        System.out.println("Not implemented yet.");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
