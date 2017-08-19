package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToArray;
import fi.arithmeticvisualizer.logic.utils.BadArrayException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.createBinaryNode;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.arrayToInputString;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.transposeArray;
import javafx.scene.control.Button;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.createBinaryNode;

public class EntrySceneController implements Initializable {

    public static double[][] createArray(TextField field, Text resultText) {
        try {
            return stringToArray(field.getText());
        } catch (BadArrayException ex) {
            resultText.setText("Bad array: " + ex.getMessage());
            return null;
        }
    }

    private BinaryNode node;
    private Operand leftOperand;
    private Operand rightOperand;

    @FXML
    private VBox VBox;

    @FXML
    private Text errorText;

    @FXML
    private Text symbol;

    @FXML
    private TextField leftField;

    @FXML
    private TextField rightField;

    @FXML
    private GridPane leftArrayGrid;

    @FXML
    private GridPane rightArrayGrid;

    @FXML
    private ChoiceBox operationBox;

    @FXML
    private Button transposeLeft;

    @FXML
    private Button transposeRight;

    @FXML
    private void leftFieldKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            leftOperand.createArray();
        }
    }

    @FXML
    private void rightFieldKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            leftOperand.createArray();
        }
    }

    @FXML
    private void createNodeButtonPush() {
        createNode();
    }

    @FXML
    private void transposeLeft() {
        transpose(leftField);
    }

    @FXML
    private void transposeRight() {
        transpose(rightField);
    }
    
    @FXML
    private void selectOperation() {
        createNode();
    }

    public boolean createNode() {
        
        setErrorMessage("");

        if (leftOperand.createArray() && rightOperand.createArray()) {
            node = createBinaryNode(leftOperand.getArray(), rightOperand.getArray(), (String) operationBox.getValue());
            drawNode();
            
            if (node.validImputDims()) {
                return true;
            } else {
                setErrorMessage("Invalid array dimensions for operation " + node.getSymbol());
            }
        }
        return false;
    }

    protected void drawNode() {
        leftOperand.drawOperandArray();
        rightOperand.drawOperandArray();
        symbol.setText(node.getSymbol());
    }

    @FXML
    private void evaluateButtonPush() {
        if (createNode()) {
            loadEvaluationScene();
        }
    }

    private void loadEvaluationScene() {
        Stage stage = (Stage) VBox.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EvaluationScene.fxml"));
            Parent root = loader.load();
            EvaluationSceneController controller = loader.<EvaluationSceneController>getController();
            controller.initData(node);
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            errorText.setText("Failed to load evaluation scene: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        leftOperand = new Operand(this, null, leftArrayGrid, leftField, transposeLeft);
        rightOperand = new Operand(this, null, rightArrayGrid, rightField, transposeRight);
    }

    private void transpose(TextField entryField) {
        double[][] array = createArray(entryField, errorText);
        if (array != null) {
            entryField.setText(arrayToInputString(transposeArray(array)));
            createNode();
        }
    }

    protected void setErrorMessage(String message) {
        errorText.setText(message);
    }
}
