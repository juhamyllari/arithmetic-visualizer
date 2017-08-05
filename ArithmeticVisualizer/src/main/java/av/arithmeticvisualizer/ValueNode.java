package av.arithmeticvisualizer;

public class ValueNode extends UnaryNode {
    
    private final Value value;

    public ValueNode(double[][] value) {
        this.value = new Value(value);
    }

    public ValueNode(double value) {
        double[][] array = new double[1][1];
        array[0][0] = value;
        this.value = new Value(array);
    }
    
    public Value evaluate() {
        return value;
    }
    
}
