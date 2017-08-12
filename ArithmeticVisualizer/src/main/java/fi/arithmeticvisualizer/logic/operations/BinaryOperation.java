package fi.arithmeticvisualizer.logic.operations;

import fi.arithmeticvisualizer.logic.evaluation.Value;
import fi.arithmeticvisualizer.logic.utils.NodeFunctions.CheckedBinaryFunction;

public class BinaryOperation {

    private CheckedBinaryFunction<Value> function;
    private char symbol;

    public BinaryOperation(CheckedBinaryFunction<Value> function, char symbol) {
        this.function = function;
        this.symbol = symbol;
    }

    public CheckedBinaryFunction<Value> getFunction() {
        return function;
    }

    public char getSymbol() {
        return symbol;
    }

}
