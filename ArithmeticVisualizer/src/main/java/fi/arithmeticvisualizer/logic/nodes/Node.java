package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.WrongShapeException;

public abstract class Node {
    
    public abstract ArrayValue evaluate() throws WrongShapeException;
    
}
