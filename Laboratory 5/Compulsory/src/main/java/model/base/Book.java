package model.base;
import model.interfaces.Item;

public class Book extends Item {
    public Book(String id, String title, String location, Integer year, String author) {
        super(title, location, year.toString(), author, "book", id);
    }

    public Book(String id, String title, String location, Integer year) {
        super(title, location, year.toString(), "unknown", "book", id);
    }

    public Book(String id, String title, String location, String author) {
        super(title, location, "0", author, "book", id);
    }

    public Book(String id, String title, String location) {
        super(title, location, "0", "unknown", "book", id);
    }
}