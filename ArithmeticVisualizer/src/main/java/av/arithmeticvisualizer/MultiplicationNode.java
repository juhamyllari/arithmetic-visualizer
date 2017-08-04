package av.arithmeticvisualizer;

public class MultiplicationNode extends BinaryNode {

    public MultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public TensorValue evaluate() throws WrongShapeException {
        TensorValue leftValue = left.evaluate();
        TensorValue rightValue = right.evaluate();

        double[][] result;

        if (leftValue.isScalar()) {
            result = Utils.scalarMultiply(leftValue.getValue()[0][0], rightValue.getValue());
            return new TensorValue(result);
        } else if (rightValue.isScalar()) {
            result = Utils.scalarMultiply(rightValue.getValue()[0][0], leftValue.getValue());
            return new TensorValue(result);
        }

        if (leftValue.getN() != rightValue.getM()) {
            throw new WrongShapeException(
                    "Cannot multiply arrays of dimensions "
                    + leftValue.dimsString()
                    + " and "
                    + rightValue.dimsString()
                    + "."
            );
        }

        result = new double[leftValue.getM()][rightValue.getN()];

        for (int i = 0; i < leftValue.getM(); i++) {
            for (int j = 0; j < rightValue.getN(); j++) {
                result[i][j] = Utils.dotProduct(leftValue.getValue(),
                        rightValue.getValue(),
                        i, j);
            }
        }

        return new TensorValue(result);
    }

}
