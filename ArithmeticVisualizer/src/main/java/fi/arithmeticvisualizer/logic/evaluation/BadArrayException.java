package fi.arithmeticvisualizer.logic.evaluation;

/** 
 * An exception that indicates failure to create a valid array.
 */
public class BadArrayException extends Exception {

    /**
     * Constructs a BadArrayException with the specified message.
     * 
     * @param message
     */
    public BadArrayException(String message) {
        super(message);
    }
    
}
