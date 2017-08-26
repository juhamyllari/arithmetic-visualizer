package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.Frame;
import fi.arithmeticvisualizer.gui.FrameSequence;
import fi.arithmeticvisualizer.gui.FramePattern;
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

    /**
     * Specifies the String format of scalar (double) values in Frame Strings.
     */
    public static final String FRAME_STRING_DOUBLE_FORMAT = "%.1f";

    /**
     * Evaluation styles represent different ways of evaluating the same
     * expression.
     */
    public enum EvaluationStyle {
        ELEMENTWISE,
        ROWWISE,
        COLUMNWISE
    }

    /**
     * A FrameStringPattern specifies how the Frame Strings are generated.
     */
    public enum FrameStringPattern {
        ROW_BY_COLUMN,
        ELEMENT_BY_ELEMENT
    }

    protected Node left;
    protected Node right;
    protected ArrayValue leftValue;
    protected ArrayValue rightValue;
    protected ArrayValue resultValue;

    @Override
    public abstract Dimensions outDimensions();

    /**
     * Returns the operation symbol of the node. The same symbol may represent
     * different operations. E.g. "*" represents matrix-matrix, scalar-matrix
     * and matrix-scalar multiplication.
     *
     * @return the operation symbol
     */
    public abstract String getSymbol();

    @Override
    public abstract ArrayValue evaluate();

    /**
     * Returns the left Node of the BinaryNode.
     *
     * @return the left Node
     */
    public abstract Node getLeft();

    /**
     * Returns the right Node of the BinaryNode.
     *
     * @return the right Node
     */
    public abstract Node getRight();

    /**
     * Returns {@code true} if and only if the left and right
     * Node have compatible output dimensions with respect to
     * the Node's operation.
     * 
     * @return {@code true} if and only if input Node dimensions
     * are valid
     */
    public abstract boolean validImputDimensions();

    /**
     * Returns a FrameSequence specifying the frames of an animation
     * of the operation represented by the Node.
     * 
     * @param style the EvaluationStyle chosen
     * @return the FrameSequence of the operation
     */
    public abstract FrameSequence getFrameSequence(EvaluationStyle style);

    protected abstract FramePattern getOperationPattern(EvaluationStyle style);

    protected abstract String frameString(FrameStringPattern pattern, int row, int column);

    /**
     * Returns a new BinaryNode whose type is determined by the specified symbol
     * and whose children are ValueNodes containing the specified ArrayValues.
     * 
     * @param left the value of the left child 
     * @param right the value of the right child 
     * @param operator the symbol specifying the operation
     * @return the new BinaryNode
     */
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

    protected FrameSequence getFramesElementwise(Dimensions out, FramePattern operationPattern, FrameStringPattern frameStringPattern) {

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
