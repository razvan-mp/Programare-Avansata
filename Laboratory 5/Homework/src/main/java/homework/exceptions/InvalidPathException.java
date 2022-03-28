package homework.exceptions;

/**
 * thrown in case a path is invalid
 */
public class InvalidPathException extends Exception {
    public InvalidPathException(String message) {
        super(message);
    }
}
