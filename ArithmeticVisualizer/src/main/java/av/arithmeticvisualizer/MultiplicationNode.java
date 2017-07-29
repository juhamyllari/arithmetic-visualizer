package av.arithmeticvisualizer;

public class MultiplicationNode extends BinaryNode {

    public MultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public TensorValue eval() throws WrongShapeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
