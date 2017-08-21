package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.visualization.ActivationPattern;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.evaluation.Dimensions;
import java.util.ArrayList;

/**
 * A SubtractionNode is a BinaryNode that performs array addition.
 */
public class SubtractionNode extends BinaryNode {

    private final Node left;
    private final Node right;

    public SubtractionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public SubtractionNode(ArrayValue left, ArrayValue right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
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
    public Dimensions outDimensions() {
        return left.outDimensions();
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public ArrayValue evaluate() {
        return left.evaluate().subtractArray(right.evaluate());
    }

    @Override
    public ActivationPattern getActivationPattern() {
        return ActivationPattern.SUBTRACTION;
    }

    @Override
    public boolean validImputDimensions() {
        return left.outDimensions().equals(right.outDimensions());
    }

    @Override
    public ArrayList<String> getSubOperationStrings() {

        double[][] leftArray = left.evaluate().getValue();
        double[][] rightArray = right.evaluate().getValue();

        int m = outDimensions().getM();
        int n = outDimensions().getN();

        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double leftOperand = leftArray[i][j];
                double rightOperand = rightArray[i][j];
                double result = leftOperand - rightOperand;
                String string;
                if (rightOperand >= 0) {
                    string = formatDouble(leftOperand) + " - " + formatDouble(rightOperand) + " = " + formatDouble(result);
                } else {
                    string = formatDouble(leftOperand) + " - (" + formatDouble(rightOperand) + ") = " + formatDouble(result);
                }
                strings.add(string);
            }
        }

        return strings;
    }

}
