package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dims;
import fi.arithmeticvisualizer.logic.utils.Utils;
import fi.arithmeticvisualizer.logic.utils.WrongShapeException;

public class SubtractionNode extends BinaryNode {

    private final Node left;
    private final Node right;

    public SubtractionNode(Node left, Node right) throws WrongShapeException {
        this.left = left;
        this.right = right;
    }

    public SubtractionNode(double[][] left, double[][] right) {
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
    public Dims outDims() {
        return left.outDims();
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public ArrayValue evaluate() {
        return new ArrayValue(Utils.subtractArrays(left.evaluate().getValue(), right.evaluate().getValue()));
    }

    @Override
    public ActivationPattern getActivationPattern() {
        return ActivationPattern.SUBTRACTION;
    }

    @Override
    public boolean validImputDims() {
        return left.outDims().equals(right.outDims());
    }

}
