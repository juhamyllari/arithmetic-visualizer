package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.gui.MaskState.Pattern;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.ALL;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.COLUMN;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.ELEMENT;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.ROW;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.UPTOBYROW;

/**
 * An ActivationPattern indicates which elements of each array are active.
 * Activated elements are highlighted in the visualization animation frame.
 * They correspond to the array elements which contribute to that suboperation.
 */
public class OperationPattern {
    
    public static final OperationPattern ADDITIONELEMENTWISE = new OperationPattern(ELEMENT, ELEMENT, ELEMENT, UPTOBYROW);
    public static final OperationPattern SUBTRACTIONELEMENTWISE = ADDITIONELEMENTWISE;
    public static final OperationPattern LEFTSCALARMULTIPLICATIONELEMENTWISE = new OperationPattern(ALL, ELEMENT, ELEMENT, UPTOBYROW);
    public static final OperationPattern RIGHTSCALARMULTIPLICATIONELEMENTWISE = new OperationPattern(ELEMENT, ALL, ELEMENT, UPTOBYROW);
    public static final OperationPattern MATRIXMULTIPLICATIONELEMENTWISE = new OperationPattern(ROW, COLUMN, ELEMENT, UPTOBYROW);
    
    Pattern leftPattern;
    Pattern rightPattern;
    Pattern resultPattern;
    Pattern shownPattern;

    public OperationPattern(Pattern leftPattern, Pattern rightPattern, Pattern resultPattern, Pattern shownPattern) {
        this.leftPattern = leftPattern;
        this.rightPattern = rightPattern;
        this.resultPattern = resultPattern;
        this.shownPattern = shownPattern;
    }
    
    public Pattern getLeftPattern() {
        return leftPattern;
    }

    public Pattern getRightPattern() {
        return rightPattern;
    }

    public Pattern getResultPattern() {
        return resultPattern;
    }

}
