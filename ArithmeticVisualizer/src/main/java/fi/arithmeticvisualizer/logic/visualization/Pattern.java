package fi.arithmeticvisualizer.logic.visualization;

public class Pattern {
    
    String pattern;
    int row;
    int column;

    public Pattern(String pattern, int m, int n) {
        this.pattern = pattern;
        this.row = m;
        this.column = n;
    }

    public String getPattern() {
        return pattern;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
