package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dims;

public abstract class Node {
    
    public abstract ArrayValue evaluate();
    public abstract Dims outDims();
    
    public boolean isScalar() {
        return (outDims().getM() == 1) && (outDims().getM() == 1);
    }
    
}
