package model.base;
import model.interfaces.Item;

public class Other extends Item {
    public Other(String id, String title, String location, Integer year, String author) {
        super(title, location, year.toString(), author, "other", id);
    }
}
