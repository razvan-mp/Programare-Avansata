package bonus.utilitary;

import bonus.exceptions.InvalidYearException;
import bonus.model.base.Article;
import bonus.model.base.Book;
import bonus.model.base.Catalog;
import bonus.model.interfaces.Item;

import java.time.Year;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * useful functions put here for decluttering
 */
public class Utils {
    public static List<String> conceptList = List.of("Hardware", "Networks", "Computer systems organization",
            "Computing methodologies", "Applied computing");

    public static void checkYear(String toCheck) {
        try {
            int year = parseInt(toCheck);
            if (year < 0 || year > Year.now().getValue() + 10)
                throw new InvalidYearException();
        } catch (InvalidYearException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getItemType(Item item) {
        if (item.getClass().isInstance(new Book()))
            return "Book";
        else if (item.getClass().isInstance(new Article()))
            return "Article";
        return "Other";
    }

    public static boolean isLocal(String location) {
        return location.startsWith("http://") || location.startsWith("https://");
    }

    public static String getPath(String identifier, Catalog catalog) {
        var myList = catalog.getItemList();
        for (var item : myList) {
            if (item.getId().equals(identifier))
                return item.getLocation();
        }
        return null;
    }
}
