package bonus.model.interfaces;

import bonus.exceptions.DuplicateIDException;
import bonus.exceptions.InvalidYearException;
import bonus.model.base.Article;
import bonus.model.base.Book;
import bonus.model.base.Other;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import bonus.utilitary.Utils;

@JsonTypeInfo(use = Id.NAME,
        property = "type")
@JsonSubTypes({
        @Type(value = Book.class, name = "book"),
        @Type(value = Article.class, name = "article"),
        @Type(value = Other.class, name = "other"),
})

public abstract class Item {
    private static List<String> identifiers = new ArrayList<>();
    private String identifier;
    private String title;
    private String location;
    private String year;
    private String author;
    private List<String> concepts;

    protected Item() {
    }

    protected Item(String title, String location, String year, String author, String identifier) {
        checkYear(Integer.valueOf(year));
        checkID(identifier);
        addConcepts();
        this.title = title;
        this.location = location;
        this.author = author;
    }

    private void addConcepts() {
        Set<String> conceptsToAdd = new HashSet<>();

        conceptsToAdd.add(Utils.conceptList.get(ThreadLocalRandom.current().nextInt(0, 5)));
        conceptsToAdd.add(Utils.conceptList.get(ThreadLocalRandom.current().nextInt(0, 5)));
        conceptsToAdd.add(Utils.conceptList.get(ThreadLocalRandom.current().nextInt(0, 5)));
        conceptsToAdd.add(Utils.conceptList.get(ThreadLocalRandom.current().nextInt(0, 5)));
        conceptsToAdd.add(Utils.conceptList.get(ThreadLocalRandom.current().nextInt(0, 5)));

        this.concepts = conceptsToAdd.stream().toList();
    }

    public List<String> getConcepts() {
        return this.concepts;
    }

    public String getId() {
        return this.identifier;
    }

    public static List<String> getIdentifiers() {
        return identifiers;
    }

    public static void setIdentifiers(List<String> identifiers) {
        Item.identifiers = identifiers;
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
