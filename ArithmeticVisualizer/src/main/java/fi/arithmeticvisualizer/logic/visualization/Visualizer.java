package fi.arithmeticvisualizer.logic.visualization;

import fi.arithmeticvisualizer.gui.EvaluationSceneController;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.Transition;
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
        
        resultGraphicArray.getShown().clearAll();
        
        List<String> subOpStrings = node.getSubOperationStrings();

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(ANIMATIONDURATION));
            }

            protected void interpolate(double frac) {
                
                final int length = subOpStrings.size();
                int frame = Math.round(length * (float) frac);
                if (frame >= length) {
                    frame = length - 1;
                }
                
                int row = frame / node.outDimensions().getN();
                int column = frame % node.outDimensions().getN();

                setActivations(row, column);
                resultGraphicArray.getShown().setAdditionalElement(row, column);
                
                drawAll();

                subOpText.setText(subOpStrings.get(frame));
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

}
