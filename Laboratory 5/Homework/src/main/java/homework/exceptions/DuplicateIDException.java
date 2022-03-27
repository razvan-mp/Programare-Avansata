package homework.exceptions;

public class DuplicateIDException extends Exception {
    public DuplicateIDException() {
        super("ID already exists in Catalog.");
    }
}
