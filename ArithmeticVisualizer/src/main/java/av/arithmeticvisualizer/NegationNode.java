package av.arithmeticvisualizer;

public class NegationNode extends UnaryNode {

    private Node singleNode;

    public NegationNode(Node singleNode) {
        this.singleNode = singleNode;
    }
    
    @Override
    public TensorValue eval() throws WrongShapeException {
        return new TensorValue(Utils.negateArray(singleNode.eval().getValue()));
    }
    
}
