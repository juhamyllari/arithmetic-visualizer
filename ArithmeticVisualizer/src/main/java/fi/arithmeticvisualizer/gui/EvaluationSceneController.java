package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.visualization.Visualizer;
import fi.arithmeticvisualizer.logic.visualization.GraphicArray;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * This is the controller class for the second (evaluation) scene of the application.
 * The evaluation scene is where expressions are visualized.
 */
public class EvaluationSceneController {

    private BinaryNode node;
    private Visualizer visualizer;
    private GraphicArray left;
    private GraphicArray right;
    private GraphicArray result;

    @FXML
    private Button startButton;

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

    public void initData(BinaryNode node) {
        this.node = node;
        this.left = new GraphicArray(leftGrid, node.getLeft().evaluate());
        this.right = new GraphicArray(rightGrid, node.getRight().evaluate());
        this.result = new GraphicArray(resultGrid, node.evaluate());
        
        setOptionsGridVisibility(false);
        this.symbol.setText(node.getSymbol());
        this.visualizer = new Visualizer(this, node, left, right, result, subOpText);
        visualizer.drawOperands();
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void startButtonPush() {
        startButton.setText("Restart visualization");
        visualizer.visualize();
    }

    public void setOptionsGridVisibility(boolean visible) {
        this.entryOptionsGrid.setVisible(visible);
    }

}
