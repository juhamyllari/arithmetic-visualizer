package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dims;
import static fi.arithmeticvisualizer.logic.utils.Utils.addArrays;

public class AdditionNode extends BinaryNode {

    private final Node left;
    private final Node right;

    public AdditionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public AdditionNode(double[][] left, double[][] right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
    }

    @Override
    public Dims outDims() {
        return left.outDims();
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
        double[][] leftArray = left.evaluate().getValue();
        double[][] rightArray = right.evaluate().getValue();
        return new ArrayValue(addArrays(leftArray, rightArray));
    }

    @Override
    public ActivationPattern getActivationPattern() {
        return ActivationPattern.ADDITION;
    }

    @Override
    public boolean validImputDims() {
        return left.outDims().equals(right.outDims());
    }

}
