package homework.model.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import homework.model.interfaces.Item;

/**
 * class that models an item of type Article
 */
public class Article extends Item {
    public Article() {
    }

    @JsonCreator
    public Article(@JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("location") String location,
                   @JsonProperty("year") Integer year, @JsonProperty("author") String author) {
        super(title, location, year.toString(), author, id);
    }

    public Article(String id, String title, String location, Integer year) {
        super(title, location, year.toString(), "unknown", id);
    }

    public Article(String id, String title, String location, String author) {
        super(title, location, "0", author, id);
    }

    public Article(String id, String title, String location) {
        super(title, location, "0", "unknown", id);
    }
}