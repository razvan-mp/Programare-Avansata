package homework.exceptions;

/**
 * thrown in case an ID is invalid
 */
public class InvalidIDException extends Exception {
    public InvalidIDException(String message) {
        super(message);
    }
}
