package av.arithmeticvisualizer;

public class AdditionNode extends BinaryNode {

    public AdditionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public TensorValue evaluate() throws WrongShapeException {
        TensorValue leftValue = left.evaluate();
        TensorValue rightValue = right.evaluate();

        if (leftValue.getM() != rightValue.getM() || leftValue.getN() != rightValue.getN()) {
            throw new WrongShapeException(
                    "Cannot multiply arrays of dimensions "
                    + leftValue.dimsString()
                    + " and "
                    + rightValue.dimsString()
                    + "."
            );
        }

        return new TensorValue(Utils.addArrays(left.evaluate().getValue(), right.evaluate().getValue()));
    }

}
