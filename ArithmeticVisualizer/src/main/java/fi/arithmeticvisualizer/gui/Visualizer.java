package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode.EvaluationStyle;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.EvaluationStyle.ELEMENTWISE;
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
        this.node.evaluate();
        this.leftGraphicArray = left;
        this.rightGraphicArray = right;
        this.resultGraphicArray = result;
        this.subOpText = subOpText;
    }

    public void visualize() {
        
        // EvaluationStyle selection to be implemented later.
        EvaluationStyle style = ELEMENTWISE;
        
        resultGraphicArray.getShown().clearAll();
        
        FrameSequence sequence = node.getFrameSequence(style);
        
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(ANIMATIONDURATION));
            }

            protected void interpolate(double frac) {
                
                final int length = sequence.getLength();
                int frameIndex = Math.round(length * (float) frac);
                
                if (frameIndex >= length) {
                    frameIndex = length - 1;
                }
                
                Frame frame = sequence.getFrame(frameIndex);
                
                int row = frame.getRow();
                int column = frame.getColumn();

                setMasks(frame, sequence.getPattern());
                
                drawAll();

                subOpText.setText(frame.getFrameString());
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
    
    private void setMasks(Frame frame, FramePattern pattern) {
        int row = frame.getRow();
        int column = frame.getColumn();
        leftGraphicArray.getActivation().setByPattern(pattern.leftPattern, row, column);
        rightGraphicArray.getActivation().setByPattern(pattern.rightPattern, row, column);
        resultGraphicArray.getActivation().setByPattern(pattern.resultPattern, row, column);
        resultGraphicArray.getShown().setByPattern(pattern.shownPattern, row, column);
    }

}
