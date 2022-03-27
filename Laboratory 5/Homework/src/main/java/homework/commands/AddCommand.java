package homework.commands;

import homework.model.base.Catalog;
import homework.model.interfaces.Item;

public class AddCommand implements Command {
    public static void execute(Catalog catalog, Item item) {
        var myList = catalog.getItemList();
        myList.add(item);
        catalog.setItemList(myList);
    }
}
