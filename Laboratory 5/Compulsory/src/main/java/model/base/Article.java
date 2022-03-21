package model.base;
import model.interfaces.Item;

public class Article extends Item {
    public Article(String id, String title, String location, Integer year, String author) {
        super(title, location, year.toString(), author, "article", id);
    }

    public Article(String id, String title, String location, Integer year) {
        super(title, location, year.toString(), "unknown", "article", id);
    }

    public Article(String id, String title, String location, String author) {
        super(title, location, "0", author, "article", id);
    }

    public Article(String id, String title, String location) {
        super(title, location, "0", "unknown", "article", id);
    }
}