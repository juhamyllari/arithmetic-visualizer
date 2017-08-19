package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dims;
import fi.arithmeticvisualizer.logic.utils.Utils;
import static fi.arithmeticvisualizer.logic.utils.Utils.multiplyArrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A MultiplicationNode is a BinaryNode that performs multiplication.
 * If one more operand is a scalar, scalar multiplication is performed.
 * Otherwise matrix multiplication is performed. 
 */
public class MultiplicationNode extends BinaryNode {

    final private Node left;
    final private Node right;

    public MultiplicationNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public MultiplicationNode(double[][] left, double[][] right) {
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
    public Dims outDims() {
        if (left.isScalar()) {
            return right.outDims();
        } else if (right.isScalar()) {
            return left.outDims();
        } else {
            return new Dims(left.outDims().getM(), right.outDims().getN());
        }
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public ArrayValue evaluate() {
        return new ArrayValue(multiplyArrays(left.evaluate().getValue(), right.evaluate().getValue()));
    }

    public ActivationPattern getActivationPattern() {
        if (left.isScalar()) {
            return ActivationPattern.LEFTSCALARMULTIPLICATION;
        } else if (right.isScalar()) {
            return ActivationPattern.RIGHTSCALARMULTIPLICATION;
        } else {
            return ActivationPattern.MATRIXMULTIPLICATION;
        }
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
                double[] leftVector = Utils.getArrayRow(leftArray, i);
                double[] rightVector = Utils.getArrayColumn(rightArray, j);
                
                String string = subOpString(leftVector, rightVector);
                strings.add(string);
            }
        }
        
        return strings;
    }
    
    private String subOpString(double[] leftVector, double[] rightVector) {
        
        int vectorLength = leftVector.length;
        String string = IntStream
                        .range(0, vectorLength)
                        .mapToObj(i -> "(" + leftVector[i] + " * " + rightVector[i] + ")")
                        .collect(Collectors.joining(" + "));
        
        double subOpResult = Utils.dotVectors(leftVector, rightVector);
        
        return string + " = " + Double.toString(subOpResult);
    }

    @Override
    public boolean validImputDims() {
        if (left.isScalar() || right.isScalar()) {
            return true;
        } else {
            return left.outDims().getN() == right.outDims().getM();
        }
    }

}
