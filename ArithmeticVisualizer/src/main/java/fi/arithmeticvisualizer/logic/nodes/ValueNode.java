package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.DoubleArray;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.evaluation.RealArray;

/**
 * ValueNodes are the leaves in expression trees. A ValueNode contains an
 DoubleArray object. It performs no arithmetic operations.
 */
public class ValueNode extends Node {

    private final RealArray value;

    /**
     * Constructs a ValueNode with the provided RealArray as its value.
     *
     * @param value the value of the Node
     */
    public ValueNode(RealArray value) {
        this.value = value;
    }

    /**
     * Constructs a ValueNode with the provided array as its value.
     *
     * @param array the value of the Node
     */
    public ValueNode(double[][] array) {
        this(new DoubleArray(array));
    }

    @Override
    public RealArray evaluate() {
        return value;
    }

    @Override
    public Dimensions outDimensions() {
        return value.getDimensions();
    }

}
