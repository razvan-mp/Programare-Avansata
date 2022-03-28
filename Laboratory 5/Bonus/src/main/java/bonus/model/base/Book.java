package bonus.model.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import bonus.model.interfaces.Item;

public class Book extends Item {
    public Book() {
        //dummy ctr
    }
    @JsonCreator
    public Book(@JsonProperty("id") String id, @JsonProperty("title") String title, @JsonProperty("location") String location,
                @JsonProperty("year") Integer year, @JsonProperty("author") String author) {
        super(title, location, year.toString(), author, id);
    }
}