package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.suboperands.Element;
import fi.arithmeticvisualizer.gui.frames.ElementWithElementFrame;
import fi.arithmeticvisualizer.gui.frames.Frame;
import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.DoubleArray;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.evaluation.RealArray;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

/**
 * A RightScalarMultiplicationNode is a BinaryNode that performs scalar
 * multiplication from the right.
 */
public class RightScalarMultiplicationNode extends BinaryNode {

    private Node left;
    private Node right;

    /**
     * Constructs a RightScalarMultiplicationNode.
     *
     * @param left the left child Node
     * @param right the right child Node
     */
    public RightScalarMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    /**
     * A convenience method for constructing a RightScalarMultiplicationNode
     * whose children are ValueNodes containing the provided RealArrays.
     *
     * @param left the value of the left child Node
     * @param right the value of the right child Node
     */
    public RightScalarMultiplicationNode(RealArray left, RealArray right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
    }

    @Override
    public Dimensions outDimensions() {
        return left.outDimensions();
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public RealArray evaluate() {
        if (resultValue == null) {
            leftValue = left.evaluate();
            rightValue = right.evaluate();
            resultValue = leftValue.multiply(rightValue);
        }
        return resultValue;
    }

    @Override
    public Node getLeft() {
        return left;
    }

    @Override
    public Node getRight() {
        return right;
    }

    @Override
    public boolean validImputDimensions() {
        return right.isScalar();
    }

    @Override
    public FrameSequence getFrameSequence(EvaluationStyle style) {
        evaluate();
        switch (style) {
            case ELEMENTWISE:
            default:
                return getFramesElementwise();
        }
    }

    private FrameSequence getFramesElementwise() {

        ArrayList<Frame> frames = new ArrayList<>();

        for (int i = 0; i < outDimensions().getM(); i++) {
            for (int j = 0; j < outDimensions().getN(); j++) {
                Element leftOperand = new Element(i, j, leftValue.getElement(i, j));
                Element rightOperand = new Element(0, 0, rightValue.getElement(0, 0));
                DoubleBinaryOperator operator = (double left, double right) -> left * right;
                Frame frame = new ElementWithElementFrame(operator, "*", leftOperand, rightOperand, i, j);
                frames.add(frame);
            }
        }

        return new FrameSequence(frames);

    }

}
