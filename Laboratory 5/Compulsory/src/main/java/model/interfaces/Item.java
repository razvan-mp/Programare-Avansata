package model.interfaces;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import model.base.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import exceptions.DuplicateIDException;
import exceptions.InvalidYearException;

@JsonTypeInfo(use = Id.NAME,
        property = "type")
@JsonSubTypes({
        @Type(value = Book.class, name = "book"),
        @Type(value = Article.class, name = "article"),
        @Type(value = Other.class, name = "other"),
})

public abstract class Item {
    private List<String> identifiers = new ArrayList<>();
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

    public void setIdentifiers(List<String> value) {
        identifiers = value;
    }

    public void setId(String id) {
        this.identifier = id;
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
