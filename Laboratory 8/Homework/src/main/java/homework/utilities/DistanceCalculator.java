package homework.utilities;

import homework.entity.Entity;
import homework.entity.EntityDAO;

import java.sql.SQLException;

public class DistanceCalculator {
    public static void get(EntityDAO entities, Entity firstCity, Entity secondCity) throws SQLException {

        Double firstCityLat = entities.getLatitude(firstCity);
        Double firstCityLon = entities.getLongitude(firstCity);

        Double secondCityLat = entities.getLatitude(secondCity);
        Double secondCityLon = entities.getLongitude(secondCity);

        if (firstCityLat == null || secondCityLat == null || firstCityLon == null || secondCityLon == null)
            System.out.println("Distance cannot be calculated. One of the cities has a null value in its coordinates.");
        else {
            firstCityLon = Math.toRadians(firstCityLon);
            secondCityLon = Math.toRadians(secondCityLon);
            firstCityLat = Math.toRadians(firstCityLat);
            secondCityLat = Math.toRadians(secondCityLat);

            double degreeLon = secondCityLon - firstCityLon;
            double degreeLat = secondCityLat - firstCityLat;
            double variance = Math.pow(Math.sin(degreeLat / 2), 2)
                    + Math.cos(firstCityLat)
                    * Math.cos(secondCityLat)
                    * Math.pow(Math.sin(degreeLon / 2), 2);

            double varianceCirc = 2 * Math.asin(Math.sqrt(variance));
            double radiusKm = 6371;
            double radiusMi = 3956;

            System.out.printf("Distance between %s and %s is: %.2f kilometers, or %.2f miles.\n", firstCity,
                    secondCity, varianceCirc * radiusKm, varianceCirc * radiusMi);
        }
    }
}
