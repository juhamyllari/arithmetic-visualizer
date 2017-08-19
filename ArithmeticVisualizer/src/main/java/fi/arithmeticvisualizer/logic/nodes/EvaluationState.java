package fi.arithmeticvisualizer.logic.nodes;

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
