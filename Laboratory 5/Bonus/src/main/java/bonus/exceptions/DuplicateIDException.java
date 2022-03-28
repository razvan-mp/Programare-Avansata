package bonus.exceptions;

/**
 * thrown in case an ID is duplicated in catalog
 */
public class DuplicateIDException extends Exception {
    public DuplicateIDException() {
        super("ID already exists in Catalog.");
    }
}
