package fi.arithmeticvisualizer.logic.evaluation;

/** 
 * An exception that indicates failure to create a valid array.
 */
public class BadArrayException extends Exception {

    public BadArrayException(String message) {
        super(message);
    }
    
}
