package fi.arithmeticvisualizer.gui;

import fi.arithmeticvisualizer.gui.FramePattern.ArrayPattern;

/**
 * A MaskState object contains the information necessary to
 * generate a Boolean mask whose dimensions are known.
 */
public class MaskState {
    
    ArrayPattern pattern;
    int row;
    int column;

    public MaskState(ArrayPattern pattern, int m, int n) {
        this.pattern = pattern;
        this.row = m;
        this.column = n;
    }

    public ArrayPattern getPattern() {
        return pattern;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
