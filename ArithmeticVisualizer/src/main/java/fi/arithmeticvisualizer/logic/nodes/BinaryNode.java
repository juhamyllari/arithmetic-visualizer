package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.visualization.ActivationPattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.visualization.GraphicArray;
import java.util.ArrayList;

/**
 * A BinaryNode is a Node that performs an operation on two operands. The
 * abstract class is extended by AdditionNode, SubtractionNode and
 * MultiplicationNode.
 */
public abstract class BinaryNode extends Node {
    
    public static final String SUBOPFORMAT = "%.1f";

    public abstract Dimensions outDimensions();

    public abstract String getSymbol();

    public abstract ArrayValue evaluate();

    public abstract Node getLeft();

    public abstract Node getRight();

    public abstract ActivationPattern getActivationPattern();

    public abstract boolean validImputDimensions();

    public abstract ArrayList<String> getSubOperationStrings();
    
    public static BinaryNode createBinaryNode(ArrayValue left, ArrayValue right, String operator) {

        BinaryNode node = null;

        switch (operator) {
            case "+":
                node = new AdditionNode(left, right);
                break;
            case "-":
                node = new SubtractionNode(left, right);
                break;
            case "*":
                if (left.isScalar()) {
                    node = new LeftScalarMultiplicationNode(left, right);
                } else if (right.isScalar()) {
                    node = new RightScalarMultiplicationNode(left, right);
                } else {
                    node = new MatrixMultiplicationNode(left, right);
                }
        }

        return node;
    }
    
}
