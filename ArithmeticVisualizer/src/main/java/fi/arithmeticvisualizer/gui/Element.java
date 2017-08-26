package fi.arithmeticvisualizer.gui;

public class Element extends SubOperand {
    
    private int m;
    private int n;
    private double value;

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
