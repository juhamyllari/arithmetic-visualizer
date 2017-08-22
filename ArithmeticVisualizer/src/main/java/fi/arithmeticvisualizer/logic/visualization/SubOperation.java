package fi.arithmeticvisualizer.logic.visualization;

/**
 * A SubOperation object represents a single frame in a visualization animation.
 * It contains BooleanMasks to indicate active elements in each array, a
 * BooleanMask to indicate which elements of the result array are to be
 * displayed, and a String representation of the suboperation (e.g. a single dot
 * product in the case of matrix multiplication).
 */
public class SubOperation {

    private final BooleanMask showResult;
    private final String subOpString;
    private final Pattern leftPattern;
    private final Pattern rightPattern;
    private final Pattern resultPattern;

    public SubOperation(BooleanMask showResult, String subOpString, Pattern leftPattern, Pattern rightPattern, Pattern resultPattern) {
        this.showResult = showResult;
        this.subOpString = subOpString;
        this.leftPattern = leftPattern;
        this.rightPattern = rightPattern;
        this.resultPattern = resultPattern;
    }

    public BooleanMask getShow() {
        return showResult;
    }

    public String getSubOpString() {
        return subOpString;
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
