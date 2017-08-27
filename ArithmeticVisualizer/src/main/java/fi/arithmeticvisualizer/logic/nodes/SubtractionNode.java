package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.Element;
import fi.arithmeticvisualizer.gui.ElementWithElement;
import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.Frame;
import fi.arithmeticvisualizer.logic.evaluation.DoubleArray;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.evaluation.RealArray;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

/**
 * A SubtractionNode is a BinaryNode that performs array addition.
 */
public class SubtractionNode extends BinaryNode {

    /**
     * Constructs a SubtractionNode.
     *
     * @param left the value of the left child Node
     * @param right the value of the right child Node
     */
    public SubtractionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    /**
     * A convenience method for constructing a SubtractionNode whose children
     * are ValueNodes containing the provided RealArrays.
     *
     * @param left the value of the left child Node
     * @param right the value of the right child Node
     */
    public SubtractionNode(RealArray left, RealArray right) {
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
    public RealArray evaluate() {
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
                Element leftOperand = new Element(i, j, leftValue.getElement(i, j));
                Element rightOperand = new Element(i, j, rightValue.getElement(i, j));
                DoubleBinaryOperator operator = (double left, double right) -> left - right;
                Frame frame = new ElementWithElement(operator, "-", leftOperand, rightOperand);
                list.add(frame);
            }
        }

        return new FrameSequence(list);
    }

}
