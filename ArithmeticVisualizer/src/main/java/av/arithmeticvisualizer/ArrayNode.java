package av.arithmeticvisualizer;

public class ArrayNode extends UnaryNode {
    
    private final TensorValue value;

    public ArrayNode(double[][] value) throws WrongShapeException {
        this.value = new TensorValue(value);
    }

    public ArrayNode(double value) throws WrongShapeException {
        double[][] array = new double[1][1];
        array[0][0] = value;
        this.value = new TensorValue(array);
    }
    
    public TensorValue eval() {
        return value;
    }
    
}
