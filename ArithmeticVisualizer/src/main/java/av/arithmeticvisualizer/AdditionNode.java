package av.arithmeticvisualizer;

public class AdditionNode extends BinaryNode {

    public AdditionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double[][] eval() throws DimensionMismatchException {
        return Utils.addArrays(left.eval(), right.eval());
    }

}
