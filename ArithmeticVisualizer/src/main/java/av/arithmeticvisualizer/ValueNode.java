package av.arithmeticvisualizer;

public class ValueNode extends UnaryNode {
    
    private final TensorValue value;

    public ValueNode(double[][] value) {
        this.value = new TensorValue(value);
    }

    public ValueNode(double value) {
        double[][] array = new double[1][1];
        array[0][0] = value;
        this.value = new TensorValue(array);
    }
    
    public TensorValue evaluate() {
        return value;
    }
    
}
