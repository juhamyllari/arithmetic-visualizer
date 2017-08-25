package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.Frame;
import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.OperationPattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A BinaryNode is a Node that performs an operation on two operands. The
 * abstract class is extended by AdditionNode, SubtractionNode and
 * MultiplicationNode.
 */
public abstract class BinaryNode extends Node {

    public static final String SUBOPERATION_FORMAT = "%.1f";

    public enum EvaluationStyle {
        ELEMENTWISE,
        ROWWISE,
        COLUMNWISE
    }

    public enum FrameStringPattern {
        ROW_BY_COLUMN,
        ELEMENT_BY_ELEMENT
    }
    
    protected Node left;
    protected Node right;
    protected ArrayValue leftValue;
    protected ArrayValue rightValue;
    protected ArrayValue resultValue;

    public abstract Dimensions outDimensions();

    public abstract String getSymbol();

    public abstract ArrayValue evaluate();

    public abstract Node getLeft();

    public abstract Node getRight();

    public abstract boolean validImputDimensions();

    public abstract FrameSequence getFrameSequence(EvaluationStyle style);

    protected abstract OperationPattern getOperationPattern(EvaluationStyle style);
    
    protected abstract String frameString(FrameStringPattern pattern, int row, int column);
    
    public static BinaryNode createBinaryNode(ArrayValue left, ArrayValue right, String operator) {

        BinaryNode node = null;

        switch (operator) {
            case "+":
                node = new AdditionNode(left, right);
                break;
            case "-":
                node = new SubtractionNode(left, right);
                break;
            case "*":
                if (left.isScalar()) {
                    node = new LeftScalarMultiplicationNode(left, right);
                } else if (right.isScalar()) {
                    node = new RightScalarMultiplicationNode(left, right);
                } else {
                    node = new MatrixMultiplicationNode(left, right);
                }
        }

        return node;
    }

    protected static String dotTypeString(double[] left, double[] right, double result,
            String mapSymbol, String reduceSymbol) {
        
        String leftSide;
        String rightSide = formatDouble(result);
        
        leftSide = IntStream.range(0, left.length)
                .mapToObj(i -> {
                    double leftOperand = left[i];
                    double rightOperand = right[i];
                    if (rightOperand >= 0.0) {
                        return formatDouble(left[i]) + " " + mapSymbol + " " + formatDouble(right[i]);
                    } else {
                        return formatDouble(left[i]) + " " + mapSymbol + " (" + formatDouble(right[i]) + ")";
                    }
                })
                .collect(Collectors.joining(" " + reduceSymbol + " "));
        return leftSide + " = " + rightSide;
    }

    protected static String oneToOneString(double left, double right, double result, String symbol) {
        
        String leftSide;
        String rightSide = formatDouble(result);
        
        if (right >= 0.0) {
            leftSide = formatDouble(left) + " " + symbol + " " + formatDouble(right);
        } else {
            leftSide = formatDouble(left) + " " + symbol + " (" + formatDouble(right) + ")";
        }
        
        return leftSide + " = " + rightSide;
    }

    protected FrameSequence getFramesElementwise(Dimensions out, OperationPattern operationPattern, FrameStringPattern frameStringPattern) {
        
        int rows = out.getM();
        int columns = out.getN();
        int numberOfFrames = rows * columns;
        List<Frame> frames = new ArrayList<>();

        for (int i = 0; i < numberOfFrames; i++) {
            int row = i / columns;
            int column = i % columns;
            String frameString = frameString(frameStringPattern, row, column);
            frames.add(new Frame(row, column, frameString));
        }

        return new FrameSequence(frames, operationPattern);
    }
}
