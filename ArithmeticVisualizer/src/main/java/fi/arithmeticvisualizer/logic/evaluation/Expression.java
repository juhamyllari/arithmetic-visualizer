package fi.arithmeticvisualizer.logic.evaluation;

import fi.arithmeticvisualizer.logic.utils.WrongShapeException;
import fi.arithmeticvisualizer.logic.nodes.Node;

public class Expression {

    private Node root;

    public Expression(Node root) {
        this.root = root;
    }

    public ArrayValue evaluate() throws WrongShapeException {
        return root.evaluate();
    }

}
