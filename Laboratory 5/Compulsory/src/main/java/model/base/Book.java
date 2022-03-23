package model.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.interfaces.Item;

public class Book extends Item {
    public Book() {

    }

    @JsonCreator
    public Book(@JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("location") String location,
                @JsonProperty("year") Integer year, @JsonProperty("author") String author) {
        super(title, location, year.toString(), author, id);
    }

    public Book(String id, String title, String location, Integer year) {
        super(title, location, year.toString(), "unknown", id);
    }

    public Book(String id, String title, String location, String author) {
        super(title, location, "0", author, id);
    }

    public Book(String id, String title, String location) {
        super(title, location, "0", "unknown", id);
    }
}