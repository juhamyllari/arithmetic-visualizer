package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.logic.evaluation.ArrayValue;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;

public class RowDotColumn extends Frame {

    private Row left;
    private Column right;
    private int rowIndex;
    private int columnIndex;

    public RowDotColumn(Row left, Column right, int rowIndex, int columnIndex) {
        this.left = left;
        this.right = right;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public double evaluate() {
        return ArrayValue.dotVectors(left.getRowVector(), right.getColumnVector());
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

}
