package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.gui.OperationPattern;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.FrameStringPattern.ELEMENTBYELEMENT;
import java.util.ArrayList;

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
    protected OperationPattern getOperationPattern(EvaluationStyle style) {
        switch (style) {
            case ELEMENTWISE:
            default:
                return OperationPattern.RIGHTSCALARMULTIPLICATIONELEMENTWISE;
        }
    }

    @Override
    public FrameSequence getFrameSequence(EvaluationStyle style) {
        evaluate();
        switch (style) {
            case ELEMENTWISE:
            default:
                return getFramesElementwise(outDimensions(), getOperationPattern(style), ELEMENTBYELEMENT);
        }
    }

    @Override
    protected String frameString(FrameStringPattern pattern, int row, int column) {
        switch (pattern) {
            case ELEMENTBYELEMENT:
            default:
                double leftElement = leftValue.getElement(row, column);
                double rightElement = rightValue.getElement(0, 0);
                double result = leftElement * rightElement;
                return oneToOneString(leftElement, rightElement, result, "*");
        }
    }

}
