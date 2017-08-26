package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.Element;
import fi.arithmeticvisualizer.gui.ElementWithElement;
import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.Frame;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;

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
    public FrameSequence getFrameSequence(EvaluationStyle style) {
        evaluate();
        switch (style) {
            case ELEMENTWISE:
            default:
                return getSubopsElementwise();
        }
    }

    private FrameSequence getSubopsElementwise() {
        ArrayList<Frame> list = new ArrayList<>();
        
        for (int i = 0; i < outDimensions().getM(); i++) {
            for (int j = 0; j < outDimensions().getN(); j++) {
                Element leftOperand = new Element(i, j, leftValue.getElement(i, j));
                Element rightOperand = new Element(i, j, rightValue.getElement(i, j));
                DoubleBinaryOperator operator = (double left, double right) -> left + right;
                Frame frame = new ElementWithElement(operator, "+", leftOperand, rightOperand);
                list.add(frame);
            }
        }
        
        return new FrameSequence(list);
    }

}
