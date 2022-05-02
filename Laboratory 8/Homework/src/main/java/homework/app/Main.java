package homework.app;

import homework.entity.Entity;
import homework.entity.EntityDAO;
import homework.model.City;
import homework.model.Country;
import homework.singleton.Database;
import homework.utilities.DistanceCalculator;
import homework.utilities.ExtractTool;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            EntityDAO entities = new EntityDAO();

            // call tool to extract data from CSV
            ExtractTool.run(entities);
            Database.getConnection().commit();


            // create a new mock country to exemplify the usage of the <code>findAll</code>
            // method &
            // create two cities that have the exact coordinates of Bucharest and Ankara
            // knowing that the distance between them is 750 km or 436 mi

            Entity newCountry = new Country("NewCountry", "Europe");
            Entity bucharest = new City("NewCountry", "Bucharest", false, 44.43333333333333, 26.1);
            Entity ankara = new City("NewCountry", "Ankara", false, 39.93333333333333, 32.866667);
            Entity city1 = new City("NewCountry", "First city", false, 39.93333333333333, 32.866667);
            Entity city2 = new City("NewCountry", "Second city", false, 39.93333333333333, 32.866667);
            Entity city3 = new City("NewCountry", "Third city", false, 39.93333333333333, 32.866667);

            entities.create(newCountry);
            entities.create(bucharest);
            entities.create(ankara);
            entities.create(city1);
            entities.create(city2);
            entities.create(city3);

            // get the distance between the mock cities
            DistanceCalculator.get(entities, bucharest, ankara);

            // find all cities in mock country
            entities.findAll(newCountry);

            Database.closeConnection();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
