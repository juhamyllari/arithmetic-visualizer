package fi.arithmeticvisualizer.logic.utils;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.NodeFunctions.CheckedBinaryFunction;

public class BinaryOperation {

    private CheckedBinaryFunction<ArrayValue> function;
    private String symbol;

    public BinaryOperation(CheckedBinaryFunction<ArrayValue> function, String symbol) {
        this.function = function;
        this.symbol = symbol;
    }
    
    public BinaryOperation(){
    }

    public CheckedBinaryFunction<ArrayValue> getFunction() {
        return function;
    }

    public String getSymbol() {
        return symbol;
    }
    
}
