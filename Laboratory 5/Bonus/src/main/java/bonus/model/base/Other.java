package bonus.model.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import bonus.model.interfaces.Item;

/**
 * class that models an item of a type other than Book or Article
 */
public class Other extends Item {
    public Other() {
    }

    @JsonCreator
    public Other(@JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("location") String location,
                 @JsonProperty("year") Integer year, @JsonProperty("author") String author) {
        super(title, location, year.toString(), author, id);
    }

    public Other(String id, String title, String location, Integer year) {
        super(title, location, year.toString(), "unknown", id);
    }

    public Other(String id, String title, String location, String author) {
        super(title, location, "0", author, id);
    }

    public Other(String id, String title, String location) {
        super(title, location, "0", "unknown", id);
    }
}
