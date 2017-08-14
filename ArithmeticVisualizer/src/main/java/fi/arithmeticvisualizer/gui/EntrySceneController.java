package fi.arithmeticvisualizer.gui;

import static fi.arithmeticvisualizer.gui.ArrayDrawingUtils.drawArray;
import fi.arithmeticvisualizer.logic.utils.WrongShapeException;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.nodes.Node;
import fi.arithmeticvisualizer.logic.nodes.ValueNode;
import fi.arithmeticvisualizer.logic.utils.BinaryOperation;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToArray;
import fi.arithmeticvisualizer.logic.utils.BadArrayException;
import fi.arithmeticvisualizer.logic.utils.OperationSelector;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EntrySceneController implements Initializable {

    private double[][] leftArray;
    private double[][] rightArray;
    private BinaryNode node;

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
        leftArray = createArray(leftField, errorText);
    }

    private void createRightArray() {
        rightArray = createArray(rightField, errorText);
    }

    private double[][] createArray(TextField field, Text resultText) {
        try {
            return stringToArray(field.getText());
        } catch (BadArrayException ex) {
            resultText.setText("Bad array: " + ex.getMessage());
            return null;
        }
    }

    @FXML
    private void createNodeButton() {

        errorText.setText("");
        createLeftArray();
        createRightArray();

        OperationSelector selector = new OperationSelector();

        BinaryOperation operation = selector.getOperation((String) operationBox.getValue());

        if (leftArray != null && rightArray != null) {
            Node leftValueNode = new ValueNode(leftArray);
            Node rightValueNode = new ValueNode(rightArray);
            node = new BinaryNode(leftValueNode, rightValueNode, operation);

            try {
                // The node is evaluated to verify the legality of the
                // array dimensions wrt. the operation. This is a kludge
                // and may be replaced by explicit dimension checking.
                drawNodeRepresentation();
            } catch (WrongShapeException exc) {
                errorText.setText("Illegal array dimensions for operation " + node.getSymbol());
            }
        }
    }

    private void drawNodeRepresentation() throws WrongShapeException {
        node.evaluate();
        symbol.setText(node.getSymbol());
        drawArray(leftArrayGrid, leftArray);
        drawArray(rightArrayGrid, rightArray);
    }

    @FXML
    private void evaluateButton() {

        createNodeButton();
        
        try {
            drawNodeRepresentation();
            loadEvaluationScene();
        } catch (WrongShapeException ex) {
        }

    }

    private void loadEvaluationScene() {
        Stage stage = (Stage) VBox.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/EvaluationScene.fxml"))));
        } catch (IOException ex) {
            errorText.setText("Failed to load evaluation scene.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
