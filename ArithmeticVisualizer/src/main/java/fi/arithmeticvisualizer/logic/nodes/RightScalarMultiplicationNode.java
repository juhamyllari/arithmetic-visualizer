package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.visualization.ActivationPattern;
import java.util.ArrayList;

public class RightScalarMultiplicationNode extends BinaryNode {

    private Node left;
    private Node right;

    public RightScalarMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public RightScalarMultiplicationNode(ArrayValue left, ArrayValue right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
    }

    @Override
    public Dimensions outDims() {
        return left.outDimensions();
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public ArrayValue evaluate() {
        return left.evaluate().scalarMultiply(right.evaluate().getElement(0, 0));
    }

    @Override
    public Node getLeft() {
        return left;
    }

    @Override
    public Node getRight() {
        return right;
    }

    @Override
    public ActivationPattern getActivationPattern() {
        return ActivationPattern.RIGHTSCALARMULTIPLICATION;
    }

    @Override
    public boolean validImputDimensions() {
        return right.isScalar();
    }

    @Override
    public ArrayList<String> getSubOperationStrings() {

        double[][] leftArray = left.evaluate().getValue();
        double rightScalar = right.evaluate().getElement(0, 0);

        int m = outDims().getM();
        int n = outDims().getN();

        ArrayList<String> strings = new ArrayList<>();

        double rightOperand = rightScalar;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double leftOperand = leftArray[i][j];
                double result = leftOperand * rightOperand;
                String string;
                if (rightOperand >= 0.0) {
                    string = leftOperand + " * " + rightOperand + " = " + result;
                } else {
                    string = leftOperand + " * (" + rightOperand + ") = " + result;
                }
                strings.add(string);
            }
        }

        return strings;
    }

}
