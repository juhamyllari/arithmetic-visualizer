package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.FramePattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.ArrayList;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.FrameStringPattern.ELEMENT_BY_ELEMENT;

/**
 * A SubtractionNode is a BinaryNode that performs array addition.
 */
public class SubtractionNode extends BinaryNode {

    private final Node left;
    private final Node right;

    public SubtractionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public SubtractionNode(ArrayValue left, ArrayValue right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
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
    public Dimensions outDimensions() {
        return left.outDimensions();
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public ArrayValue evaluate() {
        if (resultValue == null) {
            leftValue = left.evaluate();
            rightValue = right.evaluate();
            resultValue = leftValue.subtract(rightValue);
        }
        return resultValue;
    }

    @Override
    public boolean validImputDimensions() {
        return left.outDimensions().equals(right.outDimensions());
    }

    @Override
    protected FramePattern getOperationPattern(EvaluationStyle style) {
        switch (style) {
            case ELEMENTWISE:
            default:
                return FramePattern.SUBTRACTIONELEMENTWISE;
        }
    }

    @Override
    public FrameSequence getFrameSequence(EvaluationStyle style) {
        evaluate();
        switch (style) {
            case ELEMENTWISE:
            default:
                return getFramesElementwise(outDimensions(), getOperationPattern(style), ELEMENT_BY_ELEMENT);
        }
    }

    @Override
    protected String frameString(FrameStringPattern pattern, int row, int column) {
        switch (pattern) {
            case ELEMENT_BY_ELEMENT:
            default:
                double leftElement = leftValue.getElement(row, column);
                double rightElement = rightValue.getElement(row, column);
                double result = leftElement - rightElement;
                return oneToOneString(leftElement, rightElement, result, "-");
        }
    }

}
