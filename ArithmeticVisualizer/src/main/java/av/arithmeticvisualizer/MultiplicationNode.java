package av.arithmeticvisualizer;

public class MultiplicationNode extends BinaryNode {

    public MultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public TensorValue eval() throws WrongShapeException {
        TensorValue leftValue = left.eval();
        TensorValue rightValue = right.eval();
        
        double[][] result;
        
        if (leftValue.isScalar()) {
            result = Utils.scalarMultiply(leftValue.getValue()[0][0], rightValue.getValue());
            return new TensorValue(result);
        } else if (rightValue.isScalar()) {
            result = Utils.scalarMultiply(rightValue.getValue()[0][0], leftValue.getValue());
            return new TensorValue(result);
        }
        
        if (leftValue.getN() != rightValue.getM()) {
            throw new WrongShapeException("");
        }
        
        result = new double[leftValue.getM()][rightValue.getN()];
                
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
