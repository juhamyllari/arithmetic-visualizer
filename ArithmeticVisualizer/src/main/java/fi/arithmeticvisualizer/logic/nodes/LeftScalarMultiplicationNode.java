package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import fi.arithmeticvisualizer.logic.visualization.ActivationPattern;
import java.util.ArrayList;

public class LeftScalarMultiplicationNode extends BinaryNode {

    private Node left;
    private Node right;

    public LeftScalarMultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
    
    public LeftScalarMultiplicationNode(ArrayValue left, ArrayValue right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
    }

    @Override
    public Dimensions outDims() {
        return right.outDimensions();
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public ArrayValue evaluate() {
        return right.evaluate().scalarMultiply(left.evaluate().getElement(0, 0));
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
        return ActivationPattern.LEFTSCALARMULTIPLICATION;
    }

    @Override
    public boolean validImputDimensions() {
        return left.isScalar();
    }

    @Override
    public ArrayList<String> getSubOperationStrings() {

        double leftScalar = left.evaluate().getElement(0, 0);
        double[][] rightArray = right.evaluate().getValue();

        int m = outDims().getM();
        int n = outDims().getN();

        ArrayList<String> strings = new ArrayList<>();

        double leftOperand = leftScalar;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double rightOperand = rightArray[i][j];
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
