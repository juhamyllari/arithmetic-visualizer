package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dimensions;

/**
 * A Node object represents a node in an arithmetic expression.
 * Node is an abstract class; it is currently extended by ValueNode
 * and by the abstract class BinaryNode, which is in turn extended by
 * AdditionNode, SubtractionNode and MultiplicationNode.
 */
public abstract class Node {

    public abstract ArrayValue evaluate();

    public abstract Dimensions outDims();

    public boolean isScalar() {
        return (outDims().getM() == 1) && (outDims().getM() == 1);
    }

}
