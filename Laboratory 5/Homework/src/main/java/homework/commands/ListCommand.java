package homework.commands;

import homework.model.base.Catalog;
import homework.utilitary.Utils;

/**
 * lists items in catalog
 */
public class ListCommand implements Command {
    public static void execute(Catalog catalog) {
        var myList = catalog.getItemList();
        if (myList.size() == 0) {
            System.out.println("Catalog is empty");
            return;
        }
        System.out.println("Catalog " + catalog.getName());

        StringBuilder sb = new StringBuilder();
        for (var item : myList) {
            String type = Utils.getItemType(item);
            sb.append(type).append(" (").append(item.getId()).append(")").append(": ").append(item.getTitle())
                    .append(" (").append(item.getYear()).append("), ").append(item.getAuthor())
                    .append("\n\tLocated at: ").append(item.getLocation()).append("\n");
        }
        System.out.println(sb);
    }
}
