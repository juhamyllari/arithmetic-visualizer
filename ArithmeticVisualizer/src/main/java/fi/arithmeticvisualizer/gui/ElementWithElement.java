package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import java.util.function.DoubleBinaryOperator;

public class ElementWithElement extends Frame {

    private DoubleBinaryOperator operator;
    private String symbol;
    private Element left;
    private Element right;
    private int resultRow;
    private int resultColumn;
    private boolean showRowwise;

    public ElementWithElement(DoubleBinaryOperator operator, String symbol, Element left, Element right, int resultRow, int resultColumn) {
        this.operator = operator;
        this.symbol = symbol;
        this.left = left;
        this.right = right;
        this.resultRow = resultRow;
        this.resultColumn = resultColumn;
        this.showRowwise = true;
    }

    public ElementWithElement(DoubleBinaryOperator operator, String symbol, Element left, Element right) {
        this(operator, symbol, left, right, left.getM(), left.getN());
    }

    public void setShowRowwise(boolean rowwise) {
        showRowwise = rowwise;
    }

    @Override
    public MaskSetter getLeftActivation() {
        return (BooleanMask mask) -> mask.setElement(left.getM(), left.getN());
    }

    @Override
    public MaskSetter getRightActivation() {
        return (BooleanMask mask) -> mask.setElement(right.getM(), right.getN());
    }

    @Override
    public MaskSetter getResultActivation() {
        return (BooleanMask mask) -> mask.setElement(resultRow, resultColumn);
    }

    @Override
    public MaskSetter getResultShown() {
        if (showRowwise) {
            return (BooleanMask mask) -> mask.setUpToByRow(resultRow, resultColumn);
        } else {
            return (BooleanMask mask) -> mask.setUpToByColumn(resultRow, resultColumn);
        }
    }

    @Override
    public String getSubOperationString() {
        return BinaryNode.oneToOneString(left.getValue(), right.getValue(),
                operator.applyAsDouble(left.getValue(), right.getValue()), symbol);
    }

}
