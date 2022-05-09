package compulsory.utilities;

import compulsory.entities.CityEntityDAO;
import compulsory.repos.CityRepository;

import java.sql.SQLException;

public class ExampleHelper {
    public static void execute(Boolean useJDBC) throws SQLException {
        if (useJDBC) {
            CityRepository cityRepository = new CityRepository();

            cityRepository.insertCities();
            System.out.println(cityRepository.findByName("Basildon"));
        } else {
            CityEntityDAO cityEntityDAO = new CityEntityDAO();
            System.out.println(cityEntityDAO.findByName("Basildon"));
        }
    }
}
