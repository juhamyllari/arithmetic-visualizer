package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.OperationPattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.FrameStringPattern.ROWBYCOLUMN;
import static fi.arithmeticvisualizer.logic.nodes.BinaryNode.dotTypeString;

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

    protected OperationPattern getOperationPattern(EvaluationStyle style) {
        //  Only implemented for elementwise evaluation at this time.
        return OperationPattern.MATRIXMULTIPLICATIONELEMENTWISE;
    }

    @Override
    public FrameSequence getFrameSequence(EvaluationStyle style) {
        evaluate();
        switch (style) {
            case ELEMENTWISE:
                return getFramesElementwise(outDimensions(), getOperationPattern(style), ROWBYCOLUMN);
            default:
                return getFramesElementwise(outDimensions(), getOperationPattern(style), ROWBYCOLUMN);
        }
    }

    @Override
    public boolean validImputDimensions() {
        return left.outDimensions().getN() == right.outDimensions().getM();
    }

    @Override
    protected String frameString(FrameStringPattern pattern, int row, int column) {
        switch (pattern) {
            case ROWBYCOLUMN:
            default:
                double result = ArrayValue.dotVectors(leftValue.getRow(row), rightValue.getColumn(column));
                return dotTypeString(leftValue.getRow(row), rightValue.getColumn(column), result, "*", "+");
        }
    }

}
