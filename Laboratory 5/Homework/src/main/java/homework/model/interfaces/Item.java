package homework.model.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import homework.exceptions.DuplicateIDException;
import homework.exceptions.InvalidYearException;
import homework.model.base.Article;
import homework.model.base.Book;
import homework.model.base.Other;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = Id.NAME,
        property = "type")
@JsonSubTypes({
        @Type(value = Book.class, name = "book"),
        @Type(value = Article.class, name = "article"),
        @Type(value = Other.class, name = "other"),
})

public abstract class Item {
    private static final List<String> identifiers = new ArrayList<>();
    private String identifier;
    private String title;
    private String location;
    private String year;
    private String author;

    protected Item() {
    }

    protected Item(String title, String location, String year, String author, String identifier) {
        checkYear(Integer.valueOf(year));
        checkID(identifier);
        this.title = title;
        this.location = location;
        this.author = author;
    }

    public String getId() {
        return this.identifier;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLocation() {
        return this.location;
    }

    public String getYear() {
        return this.year;
    }

    public String getAuthor() {
        return this.author;
    }

    private void checkYear(Integer year) {
        try {
            if (year < 0 || year > Year.now().getValue() + 10)
                throw new InvalidYearException();
            this.year = year.toString();
        } catch (InvalidYearException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkID(String identifier) {
        try {
            if (!identifiers.isEmpty())
                for (String id : identifiers)
                    if (id.equals(identifier))
                        throw new DuplicateIDException();
            this.identifier = identifier;
            identifiers.add(identifier);
        } catch (DuplicateIDException e) {
            System.out.println(e.getMessage());
        }
    }
}
