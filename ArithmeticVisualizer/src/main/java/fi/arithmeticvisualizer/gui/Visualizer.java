package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.nodes.ActivationPattern;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.nodes.BooleanMask;
import fi.arithmeticvisualizer.logic.nodes.SubOperation;
import fi.arithmeticvisualizer.logic.utils.Dimensions;
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
                setCycleDuration(Duration.millis(5000));
            }

            protected void interpolate(double frac) {
                final int length = evaluationList.size();
                int n = Math.round(length * (float) frac);
                if (n >= length) {
                    n = length - 1;
                }

                SubOperation state = evaluationList.get(n);
                
                leftGraphicArray.setActivation(state.getLeftActivation());
                rightGraphicArray.setActivation(state.getRightActivation());
                resultGraphicArray.setActivation(state.getResultActivation());
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
    
    public List<SubOperation> getSubOperations() {
        
        ActivationPattern pattern = node.getActivationPattern();
        
        Dimensions leftDims = node.getLeft().outDims();
        Dimensions rightDims = node.getRight().outDims();
        Dimensions resultDims = node.outDims();
        
        List<SubOperation> states = new ArrayList<>();
        BooleanMask show = new BooleanMask(resultDims.getM(), resultDims.getN());
        ArrayList<String> subOpStrings = node.getSubOperationStrings();
        int subOpIndex = 0;
        
        for (int i = 0; i < resultDims.getM(); i++) {
            for (int j = 0; j < resultDims.getN(); j++) {
                BooleanMask leftActivation = new BooleanMask(leftDims, pattern.getLeftPattern(), i, j);
                BooleanMask rightActivation = new BooleanMask(rightDims, pattern.getRightPattern(), i, j);
                BooleanMask resultActivation = new BooleanMask(resultDims, pattern.getResultPattern(), i, j);
                String subOpString = subOpStrings.get(subOpIndex);
                
                show.setElement(i, j);
                BooleanMask currentlyShown = show.clone();
                
                SubOperation state = new SubOperation(leftActivation, rightActivation, resultActivation, currentlyShown, subOpString);
                states.add(state);
                subOpIndex++;
            }
        }
        
        return states;
    }

}
