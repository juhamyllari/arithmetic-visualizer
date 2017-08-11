package fi.arithmeticvisualizer.logic;

import fi.arithmeticvisualizer.logic.NodeFunctions.CheckedBinaryFunction;

public class BinaryNode extends Node {
    
    private final Node left;
    private final Node right;
    private final CheckedBinaryFunction<Value> operation;

    public BinaryNode(Node left, Node right, CheckedBinaryFunction<Value> operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }
    
    @Override
    public Value evaluate() throws WrongShapeException {
        return operation.apply(left.evaluate(), right.evaluate());
    }
    
}
