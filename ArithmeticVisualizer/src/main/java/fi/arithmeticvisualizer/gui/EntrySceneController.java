package fi.arithmeticvisualizer.gui;

import static fi.arithmeticvisualizer.gui.ArrayDrawingUtils.drawNodeRepresentation;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.createBinaryNode;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import static fi.arithmeticvisualizer.logic.utils.ArrayIOUtils.stringToArray;
import fi.arithmeticvisualizer.logic.utils.BadArrayException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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

public class EntrySceneController implements Initializable {

    public static double[][] createArray(TextField field, Text resultText) {
        try {
            return stringToArray(field.getText());
        } catch (BadArrayException ex) {
            resultText.setText("Bad array: " + ex.getMessage());
            return null;
        }
    }

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

    private boolean createLeftArray() {
        leftArray = createArray(leftField, errorText);
        return leftArray != null;
    }

    private boolean createRightArray() {
        rightArray = createArray(rightField, errorText);
        return rightArray != null;
    }

    @FXML
    private void createNodeButtonPush() {
        createNode();
    }

    private boolean createNode() {
        
        errorText.setText("");

        if (createLeftArray() && createRightArray()) {
            node = createBinaryNode(leftArray, rightArray, (String) operationBox.getValue());
            if (node.validImputDims()) {
                drawNode();
                return true;
            } else {
                errorText.setText("Invalid array dimensions for operation " + node.getSymbol());
            }
        }
        return false;
    }

    private void drawNode() {
        drawNodeRepresentation(node, leftArrayGrid, rightArrayGrid, symbol, leftArray, rightArray);
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
        // TODO
    }
}
