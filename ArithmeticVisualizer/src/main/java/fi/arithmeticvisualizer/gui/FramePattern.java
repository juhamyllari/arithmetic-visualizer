package fi.arithmeticvisualizer.gui;

import static fi.arithmeticvisualizer.gui.FramePattern.ArrayPattern.*;

/**
 * An FramePattern indicates which elements of each array are active and which
 * elements of the result array are shown. Activated elements are highlighted in
 * the visualization animation frame. They correspond to the array elements
 * which contribute to that suboperation.
 */
public class FramePattern {

    public enum ArrayPattern {
        ROW, COLUMN, ELEMENT, ALL,
        UPTOBYROW, UPTOBYCOLUMN
    }

    public static final FramePattern ADDITIONELEMENTWISE = new FramePattern(ELEMENT, ELEMENT, ELEMENT, UPTOBYROW);
    public static final FramePattern SUBTRACTIONELEMENTWISE = ADDITIONELEMENTWISE;
    public static final FramePattern LEFTSCALARMULTIPLICATIONELEMENTWISE = new FramePattern(ALL, ELEMENT, ELEMENT, UPTOBYROW);
    public static final FramePattern RIGHTSCALARMULTIPLICATIONELEMENTWISE = new FramePattern(ELEMENT, ALL, ELEMENT, UPTOBYROW);
    public static final FramePattern MATRIXMULTIPLICATIONELEMENTWISE = new FramePattern(ROW, COLUMN, ELEMENT, UPTOBYROW);

    ArrayPattern leftPattern;
    ArrayPattern rightPattern;
    ArrayPattern resultPattern;
    ArrayPattern shownPattern;

    public FramePattern(ArrayPattern leftPattern, ArrayPattern rightPattern, ArrayPattern resultPattern, ArrayPattern shownPattern) {
        this.leftPattern = leftPattern;
        this.rightPattern = rightPattern;
        this.resultPattern = resultPattern;
        this.shownPattern = shownPattern;
    }

    public ArrayPattern getLeftPattern() {
        return leftPattern;
    }

    public ArrayPattern getRightPattern() {
        return rightPattern;
    }

    public ArrayPattern getResultPattern() {
        return resultPattern;
    }

}
