package fi.arithmeticvisualizer.logic.evaluation;

import fi.arithmeticvisualizer.logic.nodes.Node;

public class Expression {

    private final Node root;

    public Expression(Node root) {
        this.root = root;
    }

    public ArrayValue evaluate() {
        return root.evaluate();
    }

}
