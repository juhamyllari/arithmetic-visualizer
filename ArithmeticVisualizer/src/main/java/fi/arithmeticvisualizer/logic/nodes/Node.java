package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.Value;
import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;

public abstract class Node {
    
    public abstract Value evaluate() throws WrongShapeException;
    
}
