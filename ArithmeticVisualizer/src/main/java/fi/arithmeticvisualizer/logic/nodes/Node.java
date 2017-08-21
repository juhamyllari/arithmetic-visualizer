package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;

/**
 * A Node object represents a node in an arithmetic expression.
 * Node is an abstract class; it is currently extended by ValueNode
 * and by the abstract class BinaryNode, which is in turn extended by
 * AdditionNode, SubtractionNode and MultiplicationNode.
 */
public abstract class Node {

    public abstract ArrayValue evaluate();

    public abstract Dimensions outDimensions();

    public boolean isScalar() {
        return (outDimensions().getM() == 1) && (outDimensions().getN() == 1);
    }

}
