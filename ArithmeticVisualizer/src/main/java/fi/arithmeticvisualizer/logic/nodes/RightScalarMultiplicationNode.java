package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.Element;
import fi.arithmeticvisualizer.gui.ElementWithElement;
import fi.arithmeticvisualizer.gui.Frame;
import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

/**
 * A RightScalarMultiplicationNode is a BinaryNode that performs scalar
 * multiplication from the right.
 */
public class RightScalarMultiplicationNode extends BinaryNode {

    private Node left;
    private Node right;

    public RightScalarMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public RightScalarMultiplicationNode(ArrayValue left, ArrayValue right) {
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
    public ArrayValue evaluate() {
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
                Frame frame = new ElementWithElement(operator, "*", leftOperand, rightOperand, i, j);
                frames.add(frame);
            }
        }

        return new FrameSequence(frames);

    }

}
