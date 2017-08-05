package av.arithmeticvisualizer;

import java.util.function.BinaryOperator;

public class BinaryNode extends Node {
    
    private final Node left;
    private final Node right;
    private final BinaryOperator<Value> function;

    public BinaryNode(Node left, Node right, BinaryOperator<Value> function) {
        this.left = left;
        this.right = right;
        this.function = function;
    }
    
    @Override
    public Value evaluate() {
        return function.apply(left.evaluate(), right.evaluate());
    }
    
}
