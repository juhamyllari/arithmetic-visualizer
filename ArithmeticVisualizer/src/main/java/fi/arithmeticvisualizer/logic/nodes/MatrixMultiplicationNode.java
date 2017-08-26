package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.Column;
import fi.arithmeticvisualizer.gui.ElementWithElement;
import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.Row;
import fi.arithmeticvisualizer.gui.RowDotColumn;
import fi.arithmeticvisualizer.gui.SubOperand;
import fi.arithmeticvisualizer.gui.Frame;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

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
                Frame frame = new RowDotColumn(leftOperand, rightOperand, i, j);
                list.add(frame);
            }
        }

        return new FrameSequence(list);
    }

}
