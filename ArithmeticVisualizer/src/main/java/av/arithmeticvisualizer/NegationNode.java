package av.arithmeticvisualizer;

public class NegationNode extends UnaryNode {

    private Node singleNode;

    public NegationNode(Node singleNode) {
        this.singleNode = singleNode;
    }
    
    @Override
    public double[][] eval() throws DimensionMismatchException {
        return Utils.negateArray(singleNode.eval());
    }
    
}
