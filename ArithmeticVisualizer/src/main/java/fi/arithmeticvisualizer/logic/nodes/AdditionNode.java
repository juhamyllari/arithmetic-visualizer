package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.ArrayDrawingUtils;
import fi.arithmeticvisualizer.gui.GraphicalArray;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dims;
import static fi.arithmeticvisualizer.logic.utils.Utils.addArrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class AdditionNode extends BinaryNode {

    static final long SLEEPTIME = 200; // In milliseconds

    private Node left;
    private Node right;
    private Dims dims;

    public AdditionNode(Node left, Node right) {
        this.left = left;
        this.right = right;
        this.dims = left.outDims();
    }

    public AdditionNode(double[][] left, double[][] right) {
        this.left = new ValueNode(left);
        this.right = new ValueNode(right);
        this.dims = new Dims(left.length, left[0].length);
    }

    @Override
    public Dims outDims() {
        return dims;
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

}
