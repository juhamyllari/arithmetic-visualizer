package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.frames.FrameSequence;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.evaluation.RealArray;
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
     * Returns a FrameSequence corresponding to the operation evaluated in the
     * designated style.
     * 
     * @param style the desired EvaluationStyle
     * @return a FrameSequence representing the operations
     */
    public abstract FrameSequence getFrameSequence(EvaluationStyle style);

    /**
     * Evaluation styles represent different ways of evaluating the same
     * expression.
     */
    public enum EvaluationStyle {
        ELEMENTWISE,
        ROWWISE,
        COLUMNWISE
    }

    protected Node left;
    protected Node right;
    protected RealArray leftValue;
    protected RealArray rightValue;
    protected RealArray resultValue;

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
    public abstract RealArray evaluate();

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
     * Returns {@code true} if and only if the left and right Node have
     * compatible output dimensions with respect to the Node's operation.
     *
     * @return {@code true} if and only if input Node dimensions are valid
     */
    public abstract boolean validImputDimensions();

    /**
     * Returns a new BinaryNode whose type is determined by the specified symbol
     * and whose children are ValueNodes containing the specified RealArrays.
     *
     * @param left the value of the left child
     * @param right the value of the right child
     * @param operator the symbol specifying the operation
     * @return the new BinaryNode
     */
    public static BinaryNode createBinaryNode(RealArray left, RealArray right, String operator) {

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

    /**
     * Returns a String representing a single dot product type calculation.
     * A symbol must be provided for the pairwise operations (e.g. "*") and
     * the reduction operation (e.g. "+").
     * 
     * @param left the left vector operand
     * @param right the right vector operand
     * @param result the scalar result of the calculation
     * @param mapSymbol the pairwise operation symbol
     * @param reduceSymbol the reduction symbol
     * @return a String representing the calculation
     */
    public static String dotTypeString(double[] left, double[] right, double result,
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

    /**
     * Returns a String representing a single calculation of two scalar
     * operands. A String symbol (e.g. "*" or "+") for the operation 
     * must be provided.
     * 
     * @param left the left scalar operand
     * @param right the right scalar operand
     * @param result the scalar result
     * @param symbol the operation symbol
     * @return a String representing the calculation
     */
    public static String oneToOneString(double left, double right, double result, String symbol) {

        String leftSide;
        String rightSide = formatDouble(result);

        if (right >= 0.0) {
            leftSide = formatDouble(left) + " " + symbol + " " + formatDouble(right);
        } else {
            leftSide = formatDouble(left) + " " + symbol + " (" + formatDouble(right) + ")";
        }

        return leftSide + " = " + rightSide;
    }

}
