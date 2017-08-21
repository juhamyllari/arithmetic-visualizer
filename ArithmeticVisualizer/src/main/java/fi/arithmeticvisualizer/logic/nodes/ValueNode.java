package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;

/**
 * ValueNodes are the leaves in expression trees.
 * A ValueNode contains an ArrayValue object.
 * It performs no arithmetic operations.
 */
public class ValueNode extends Node {

    private final ArrayValue value;

    public ValueNode(double[][] array) {
        this.value = new ArrayValue(array);
    }
    
    public ValueNode(ArrayValue value) {
        this.value = value;
    }

    public ValueNode(double dbl) {
        this.value = new ArrayValue(dbl);
    }

    public ArrayValue evaluate() {
        return value;
    }

    @Override
    public Dimensions outDimensions() {
        return value.getDimensions();
    }

}
