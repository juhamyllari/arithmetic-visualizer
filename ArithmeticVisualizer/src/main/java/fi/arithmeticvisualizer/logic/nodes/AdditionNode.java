package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.OperationPattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.FrameStringPattern.ELEMENTBYELEMENT;
import java.util.ArrayList;

/**
 * An AdditionNode is a BinaryNode that performs array addition.
 */
public class AdditionNode extends BinaryNode {

    public AdditionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public AdditionNode(ArrayValue left, ArrayValue right) {
        this(new ValueNode(left), new ValueNode(right));
    }

    @Override
    public Dimensions outDimensions() {
        return left.outDimensions();
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public ArrayValue evaluate() {
        if (resultValue == null) {
            leftValue = left.evaluate();
            rightValue = right.evaluate();
            resultValue = leftValue.add(rightValue);
        }
        return resultValue;
    }

    @Override
    public boolean validImputDimensions() {
        return left.outDimensions().equals(right.outDimensions());
    }

    @Override
    protected OperationPattern getOperationPattern(EvaluationStyle style) {
        switch (style) {
            case ELEMENTWISE:
            default:
                return OperationPattern.ADDITIONELEMENTWISE;
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
                double rightElement = rightValue.getElement(row, column);
                double result = leftElement + rightElement;
                return oneToOneString(leftElement, rightElement, result, "+");
        }
    }
}
