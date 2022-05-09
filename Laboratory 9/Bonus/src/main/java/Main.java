import compulsory.entities.City;
import compulsory.entities.CityEntityDAO;
import compulsory.repos.CityRepository;
import compulsory.utilities.ExampleHelper;
import compulsory.utilities.FileInfoGetter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Boolean useJDBC = FileInfoGetter.get();
        ExampleHelper.execute(useJDBC);
    }
}
