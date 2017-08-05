package av.arithmeticvisualizer;

public class NegationNode extends UnaryNode {

    private final Node singleNode;

    public NegationNode(Node singleNode) {
        this.singleNode = singleNode;
    }
    
    @Override
    public Value evaluate() {
        return new Value(Utils.negateArray(singleNode.evaluate().getValue()));
    }
    
}
