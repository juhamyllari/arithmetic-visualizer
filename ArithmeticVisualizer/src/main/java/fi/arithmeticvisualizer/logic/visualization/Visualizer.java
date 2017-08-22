package fi.arithmeticvisualizer.logic.visualization;

import fi.arithmeticvisualizer.gui.EvaluationSceneController;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * The Visualizer class is responsible for visualizing the evaluation of expressions.
 */
public class Visualizer {
    
    public static final int ANIMATIONDURATION = 5000;

    private final EvaluationSceneController controller;
    private final BinaryNode node;
    private final GraphicArray leftGraphicArray;
    private final GraphicArray rightGraphicArray;
    private final GraphicArray resultGraphicArray;
    private final Text subOpText;

    public Visualizer(EvaluationSceneController controller, BinaryNode node, GraphicArray left, GraphicArray right, GraphicArray result, Text subOpText) {
        this.controller = controller;
        this.node = node;
        this.leftGraphicArray = left;
        this.rightGraphicArray = right;
        this.resultGraphicArray = result;
        this.subOpText = subOpText;
    }

    public void visualize() {
        
        List<SubOperation> evaluationList = this.getSubOperations();

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(ANIMATIONDURATION));
            }

            protected void interpolate(double frac) {
                final int length = evaluationList.size();
                int frame = Math.round(length * (float) frac);
                if (frame >= length) {
                    frame = length - 1;
                }
                
                int row = frame / node.outDimensions().getN();
                int column = frame % node.outDimensions().getN();

                SubOperation state = evaluationList.get(frame);
                
                setActivations(row, column);
                resultGraphicArray.setShown(state.getShow());
                
                drawAll();

                subOpText.setText(state.getSubOpString());
            }

        };

        animation.play();
        
        controller.setOptionsGridVisibility(true);
    }

    public void drawOperands() {
        leftGraphicArray.draw();
        rightGraphicArray.draw();
    }
    
    private void drawAll() {
        leftGraphicArray.draw();
        rightGraphicArray.draw();
        resultGraphicArray.draw();
    }
    
    private void setActivations(int row, int column) {
        ActivationPattern activationPattern = node.getActivationPattern();
        leftGraphicArray.setActivation(new Pattern(activationPattern.getLeftPattern(), row, column));
        rightGraphicArray.setActivation(new Pattern(activationPattern.getRightPattern(), row, column));
        resultGraphicArray.setActivation(new Pattern(activationPattern.getResultPattern(), row, column));
    }
    
    public List<SubOperation> getSubOperations() {
        
        Dimensions resultDims = node.outDimensions();
        
        List<SubOperation> states = new ArrayList<>();
        
        BooleanMask show = new BooleanMask(resultDims.getM(), resultDims.getN());
        
        ArrayList<String> subOpStrings = node.getSubOperationStrings();
        
        for (int i = 0; i < resultDims.getM(); i++) {
            for (int j = 0; j < resultDims.getN(); j++) {
                String subOpString = subOpStrings.get(i * resultDims.getN() + j);
                
                show.setAdditionalElement(i, j);
                BooleanMask currentlyShown = show.clone();
                
                SubOperation subOp = new SubOperation(currentlyShown, subOpString);
                states.add(subOp);
            }
        }
        
        return states;
    }

}
