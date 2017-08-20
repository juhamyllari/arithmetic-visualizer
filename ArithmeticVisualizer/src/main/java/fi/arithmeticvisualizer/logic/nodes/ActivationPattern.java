package fi.arithmeticvisualizer.logic.nodes;

/**
 * An ActivationPattern indicates which elements of each array are active.
 * Activated elements are highlighted in the visualization animation frame.
 * They correspond to the array elements which contribute to that suboperation.
 */
public class ActivationPattern {
    
    public static final ActivationPattern ADDITION = new ActivationPattern("element", "element", "element");
    public static final ActivationPattern SUBTRACTION = ADDITION;
    public static final ActivationPattern LEFTSCALARMULTIPLICATION = new ActivationPattern("all", "element", "element");
    public static final ActivationPattern RIGHTSCALARMULTIPLICATION = new ActivationPattern("element", "all", "element");
    public static final ActivationPattern MATRIXMULTIPLICATION = new ActivationPattern("row", "column", "element");
    
    String leftPattern;
    String rightPattern;
    String resultPattern;

    public ActivationPattern(String leftPattern, String rightPattern, String resultPattern) {
        this.leftPattern = leftPattern;
        this.rightPattern = rightPattern;
        this.resultPattern = resultPattern;
    }

    public String getLeftPattern() {
        return leftPattern;
    }

    public String getRightPattern() {
        return rightPattern;
    }

    public String getResultPattern() {
        return resultPattern;
    }

}
