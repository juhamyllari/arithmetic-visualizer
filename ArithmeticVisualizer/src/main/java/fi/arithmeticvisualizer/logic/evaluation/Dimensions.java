package fi.arithmeticvisualizer.logic.evaluation;

/**
 * A Dimensions object indicates the input or output dimensions of a node.
 */
public class Dimensions {
    
    private int m;
    private int n;

    /**
     * Constructs a Dimensions instance representing the dimensions
     * of a array with m rows and n columns.
     * 
     * @param m the number of rows
     * @param n the number of columns
     */
    public Dimensions(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        return "(" + m + ", " + n + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dimensions other = (Dimensions) obj;
        if (this.m != other.m) {
            return false;
        }
        if (this.n != other.n) {
            return false;
        }
        return true;
    }
    
}
