package fi.arithmeticvisualizer.gui;

import static fi.arithmeticvisualizer.gui.ArrayDrawingUtils.drawArray;
import fi.arithmeticvisualizer.logic.nodes.ActivationPattern;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.nodes.BooleanMask;
import fi.arithmeticvisualizer.logic.nodes.EvaluationState;
import fi.arithmeticvisualizer.logic.utils.Dims;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Visualizer {

    private BinaryNode node;
    private GridPane leftGrid;
    private GridPane rightGrid;
    private GridPane resultGrid;

    public Visualizer(BinaryNode node, GridPane leftGrid, GridPane rightGrid, GridPane resultGrid) {
        this.node = node;
        this.leftGrid = leftGrid;
        this.rightGrid = rightGrid;
        this.resultGrid = resultGrid;
    }

    public void visualize() {

        double[][] leftArray = node.getLeft().evaluate().getValue();
        double[][] rightArray = node.getRight().evaluate().getValue();
        double[][] resultArray = node.evaluate().getValue();

        List<EvaluationState> evaluationList = this.getEvaluationStates();

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(5000));
            }

            protected void interpolate(double frac) {
                final int length = evaluationList.size();
                int n = Math.round(length * (float) frac);
                if (n >= length) {
                    n = length - 1;
                }

                EvaluationState state = evaluationList.get(n);

                ArrayDrawingUtils.drawArrayWithMasks(leftGrid, leftArray, state.getLeftActivation());
                ArrayDrawingUtils.drawArrayWithMasks(rightGrid, rightArray, state.getRightActivation());
                ArrayDrawingUtils.drawArrayWithMasks(resultGrid, resultArray, state.getResultActivation(), state.getShow());
            }

        };

        animation.play();
    }

    public void drawOperands() {
        drawArray(leftGrid, node.getLeft().evaluate().getValue());
        drawArray(rightGrid, node.getRight().evaluate().getValue());
    }
    
    public List<EvaluationState> getEvaluationStates() {
        
        ActivationPattern pattern = node.getActivationPattern();
        
        Dims leftDims = node.getLeft().outDims();
        Dims rightDims = node.getRight().outDims();
        Dims resultDims = node.outDims();
        
        List<EvaluationState> states = new ArrayList<>();
        BooleanMask show = new BooleanMask(resultDims.getM(), resultDims.getN());
        
        for (int i = 0; i < resultDims.getM(); i++) {
            for (int j = 0; j < resultDims.getN(); j++) {
                BooleanMask leftActivation = new BooleanMask(leftDims, pattern.getLeftPattern(), i, j);
                BooleanMask rightActivation = new BooleanMask(rightDims, pattern.getRightPattern(), i, j);
                BooleanMask resultActivation = new BooleanMask(resultDims, pattern.getResultPattern(), i, j);
                
                show.setElement(i, j);
                BooleanMask currentlyShown = show.clone();
                
                EvaluationState state = new EvaluationState(leftActivation, rightActivation, resultActivation, currentlyShown);
                states.add(state);
            }
        }
        
        return states;
    }

}
