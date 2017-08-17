package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.ArrayDrawingUtils;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dims;
import static fi.arithmeticvisualizer.logic.utils.Utils.multiplyArrays;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class MultiplicationNode extends BinaryNode {

    private Node left;
    private Node right;

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
    public boolean validImputDims() {
        if (left.isScalar() || right.isScalar()) {
            return true;
        } else {
            return left.outDims().getN() == right.outDims().getM();
        }
    }

}
