package av.arithmeticvisualizer;

import java.util.function.BinaryOperator;

public class NodeFunctions {

    static BinaryOperator<Value> multiply = (Value left, Value right) -> new Value(Utils.multiplyArrays(left.getValue(), right.getValue()));

    static BinaryOperator<Value> add = (Value left, Value right) -> new Value(Utils.addArrays(left.getValue(), right.getValue()));

}
