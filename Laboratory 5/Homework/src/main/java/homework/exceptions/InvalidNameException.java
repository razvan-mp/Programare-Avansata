package homework.exceptions;

/**
 * versatile exception with custom message, used for incorrect commands
 */
public class InvalidNameException extends Exception {
    public InvalidNameException(String message) {
        super(message);
    }
}
