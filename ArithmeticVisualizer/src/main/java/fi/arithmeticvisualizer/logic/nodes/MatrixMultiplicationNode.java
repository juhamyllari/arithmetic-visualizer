package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.FramePattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.dotTypeString;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.FrameStringPattern.ROW_BY_COLUMN;

/**
 * A MatrixMultiplicationNode is a BinaryNode that performs matrix
 * multiplication.
 */
public class MatrixMultiplicationNode extends BinaryNode {

    public MatrixMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public MatrixMultiplicationNode(ArrayValue left, ArrayValue right) {
        this(new ValueNode(left), new ValueNode(right));
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public Dimensions outDimensions() {
        return new Dimensions(left.outDimensions().getM(), right.outDimensions().getN());
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

    protected FramePattern getOperationPattern(EvaluationStyle style) {
        //  Only implemented for elementwise evaluation at this time.
        return FramePattern.MATRIXMULTIPLICATIONELEMENTWISE;
    }

    @Override
    public FrameSequence getFrameSequence(EvaluationStyle style) {
        evaluate();
        switch (style) {
            case ELEMENTWISE:
            default:
                return getFramesElementwise(outDimensions(), getOperationPattern(style), ROW_BY_COLUMN);
        }
    }

    @Override
    public boolean validImputDimensions() {
        return left.outDimensions().getN() == right.outDimensions().getM();
    }

    @Override
    protected String frameString(FrameStringPattern pattern, int row, int column) {
        switch (pattern) {
            case ROW_BY_COLUMN:
            default:
                double result = ArrayValue.dotVectors(leftValue.getRow(row), rightValue.getColumn(column));
                return dotTypeString(leftValue.getRow(row), rightValue.getColumn(column), result, "*", "+");
        }
    }

}
