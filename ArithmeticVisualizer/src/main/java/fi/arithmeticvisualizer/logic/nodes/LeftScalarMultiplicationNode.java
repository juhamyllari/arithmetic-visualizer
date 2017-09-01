package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.suboperands.Element;
import fi.arithmeticvisualizer.logic.frames.ElementWithElementFrame;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.frames.FrameSequence;
import fi.arithmeticvisualizer.logic.frames.Frame;
import fi.arithmeticvisualizer.logic.evaluation.RealArray;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

/**
 * A LeftScalarMultiplicationNode is a BinaryNode that performs scalar
 * multiplication from the left.
 */
public class LeftScalarMultiplicationNode extends BinaryNode {

    /**
     * Constructs a LeftScalarMultiplicationNode.
     *
     * @param left the left child Node
     * @param right the right child Node
     */
    public LeftScalarMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    /**
     * A convenience method for constructing a LeftScalarMultiplicationNode
     * whose children are ValueNodes containing the provided RealArrays.
     *
     * @param left the value of the left child Node
     * @param right the value of the right child Node
     */
    public LeftScalarMultiplicationNode(RealArray left, RealArray right) {
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
        return left.isScalar();
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
                Element leftOperand = new Element(0, 0, leftValue.getElement(0, 0));
                Element rightOperand = new Element(i, j, rightValue.getElement(i, j));
                DoubleBinaryOperator operator = (double left, double right) -> left * right;
                Frame frame = new ElementWithElementFrame(operator, "*", leftOperand, rightOperand, i, j);
                list.add(frame);
            }
        }

        return new FrameSequence(list);
    }

}
