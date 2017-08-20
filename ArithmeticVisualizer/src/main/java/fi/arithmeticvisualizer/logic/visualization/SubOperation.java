package fi.arithmeticvisualizer.logic.visualization;

/**
 * A SubOperation object represents a single frame in a visualization
 * animation. It contains BooleanMasks to indicate active elements
 * in each array, a BooleanMask to indicate which elements of the
 * result array are to be displayed, and a String representation of
 * the suboperation (e.g. a single dot product in the case of matrix
 * multiplication).
 */
public class SubOperation {
    
    private final BooleanMask leftActivation;
    private final BooleanMask rightActivation;
    private final BooleanMask resultActivation;
    private final BooleanMask showResult;
    private final String subOpString;

    public SubOperation(BooleanMask leftActivation, BooleanMask rightActivation, BooleanMask resultActivation, BooleanMask showResult, String subOpString) {
        int m = leftActivation.getMask().length;
        int n = leftActivation.getMask()[0].length;
        this.leftActivation = leftActivation;
        this.rightActivation = rightActivation;
        this.resultActivation = resultActivation;
        this.showResult = showResult;
        this.subOpString = subOpString;
    }

    public BooleanMask getLeftActivation() {
        return leftActivation;
    }

    public BooleanMask getRightActivation() {
        return rightActivation;
    }

    public BooleanMask getResultActivation() {
        return resultActivation;
    }

    public BooleanMask getShow() {
        return showResult;
    }

    public String getSubOpString() {
        return subOpString;
    }
 
}
