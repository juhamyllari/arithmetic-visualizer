package fi.arithmeticvisualizer.logic.nodes;

/**
 * An EvaluationState represents a single frame in a visualization
 * animation. It contains BooleanMasks to indicate active elements
 * in each array, a BooleanMask to indicate which elements of the
 * result array are to be displayed, and a String representation
 * of the suboperation (i.e. the calculation displayed in a single frame).
 */
public class EvaluationState {
    
    private final BooleanMask leftActivation;
    private final BooleanMask rightActivation;
    private final BooleanMask resultActivation;
    private final BooleanMask showResult;
    private final String subOpString;

    public EvaluationState(BooleanMask leftActivation, BooleanMask rightActivation, BooleanMask resultActivation, BooleanMask showResult, String subOpString) {
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
