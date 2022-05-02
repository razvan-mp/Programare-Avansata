package homework.utilities;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import homework.entity.Entity;
import homework.entity.EntityDAO;
import homework.model.City;
import homework.model.Continent;
import homework.model.Country;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtractTool {
    public static void run(EntityDAO entities) throws SQLException {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\razva\\Desktop\\concap.csv"))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        records.remove(0);

        for (List<String> item : records) {
            Entity continent = new Continent(item.get(item.size() - 1));
            entities.create(continent);
        }

        for (List<String> item : records) {
            Entity country = new Country(item.get(0).replaceAll("'", " "), item.get(item.size() - 1));
            entities.create(country);
        }

        for (List<String> item : records) {
            Entity city = new City(item.get(0).replaceAll("'", " "), item.get(1).replaceAll("'", " "),
                    true, Double.parseDouble(item.get(2)), Double.parseDouble(item.get(3)));
            entities.create(city);
        }
    }
}
