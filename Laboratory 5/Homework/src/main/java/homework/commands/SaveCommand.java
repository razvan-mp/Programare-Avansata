package homework.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import homework.model.base.Catalog;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command {
    public static void execute(Catalog catalog, String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

            mapper.writeValue(new File(path + "\\" + catalog.getName() + ".json"), catalog);
            System.out.printf("Catalog data is saved in %s%s\n", path, catalog.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
