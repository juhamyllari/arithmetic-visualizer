package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.gui.frames.Frame;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode.EvaluationStyle;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.EvaluationStyle.ELEMENTWISE;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * The Visualizer class is responsible for visualizing the evaluation of
 * expressions.
 */
public class Visualizer {

    public static final int ANIMATIONDURATION = 5000;

    private final EvaluationSceneController controller;
    private final BinaryNode node;
    private final GraphicArray leftGraphicArray;
    private final GraphicArray rightGraphicArray;
    private final GraphicArray resultGraphicArray;
    private final Text subOpText;

    /**
     * Constructs a Visualizer.
     * 
     * @param controller the Scene Controller
     * @param node the Node to be visualized
     * @param left the GraphicArray representing the left operand
     * @param right the GraphicArray representing the right operand
     * @param result the GraphicArray representing the resulting value
     * @param subOpText a Text object to display suboperations
     */
    public Visualizer(EvaluationSceneController controller, BinaryNode node, GraphicArray left, GraphicArray right, GraphicArray result, Text subOpText) {
        this.controller = controller;
        this.node = node;
        this.node.evaluate();
        this.leftGraphicArray = left;
        this.rightGraphicArray = right;
        this.resultGraphicArray = result;
        this.subOpText = subOpText;
    }

    /**
     * Creates and plays an animation of the operation.
     */
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

                setMasksByMaskSetters(frame);
                drawAll();

                subOpText.setText(frame.getSubOperationString());
            }

        };

        animation.play();

        controller.setOptionsGridVisibility(true);
    }

    /**
     * Draws the two operands of the Node.
     */
    public void drawOperands() {
        leftGraphicArray.draw();
        rightGraphicArray.draw();
    }

    private void drawAll() {
        leftGraphicArray.draw();
        rightGraphicArray.draw();
        resultGraphicArray.draw();
    }

    private void setMasksByMaskSetters(Frame frame) {
        frame.getLeftActivation().accept(leftGraphicArray.getActivation());
        frame.getRightActivation().accept(rightGraphicArray.getActivation());
        frame.getResultActivation().accept(resultGraphicArray.getActivation());
        frame.getResultShown().accept(resultGraphicArray.getShown());
    }

}
