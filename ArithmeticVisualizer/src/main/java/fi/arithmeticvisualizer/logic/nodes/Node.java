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

    /**
     * Returns the output dimensions of the node.
     * 
     * @return the output dimensions of the node
     */
    public abstract Dimensions outDimensions();

    /**
     * Returns {@code true} if and only if the output dimensions
     * of the node are (1, 1).
     * 
     * @return {@code true} if and only if the output dimensions
     * of the node are (1, 1).
     */
    public boolean isScalar() {
        return (outDimensions().getM() == 1) && (outDimensions().getN() == 1);
    }
    
    /**
     * Returns the specified scalar (double) as a string.
     * The format is specified in the constant
     * BinaryNode.FRAME_STRING_DOUBLE_FORMAT.
     * 
     * @param d the scalar to be formatted
     * @return a String representation of the scalar
     */
    public static String formatDouble(double d) {
        return String.format(BinaryNode.FRAME_STRING_DOUBLE_FORMAT, d);
    }

}
