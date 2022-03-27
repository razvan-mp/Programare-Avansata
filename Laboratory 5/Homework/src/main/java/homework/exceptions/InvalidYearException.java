package homework.exceptions;

public class InvalidYearException extends Exception {
    public InvalidYearException() {
        super("Year must not be negative or too far into the future. (publications that will be made later\n than five years from now will be added at their respective time.)");
    }
}
