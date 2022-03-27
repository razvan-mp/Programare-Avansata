package homework.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import homework.model.base.Catalog;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command {
    public static Catalog execute(String path) {
        Catalog catalog = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            catalog = mapper.readValue(new File(path), Catalog.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return catalog;
    }
}
