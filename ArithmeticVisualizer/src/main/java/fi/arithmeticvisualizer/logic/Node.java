package fi.arithmeticvisualizer.logic;

public abstract class Node {
    
    public abstract Value evaluate() throws WrongShapeException;
    
}
