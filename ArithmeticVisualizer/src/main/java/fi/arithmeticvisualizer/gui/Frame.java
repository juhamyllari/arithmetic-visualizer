package fi.arithmeticvisualizer.gui;

abstract public class Frame {
    
    @FunctionalInterface
    protected interface MaskSetter {
        void apply(BooleanMask mask);
    }
    
    private SubOperand left;
    private SubOperand right;
    
    public abstract MaskSetter getLeftActivation();
    public abstract MaskSetter getRightActivation();
    public abstract MaskSetter getResultActivation();
    public abstract MaskSetter getResultShown();
    public abstract String getSubOperationString();
    
}
