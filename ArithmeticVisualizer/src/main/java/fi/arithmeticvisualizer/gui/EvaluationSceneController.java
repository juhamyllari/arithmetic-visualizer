package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.utils.Dims;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class EvaluationSceneController {

    private BinaryNode node;
    private double[][] leftArray;
    private double[][] rightArray;
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
    private HBox lowerRow;

    public void initData(BinaryNode node) {
        this.node = node;
        this.leftArray = node.getLeft().evaluate().getValue();
        this.rightArray = node.getRight().evaluate().getValue();
        this.symbol.setText(node.getSymbol());
        this.visualizer = new Visualizer(node, leftGrid, rightGrid, resultGrid);
        visualizer.drawOperands();
    }

    @FXML
    private void startButtonPush() {
        visualizer.visualize();
    }

}
