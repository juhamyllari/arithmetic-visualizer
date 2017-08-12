package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.utils.NodeFunctions.CheckedUnaryFunction;
import fi.arithmeticvisualizer.logic.evaluation.Value;
import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;

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
