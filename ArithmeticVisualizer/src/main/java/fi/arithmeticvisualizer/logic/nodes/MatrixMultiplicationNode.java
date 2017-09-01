package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.suboperands.Column;
import fi.arithmeticvisualizer.logic.frames.FrameSequence;
import fi.arithmeticvisualizer.logic.suboperands.Row;
import fi.arithmeticvisualizer.logic.frames.RowDotColumnFrame;
import fi.arithmeticvisualizer.logic.frames.Frame;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.evaluation.RealArray;
import java.util.ArrayList;

/**
 * A MatrixMultiplicationNode is a BinaryNode that performs matrix
 * multiplication.
 */
public class MatrixMultiplicationNode extends BinaryNode {

    /**
     * Constructs a MatrixMultiplicationNode.
     *
     * @param left the left child Node
     * @param right the right child Node
     */
    public MatrixMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    /**
     * A convenience method for constructing a MatrixMultiplicationNode
     * whose children are ValueNodes containing the provided RealArrays.
     *
     * @param left the value of the left child Node
     * @param right the value of the right child Node
     */
    public MatrixMultiplicationNode(RealArray left, RealArray right) {
        this(new ValueNode(left), new ValueNode(right));
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
    public RealArray evaluate() {
        if (resultValue == null) {
            leftValue = left.evaluate();
            rightValue = right.evaluate();
            resultValue = leftValue.multiply(rightValue);
        }
        return resultValue;
    }

    @Override
    public boolean validImputDimensions() {
        return left.outDimensions().getN() == right.outDimensions().getM();
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
        ArrayList<Frame> list = new ArrayList<>();

        for (int i = 0; i < outDimensions().getM(); i++) {
            for (int j = 0; j < outDimensions().getN(); j++) {
                Row leftOperand = new Row(i, leftValue.getRow(i));
                Column rightOperand = new Column(j, rightValue.getColumn(j));
                Frame frame = new RowDotColumnFrame(leftOperand, rightOperand);
                list.add(frame);
            }
        }

        return new FrameSequence(list);
    }

}
