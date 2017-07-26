package av.arithmeticvisualizer;

public class ArrayNode extends UnaryNode {
    
    private final double[][] value;

    public ArrayNode(double[][] value) {
        this.value = value;
    }

    public ArrayNode(double d) {
        this.value = new double[1][1];
        this.value[0][0] = d;
    }
    
    public double[][] eval() {
        return value;
    }
    
}
