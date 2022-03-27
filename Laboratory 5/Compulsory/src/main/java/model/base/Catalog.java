package model.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.SerializationFeature;
import model.interfaces.Item;

public class Catalog {
    private String name;
    private List<Item> itemList;

    @JsonCreator
    public Catalog(@JsonProperty("name") String name, @JsonProperty("itemList") List<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public Catalog(String name) {
        this.name = name;
        this.itemList = new ArrayList<>();
    }

    public void addItem(Item itemToAdd) {
        if (!itemList.isEmpty()) {
            for (var item : itemList)
                if (item.equals(itemToAdd))
                    return;
        }
        itemList.add(itemToAdd);
    }

    public void save() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        mapper.writeValue(new File("target/out.json"), this);
    }

    public Catalog load(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), Catalog.class);
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

            sb.append("\"]\n");
        }
        return sb.toString();
    }
}
