package fi.arithmeticvisualizer.logic.visualization;

/**
 * A SubOperation object represents a single frame in a visualization animation.
 * It contains a BooleanMask to indicate which elements of the result array
 * are to be displayed, and a String representation of the suboperation (e.g.
 * a single dot product in the case of matrix multiplication).
 */
public class SubOperation {

    private final BooleanMask showResult;
    private final String subOpString;

    public SubOperation(BooleanMask showResult, String subOpString) {
        this.showResult = showResult;
        this.subOpString = subOpString;
    }

    public BooleanMask getShow() {
        return showResult;
    }

    public String getSubOpString() {
        return subOpString;
    }

}
