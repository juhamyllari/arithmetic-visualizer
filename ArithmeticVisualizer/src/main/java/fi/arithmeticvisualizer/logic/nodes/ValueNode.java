package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;

/**
 * ValueNodes are the leaves in expression trees. A ValueNode contains an
 * ArrayValue object. It performs no arithmetic operations.
 */
public class ValueNode extends Node {

    private final ArrayValue value;

    /**
     * Constructs a ValueNode with the provided ArrayValue as its value.
     *
     * @param value the value of the Node
     */
    public ValueNode(ArrayValue value) {
        this.value = value;
    }

    /**
     * Constructs a ValueNode with the provided array as its value.
     *
     * @param array the value of the Node
     */
    public ValueNode(double[][] array) {
        this(new ArrayValue(array));
    }

    @Override
    public ArrayValue evaluate() {
        return value;
    }

    @Override
    public Dimensions outDimensions() {
        return value.getDimensions();
    }

}
