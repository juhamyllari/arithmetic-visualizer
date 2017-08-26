package fi.arithmeticvisualizer.gui;

abstract public class Frame {
    
    /**
     * Represents a function that modifies a {@code BooleanMask} and has no
     * return value.
     */
    @FunctionalInterface
    public interface MaskSetter {
        void accept(BooleanMask mask);
    }
    
    private SubOperand left;
    private SubOperand right;
    
    public abstract MaskSetter getLeftActivation();
    public abstract MaskSetter getRightActivation();
    public abstract MaskSetter getResultActivation();
    public abstract MaskSetter getResultShown();
    public abstract String getSubOperationString();
    
}
