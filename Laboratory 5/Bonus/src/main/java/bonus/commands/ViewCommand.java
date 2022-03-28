package bonus.commands;

import bonus.exceptions.InvalidIDException;
import bonus.model.base.Catalog;
import bonus.utilitary.Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ViewCommand implements Command {
    public static void execute(String identifier, Catalog catalog) throws IOException {
        var myList = catalog.getItemList();
        try {
            if (!myList.get(0).getIdentifiers().contains(identifier))
                throw new InvalidIDException("ID does not exist in this catalog.");

            for (var item : myList) {
                if (item.getId().equals(identifier))
                    if (!Utils.isLocal(item.getLocation()))
                        Desktop.getDesktop().open(new File(item.getLocation()));
                    else
                        Desktop.getDesktop().browse(new URI(item.getLocation()));
            }

        } catch (InvalidIDException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
