package av.arithmeticvisualizer;

public class AdditionNode extends BinaryNode {

    public AdditionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public TensorValue eval() throws WrongShapeException {
        TensorValue leftValue = left.eval();
        TensorValue rightValue = right.eval();

        if (leftValue.getM() != rightValue.getM() || leftValue.getN() != rightValue.getN()) {
            throw new WrongShapeException(
                    "Cannot multiply arrays of dimensions "
                    + leftValue.dimsString()
                    + " and "
                    + rightValue.dimsString()
                    + "."
            );
        }

        return new TensorValue(Utils.addArrays(left.eval().getValue(), right.eval().getValue()));
    }

}
