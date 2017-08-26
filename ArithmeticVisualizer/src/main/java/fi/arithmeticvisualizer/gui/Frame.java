package fi.arithmeticvisualizer.gui;

/**
 * A Frame represents a single frame in a visualization animation. Equivalently,
 * a Frame represents an operation on two SubOperands. The Frame determines
 * which elements of each array are active (and which elements of the resulting
 * array are displayed) in each frame of an animation.
 */
abstract public class Frame {

    /**
     * Represents a function that modifies a {@code BooleanMask} and has no
     * return value.
     */
    @FunctionalInterface
    public interface MaskSetter {

        /**
         * Modifies a {@code BooleanMask} and returns no value.
         * 
         * @param mask the {@code BooleanMask} to modify
         */
        void accept(BooleanMask mask);
    }

    private SubOperand left;
    private SubOperand right;

    /**
     * Returns a function to set the activation of the left GraphicArray.
     *
     * @return a MaskSetter function
     */
    public abstract MaskSetter getLeftActivation();

    /**
     * Returns a function to set the activation of the right GraphicArray.
     *
     * @return a MaskSetter function
     */
    public abstract MaskSetter getRightActivation();

    /**
     * Returns a function to set the activation of the result GraphicArray.
     *
     * @return a MaskSetter function
     */
    public abstract MaskSetter getResultActivation();

    /**
     * Returns a function to set which elements of the result array are to be
     * displayed.
     *
     * @return a MaskSetter function
     */
    public abstract MaskSetter getResultShown();

    /**
     * Returns a String representing the operation taking place in the Frame.
     *
     * @return a String representation of the operation
     */
    public abstract String getSubOperationString();

}
