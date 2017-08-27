package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.nodes.BinaryNode;
import java.util.function.DoubleBinaryOperator;

/**
 * An ElementWithElementFrame is a Frame representing an operation on two scalar
 valued operands.
 */
public class ElementWithElementFrame extends Frame {

    private DoubleBinaryOperator operator;
    private String symbol;
    private Element left;
    private Element right;
    private int resultRow;
    private int resultColumn;
    private boolean showRowwise;

    /**
     * Constructs an ElementWithElement instance.
     * 
     * @param operator the scalar valued binary operator
     * @param symbol the operator symbol
     * @param left the left element
     * @param right the right element
     * @param resultRow the row index of the resulting scalar
     * @param resultColumn the column index of the resulting scalar
     */
    public ElementWithElementFrame(DoubleBinaryOperator operator, String symbol, Element left, Element right, int resultRow, int resultColumn) {
        this.operator = operator;
        this.symbol = symbol;
        this.left = left;
        this.right = right;
        this.resultRow = resultRow;
        this.resultColumn = resultColumn;
        this.showRowwise = true;
    }

    /**
     * A convenience constructor applicable when the resulting scalar's row and
     * column indices are the same as those of the left operand.
     * 
     * @param operator the scalar valued binary operator
     * @param symbol the operator symbol
     * @param left the left element
     * @param right the right element
     */
    public ElementWithElementFrame(DoubleBinaryOperator operator, String symbol, Element left, Element right) {
        this(operator, symbol, left, right, left.getM(), left.getN());
    }

    /**
     * Set whether the result array is revealed in row-major or column-major
     * order. The default order is row-major.
     *
     * @param rowMajor set order to row-major
     */
    public void setShowRowwise(boolean rowMajor) {
        showRowwise = rowMajor;
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
