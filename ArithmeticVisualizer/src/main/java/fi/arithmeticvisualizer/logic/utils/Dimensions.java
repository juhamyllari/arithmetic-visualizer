package fi.arithmeticvisualizer.logic.utils;

public class Dimensions {
    
    private int m;
    private int n;

    public Dimensions(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public Dimensions(double[][] array) {
        this.m = array.length;
        this.n = array[0].length;
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
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.m;
        hash = 37 * hash + this.n;
        return hash;
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
