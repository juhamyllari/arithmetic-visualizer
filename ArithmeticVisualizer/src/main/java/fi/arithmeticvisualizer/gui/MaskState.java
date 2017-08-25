package fi.arithmeticvisualizer.gui;

/**
 * A MaskState object contains the information necessary to
 * generate a Boolean mask whose dimensions are known.
 */
public class MaskState {
    
    public enum Pattern {
        ROW, COLUMN, ELEMENT, ALL,
        UPTOBYROW, UPTOBYCOLUMN
    }
    
    Pattern pattern;
    int row;
    int column;

    public MaskState(Pattern pattern, int m, int n) {
        this.pattern = pattern;
        this.row = m;
        this.column = n;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
