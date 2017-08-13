package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.utils.BinaryOperation;
import fi.arithmeticvisualizer.logic.evaluation.Value;
import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinaryNode extends Node {
    
    private final Node left;
    private final Node right;
    private final BinaryOperation operation;

    public BinaryNode(Node left, Node right, BinaryOperation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }
    
    public char getSymbol() {
        return operation.getSymbol();
    }
    
    @Override
    public Value evaluate() throws WrongShapeException {
        return operation.getFunction().apply(left.evaluate(), right.evaluate());
    }

    @Override
    public String toString() {
        try {
            Value leftValue = left.evaluate();
            Value rightValue = right.evaluate();
            String leftArrayString = Arrays.deepToString(leftValue.getValue());
            String rightArrayString = Arrays.deepToString(rightValue.getValue()); 
            return leftArrayString + " " + operation.getSymbol() + " " + rightArrayString;
        } catch (WrongShapeException ex) {
            return "Invalid Node";
        }
    }
    
    

}
