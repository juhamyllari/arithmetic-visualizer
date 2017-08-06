package av.arithmeticvisualizer;

import java.util.function.BinaryOperator;

public class NodeFunctions {
    
    @FunctionalInterface
    public interface CheckedUnaryFunction<T> {
        T apply(T t1) throws WrongShapeException;
    }
    
    @FunctionalInterface
    public interface CheckedBinaryFunction<T> {
        T apply(T t1, T t2) throws WrongShapeException;
    }

    static CheckedBinaryFunction<Value> multiply = (Value left, Value right) -> new Value(Utils.multiplyArrays(left.getValue(), right.getValue()));

    static CheckedBinaryFunction<Value> add = (Value left, Value right) -> new Value(Utils.addArrays(left.getValue(), right.getValue()));

    static CheckedUnaryFunction<Value> negate = (Value val) -> new Value(Utils.scalarMultiply(-1, val.getValue()));
}
