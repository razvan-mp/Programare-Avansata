package exceptions;

public class DuplicateIDException extends Exception {
    public DuplicateIDException() {
        super("ID already exists in model.base.Catalog.");
    }
}
