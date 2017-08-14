package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.WrongShapeException;

public class ValueNode extends Node {

    private final ArrayValue value;

    public ValueNode(double[][] array) {
        this.value = new ArrayValue(array);
    }

    public ValueNode(double dbl) {
        double[][] array = new double[1][1];
        array[0][0] = dbl;
        this.value = new ArrayValue(array);
    }

    public ArrayValue evaluate() throws WrongShapeException {
        
        for (double[] row : value.getValue()) {
            if (row.length != value.getN()) {
                throw new WrongShapeException("Jagged arrays are not allowed");
            }
        }
        
        return value;
    }

}
