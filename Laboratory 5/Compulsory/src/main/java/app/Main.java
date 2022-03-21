package app;

import model.base.*;
import model.interfaces.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Catalog myCatalog = new Catalog();
        Item i1 = new Book("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth");
        Item i2 = new Article("java17", "The Java Language Specification", "docs.oracle.com/index.html", 2021, "James Gosling & Others");
        Item i3 = new Article("java19", "Ceva", "docs.oracle.com/index.html", 2021, "James Gosling & Others");
        Item i4 = new Article("knuth12", "Altceva", "docs.oracle.com/index.html", 2011, "James Gosling & Others");

        myCatalog.addItem(i1);
        myCatalog.addItem(i2);
        myCatalog.addItem(i3);
        myCatalog.addItem(i4);

        List<Item> itemList = new ArrayList<>();
        itemList.add(i1);
        itemList.add(i2);

        Catalog myOtherCatalog = new Catalog(itemList);

        System.out.println("First catalog: ");
        System.out.println(myCatalog);
        System.out.println("Second catalog: ");
        System.out.println(myOtherCatalog);

        myCatalog.save();
        myOtherCatalog.load("target/out.json");

        System.out.println("Second catalog after update: ");
        System.out.println(myOtherCatalog);
    }
}
