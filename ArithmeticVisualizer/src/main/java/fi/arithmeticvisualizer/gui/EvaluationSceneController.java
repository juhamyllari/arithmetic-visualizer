package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class EvaluationSceneController {

    private BinaryNode node;
    private Visualizer visualizer;

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
        setOptionsGridVisibility(false);
        this.symbol.setText(node.getSymbol());
        this.visualizer = new Visualizer(node, leftGrid, rightGrid, resultGrid, subOpText, this);
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
