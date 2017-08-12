package fi.arithmeticvisualizer.logic.utils;

import fi.arithmeticvisualizer.logic.evaluation.Value;
import fi.arithmeticvisualizer.logic.evaluation.WrongShapeException;
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

    public static CheckedBinaryFunction<Value> multiply = (Value left, Value right) -> new Value(Utils.multiplyArrays(left.getValue(), right.getValue()));

    public static CheckedBinaryFunction<Value> add = (Value left, Value right) -> new Value(Utils.addArrays(left.getValue(), right.getValue()));

    public static CheckedUnaryFunction<Value> negate = (Value val) -> new Value(Utils.scalarMultiply(-1, val.getValue()));
}
