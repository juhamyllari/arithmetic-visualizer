package fi.arithmeticvisualizer.logic.nodes;

import fi.arithmeticvisualizer.gui.GraphicalArray;
import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.utils.Dims;
import fi.arithmeticvisualizer.logic.utils.WrongShapeException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;

public class SubtractionNode extends BinaryNode {

    private Node left;
    private Node right;
    private Dims dims;

    public SubtractionNode(Node left, Node right) throws WrongShapeException {
        this.left = left;
        this.right = right;
        this.dims = left.outDims();
    }

    public SubtractionNode(double[][] left, double[][] right) {
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
    public Dims outDims() {
        return left.outDims();
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public ArrayValue evaluate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ActivationPattern getActivationPattern() {
        return ActivationPattern.SUBTRACTION;
    }

    @Override
    public boolean validImputDims() {
        return left.outDims().equals(right.outDims());
    }

}
