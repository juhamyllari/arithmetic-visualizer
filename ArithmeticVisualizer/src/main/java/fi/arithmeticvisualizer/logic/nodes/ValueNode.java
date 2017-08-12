package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.Value;
import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;

public class ValueNode extends Node {

    private final Value value;

    public ValueNode(double[][] array) {
        this.value = new Value(array);
    }

    public ValueNode(double dbl) {
        double[][] array = new double[1][1];
        array[0][0] = dbl;
        this.value = new Value(array);
    }

    public Value evaluate() throws WrongShapeException {
        
        for (double[] row : value.getValue()) {
            if (row.length != value.getN()) {
                throw new WrongShapeException("Jagged arrays are not allowed");
            }
        }
        
        return value;
    }

}
