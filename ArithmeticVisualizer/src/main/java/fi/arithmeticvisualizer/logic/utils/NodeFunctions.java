package fi.arithmeticvisualizer.logic.utils;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import java.util.HashMap;

public class NodeFunctions {

    @FunctionalInterface
    public interface CheckedUnaryFunction<T> {

        T apply(T t1) throws WrongShapeException;
    }

    @FunctionalInterface
    public interface CheckedBinaryFunction<T> {

        T apply(T t1, T t2) throws WrongShapeException;
    }



}
