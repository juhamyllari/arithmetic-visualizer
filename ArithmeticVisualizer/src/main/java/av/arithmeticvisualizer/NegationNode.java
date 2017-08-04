package av.arithmeticvisualizer;

public class NegationNode extends UnaryNode {

    private final Node singleNode;

    public NegationNode(Node singleNode) {
        this.singleNode = singleNode;
    }
    
    @Override
    public TensorValue evaluate() throws WrongShapeException {
        return new TensorValue(Utils.negateArray(singleNode.evaluate().getValue()));
    }
    
}
