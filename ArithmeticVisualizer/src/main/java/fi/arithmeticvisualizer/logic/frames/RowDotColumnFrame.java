package fi.arithmeticvisualizer.logic.frames;

import fi.arithmeticvisualizer.logic.suboperands.Column;
import fi.arithmeticvisualizer.logic.suboperands.Row;
import static fi.arithmeticvisualizer.logic.evaluation.RealArray.dotVectors;
import fi.arithmeticvisualizer.logic.nodes.BinaryNode;

/**
 * A RowDotColumnFrame is a Frame representing a dot product of a row vector and a
 column vector.
 */
public class RowDotColumnFrame implements Frame {

    private final Row left;
    private final Column right;
    private final int rowIndex;
    private final int columnIndex;

    /**
     * Constructs a RowDotColumn Frame.
     * 
     * @param left the left Row operand
     * @param right the right Column operand
     */
    public RowDotColumnFrame(Row left, Column right) {
        this.left = left;
        this.right = right;
        this.rowIndex = left.getRowIndex();
        this.columnIndex = right.getColumnIndex();
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
        return dotVectors(left.getRowVector(), right.getColumnVector());
    }
}
