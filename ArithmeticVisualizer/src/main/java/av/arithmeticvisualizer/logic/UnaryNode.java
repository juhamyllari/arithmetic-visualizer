package av.arithmeticvisualizer.logic;

import av.arithmeticvisualizer.logic.NodeFunctions.CheckedUnaryFunction;

public class UnaryNode extends Node {

    private CheckedUnaryFunction<Value> operation;
    private Node node;

    public UnaryNode(CheckedUnaryFunction<Value> operation, Node node) {
        this.operation = operation;
        this.node = node;
    }

    @Override
    public Value evaluate() throws WrongShapeException {
        return operation.apply(node.evaluate());
    }
}
