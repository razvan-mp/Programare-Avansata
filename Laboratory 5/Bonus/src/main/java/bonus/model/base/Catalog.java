package bonus.model.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import bonus.model.interfaces.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Catalog class that implements a catalog structure, with a <code>name</code> and an <code>itemList</code>
 */
public class Catalog {
    protected String name;
    protected List<Item> itemList;

    /**
     * Used JsonCreator for parsing from Json
     * @param name catalog name
     * @param itemList list of items to be added to newly created Catalog
     */
    @JsonCreator
    public Catalog(@JsonProperty("name") String name, @JsonProperty("itemList") List<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public Catalog(String name) {
        this.name = name;
        this.itemList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var item : itemList) {
            sb.append("[\"identifier\":\"").append(item.getId()).append("\", \"title\":\"").append(item.getTitle());
            sb.append("\", \"location\":\"").append(item.getLocation()).append("\", \"year\":\"");
            sb.append(item.getYear()).append("\", \"author\":\"").append(item.getAuthor());
            sb.append("\", \"type\":\"");

            if (item.getClass().isInstance(new Book()))
                sb.append("book");
            else if (item.getClass().isInstance(new Article()))
                sb.append("article");
            else
                sb.append("other");

            sb.append(item.getConcepts().toString());
            sb.append("\"]\n");
        }
        return sb.toString();
    }
}
