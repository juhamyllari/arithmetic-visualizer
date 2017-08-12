package fi.arithmeticvisualizer.logic.evaluation;

import fi.arithmeticvisualizer.logic.nodes.Node;

public class Expression {

    private Node root;

    public Expression(Node root) {
        this.root = root;
    }

    public Value evaluate() throws WrongShapeException {
        return root.evaluate();
    }

}
