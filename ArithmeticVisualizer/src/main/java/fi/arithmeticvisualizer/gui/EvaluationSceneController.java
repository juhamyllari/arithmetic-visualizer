package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the controller class for the second (evaluation) scene of the
 * application. The evaluation scene is where expressions are visualized.
 */
public class EvaluationSceneController {

    private BinaryNode node;
    private Visualizer visualizer;
    private GraphicArray left;
    private GraphicArray right;
    private GraphicArray result;
    private boolean paused;

    @FXML
    private VBox vBox;

    @FXML
    private Button startButton;

    @FXML
    private Button pausePlayButton;

    @FXML
    private GridPane leftGrid;

    @FXML
    private GridPane rightGrid;

    @FXML
    private GridPane resultGrid;

    @FXML
    private Text symbol;

    @FXML
    private Text subOpText;

    @FXML
    private HBox lowerRow;

    @FXML
    private GridPane entryOptionsGrid;

    @FXML
    private ChoiceBox operandChoiceBox;

    protected void initData(BinaryNode node) {
        this.node = node;
        this.left = new GraphicArray(leftGrid, node.getLeft().evaluate());
        this.right = new GraphicArray(rightGrid, node.getRight().evaluate());
        this.result = new GraphicArray(resultGrid, node.evaluate());
        this.paused = true;

        setOptionsGridVisibility(false);
        this.symbol.setText(node.getSymbol());
        this.visualizer = new Visualizer(this, node, left, right, result, subOpText);
        visualizer.drawOperands();
    }

    @FXML
    private void pausePlayPush() {
        if (paused) {
            visualizer.play();
            setPaused(false);
        } else {
            visualizer.pause();
            setPaused(true);
        }
    }

    protected void setPaused(boolean setToPaused) {
        if (setToPaused) {
            paused = true;
            pausePlayButton.setText("Play");
        } else {
            paused = false;
            pausePlayButton.setText("Pause");
        }
    }

    protected void setOptionsGridVisibility(boolean visible) {
        this.entryOptionsGrid.setVisible(visible);
    }

    @FXML
    private void nextExpressionButtonPush() {
        loadEntryScene();
    }

    private void loadEntryScene() {

        Stage stage = (Stage) vBox.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EntryScene.fxml"));
            Parent root = loader.load();
            EntrySceneController controller = loader.<EntrySceneController>getController();
            initializeEntrySceneArrayInputs(controller);
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            System.out.println("Failed to load evaluation scene: " + ex.getMessage());
        }
    }

    private void initializeEntrySceneArrayInputs(EntrySceneController controller) {

        String resultText = result.toInputString();
        String operandChoice = (String) operandChoiceBox.getValue();

        switch (operandChoice) {
            case "Left operand":
                controller.initializeTextFieldData(resultText, "");
                break;
            case "Right operand":
                controller.initializeTextFieldData("", resultText);
                break;
            case "Both operands":
                controller.initializeTextFieldData(resultText, resultText);
                break;
            default:
                controller.initializeTextFieldData("", "");
                break;
        }
    }
}
