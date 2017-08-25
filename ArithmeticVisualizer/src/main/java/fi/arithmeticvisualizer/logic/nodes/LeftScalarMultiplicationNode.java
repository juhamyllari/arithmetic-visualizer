package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.gui.OperationPattern;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.FrameStringPattern.ELEMENTBYELEMENT;

/**
 * A LeftScalarMultiplicationNode is a BinaryNode that performs scalar
 * multiplication from the left.
 */
public class LeftScalarMultiplicationNode extends BinaryNode {

    public LeftScalarMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public LeftScalarMultiplicationNode(ArrayValue left, ArrayValue right) {
        this(new ValueNode(left), new ValueNode(right));
    }

    @Override
    public Dimensions outDimensions() {
        return right.outDimensions();
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
        return left.isScalar();
    }

    @Override
    protected OperationPattern getOperationPattern(EvaluationStyle style) {
        switch (style) {
            case ELEMENTWISE:
            default:
                return OperationPattern.LEFTSCALARMULTIPLICATIONELEMENTWISE;
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
                double leftElement = leftValue.getElement(0, 0);
                double rightElement = rightValue.getElement(row, column);
                double result = leftElement * rightElement;
                return oneToOneString(leftElement, rightElement, result, "*");
        }
    }

}
