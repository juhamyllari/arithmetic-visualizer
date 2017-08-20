package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dimensions;
import java.util.ArrayList;

/**
 * A BinaryNode is a Node that performs an operation on two operands.
 * The abstract class is extended by AdditionNode, SubtractionNode
 * and MultiplicationNode.
 */
public abstract class BinaryNode {

    @FunctionalInterface
    public interface PartialOpStringMaker {
        String apply(double[][] left, double[][] right, int m, int n);
    }

    public abstract Dimensions outDims();

    public abstract String getSymbol();

    public abstract ArrayValue evaluate();

    public abstract Node getLeft();

    public abstract Node getRight();

    public abstract ActivationPattern getActivationPattern();

    public abstract boolean validImputDims();
    
    public abstract ArrayList<String> getSubOperationStrings();

    public static BinaryNode createBinaryNode(ArrayValue left, ArrayValue right, String operator) {

        BinaryNode node = null;
        double[][] leftArray = left.getValue();
        double[][] rightArray = right.getValue();

        switch (operator) {
            case "+":
                node = new AdditionNode(leftArray, rightArray);
                break;
            case "-":
                node = new SubtractionNode(leftArray, rightArray);
                break;
            case "*":
                node = new MultiplicationNode(leftArray, rightArray);
        }

        return node;
    }

}
