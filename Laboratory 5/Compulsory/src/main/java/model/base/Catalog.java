package model.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.interfaces.Item;
import model.enums.ItemType;

public class Catalog {
    protected List<Item> itemList;

    public Catalog(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Catalog() {
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWr = new FileWriter("target/out.json");
        gson.toJson(itemList, fileWr);
        fileWr.flush();
        fileWr.close();
    }

    public void load(String filePath) throws IOException {
        Gson gson = new Gson();
        FileReader fileRd = new FileReader(filePath);
        Item[] newItemList = gson.fromJson(fileRd, Item[].class);
        this.itemList.clear();
        this.itemList.addAll(Arrays.asList(newItemList));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var item : itemList) {
            sb.append("[\"identifier\":\"").append(item.getId()).append("\", \"title\":\"").append(item.getTitle());
            sb.append("\", \"location\":\"").append(item.getLocation()).append("\", \"year\":\"");
            sb.append(item.getYear()).append("\", \"author\":\"").append(item.getAuthor());
            sb.append("\", \"type\":\"").append(item.getType().toString()).append("\"]\n");
        }
        return sb.toString();
    }
}
