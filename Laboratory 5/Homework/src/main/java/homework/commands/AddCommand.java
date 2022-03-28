package homework.commands;

import homework.model.base.Catalog;
import homework.model.interfaces.Item;

/**
 * command class for adding an item to catalog
 */
public class AddCommand implements Command {
    /**
     * adds an item to catalog
     * @param catalog catalog to insert item into
     * @param item item to be inserted
     */
    public static void execute(Catalog catalog, Item item) {
        var myList = catalog.getItemList();
        myList.add(item);
        catalog.setItemList(myList);
    }
}
