package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;

/**
 * A RowDotColumn is a Frame representing a dot product of a row vector and a
 * column vector.
 */
public class RowDotColumn extends Frame {

    private final Row left;
    private final Column right;
    private final int rowIndex;
    private final int columnIndex;

    /**
     * Constructs a RowDotColumn Frame.
     * 
     * @param left the left Row operand
     * @param right the right Column operand
     * @param rowIndex the index of the row argument
     * @param columnIndex the index of the column argument
     */
    public RowDotColumn(Row left, Column right, int rowIndex, int columnIndex) {
        this.left = left;
        this.right = right;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    @Override
    public MaskSetter getLeftActivation() {
        return (BooleanMask mask) -> mask.setRow(rowIndex);
    }

    @Override
    public MaskSetter getRightActivation() {
        return (BooleanMask mask) -> mask.setColumn(columnIndex);
    }

    @Override
    public MaskSetter getResultActivation() {
        return (BooleanMask mask) -> mask.setElement(rowIndex, columnIndex);
    }

    @Override
    public MaskSetter getResultShown() {
        return (BooleanMask mask) -> mask.setUpToByRow(rowIndex, columnIndex);
    }

    @Override
    public String getSubOperationString() {
        return BinaryNode.dotTypeString(left.getRowVector(),
                right.getColumnVector(), evaluate(), "*", "+");
    }

    private double evaluate() {
        return ArrayValue.dotVectors(left.getRowVector(), right.getColumnVector());
    }
}
