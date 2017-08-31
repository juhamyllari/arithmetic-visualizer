package fi.arithmeticvisualizer.logic.suboperands;

/**
 * An Element is a scalar valued SubOperand.
 */
public class Element extends SubOperand {
    
    private int m;
    private int n;
    private double value;

    /**
     * Constructs an Element.
     * 
     * @param m the row index
     * @param n the column index
     * @param value the scalar value
     */
    public Element(int m, int n, double value) {
        this.m = m;
        this.n = n;
        this.value = value;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public double getValue() {
        return value;
    }
    
}
