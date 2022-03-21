package model.interfaces;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import model.enums.ItemType;
import exceptions.DuplicateIDException;
import exceptions.InvalidYearException;

public abstract class Item {
    private final static List<String> identifiers = new ArrayList<>();
    private String identifier;
    private final String title;
    private final String location;
    private String year;
    private final String author;
    private final ItemType type;

    protected Item(String title, String location, String year, String author, String type, String identifier) {
        checkYear(Integer.valueOf(year));
        checkID(identifier);
        this.title = title;
        this.location = location;
        this.author = author;
        this.type = ItemType.valueOf(type.toUpperCase());
    }

    public String getId() {
        return this.identifier;
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

    public ItemType getType() {
        return this.type;
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
