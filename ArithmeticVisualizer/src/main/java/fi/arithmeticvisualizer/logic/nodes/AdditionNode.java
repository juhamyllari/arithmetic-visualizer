package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dimensions;
import static fi.arithmeticvisualizer.logic.utils.Utils.addArrays;
import java.util.ArrayList;

/**
 * An AdditionNode is a BinaryNode that performs array addition.
 */
public class AdditionNode extends BinaryNode {

    private final Node left;
    private final Node right;

    public AdditionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public AdditionNode(double[][] left, double[][] right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
    }

    @Override
    public Dimensions outDims() {
        return left.outDims();
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public ArrayValue evaluate() {
        double[][] leftArray = left.evaluate().getValue();
        double[][] rightArray = right.evaluate().getValue();
        return new ArrayValue(addArrays(leftArray, rightArray));
    }

    @Override
    public ActivationPattern getActivationPattern() {
        return ActivationPattern.ADDITION;
    }

    @Override
    public boolean validImputDims() {
        return left.outDims().equals(right.outDims());
    }

    @Override
    public ArrayList<String> getSubOpStrings() {
        
        double[][] leftArray = left.evaluate().getValue();
        double[][] rightArray = right.evaluate().getValue();
        
        int m = outDims().getM();
        int n = outDims().getN();
        
        ArrayList<String> strings = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double leftOperand = leftArray[i][j];
                double rightOperand = rightArray[i][j];
                double result = leftOperand + rightOperand;
                String string;
                if (rightOperand >= 0.0) {
                    string = leftOperand + " + " + rightOperand + " = " + result;
                } else {
                    string = leftOperand + " + (" + rightOperand + ") = " + result;
                }
                strings.add(string);
            }
        }
        
        return strings;
    }

}
