package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.visualization.ActivationPattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A MultiplicationNode is a BinaryNode that performs multiplication. If one
 * more operand is a scalar, scalar multiplication is performed. Otherwise
 * matrix multiplication is performed.
 */
public class MatrixMultiplicationNode extends BinaryNode {

    final private Node left;
    final private Node right;

    public MatrixMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public MatrixMultiplicationNode(ArrayValue left, ArrayValue right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public Dimensions outDimensions() {
        return new Dimensions(left.outDimensions().getM(), right.outDimensions().getN());
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public ArrayValue evaluate() {
        return left.evaluate().multiply(right.evaluate());
    }

    public ActivationPattern getActivationPattern() {
        return ActivationPattern.MATRIXMULTIPLICATION;
    }

    @Override
    public ArrayList<String> getSubOperationStrings() {

        ArrayValue leftValue = left.evaluate();
        ArrayValue rightValue = right.evaluate();

        int m = outDimensions().getM();
        int n = outDimensions().getN();

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double[] leftVector = leftValue.getRow(i);
                double[] rightVector = rightValue.getColumn(j);
                String string= subOpString(leftVector, rightVector);
                strings.add(string);
            }
        }

        return strings;
    }

    private String subOpString(double[] leftVector, double[] rightVector) {

        int vectorLength = leftVector.length;
        String string = IntStream
                .range(0, vectorLength)
                .mapToObj(i -> "(" + formatDouble(leftVector[i]) + " * " + formatDouble(rightVector[i]) + ")")
                .collect(Collectors.joining(" + "));

        double subOpResult = ArrayValue.dotVectors(leftVector, rightVector);
        return string + " = " + formatDouble(subOpResult);
    }

    @Override
    public boolean validImputDimensions() {
        return left.outDimensions().getN() == right.outDimensions().getM();
    }

}
