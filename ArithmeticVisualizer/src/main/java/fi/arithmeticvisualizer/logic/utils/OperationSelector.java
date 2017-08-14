package fi.arithmeticvisualizer.logic.utils;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.NodeFunctions.CheckedBinaryFunction;
import java.util.HashMap;

public class OperationSelector {

    public static CheckedBinaryFunction<ArrayValue> multiply = (ArrayValue left, ArrayValue right) -> new ArrayValue(Utils.multiplyArrays(left.getValue(), right.getValue()));
    
    public static CheckedBinaryFunction<ArrayValue> add = (ArrayValue left, ArrayValue right) -> new ArrayValue(Utils.addArrays(left.getValue(), right.getValue()));
    
    public static CheckedBinaryFunction<ArrayValue> subtract = (ArrayValue left, ArrayValue right) -> new ArrayValue(Utils.addArrays(left.getValue(), Utils.scalarMultiply(-1.0, right.getValue())));

    public static BinaryOperation multiplication = new BinaryOperation(multiply, "*");

    public static BinaryOperation addition = new BinaryOperation(add, "+");

    public static BinaryOperation subtraction = new BinaryOperation(subtract, "-");
    
    private HashMap<String, BinaryOperation> map;

    public OperationSelector() {

        this.map = new HashMap<>();
        map.put("+", addition);
        map.put("-", subtraction);
        map.put("*", multiplication);
    }

    public BinaryOperation getOperation(String symbol) {

        return map.get(symbol);
        
    }
}
