package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dims;

public abstract class BinaryNode {
    
    public abstract Dims outDims();
    public abstract String getSymbol();
    public abstract ArrayValue evaluate();
    public abstract Node getLeft();
    public abstract Node getRight();
    public abstract ActivationPattern getActivationPattern();
    public abstract boolean validImputDims();
    
    public static BinaryNode createBinaryNode(ArrayValue left, ArrayValue right, String operator) {
        
        BinaryNode node = null;
        double[][] leftArray = left.getValue();
        double[][] rightArray = right.getValue();
        
        switch (operator) {
            case "+": node = new AdditionNode(leftArray, rightArray);
            break;
            case "-": node = new SubtractionNode(leftArray, rightArray);
            break;
            case "*": node = new MultiplicationNode(leftArray, rightArray);
        }
        
        return node;
    }
    
    public static BinaryNode createBinaryNode(double[][] left, double[][] right, String operator) {
        return createBinaryNode(new ArrayValue(left), new ArrayValue(right), operator);
    } 
    
}
