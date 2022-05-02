package homework.entity;

import homework.model.City;
import homework.model.Continent;
import homework.model.Country;
import homework.singleton.Database;

import java.sql.*;

public class EntityDAO {
    public void create(Entity entity) throws SQLException {
        if (entity instanceof City) {
            Connection connection = Database.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "DO $$ \n" +
                            "<<first_block>>\n" +
                            "DECLARE\n" +
                            "  v_exists integer := 0;\n" +
                            "BEGIN \n" +
                            "   select count(*) into v_exists from (select name from hcities where name='" + entity.getName() + "' and country='" + ((City) entity).getCountry() + "') foo;\n" +
                            "\tif v_exists = 0 \n" +
                            "\tthen \n" +
                            "\t\tinsert into hcities (country, name, capital, latitude, longitude) values" +
                            " ('" + ((City) entity).getCountry() + "', '" + entity.getName() + "', " + ((City) entity).isCapital() + "," + ((City) entity).getLatitude() + ", " + ((City) entity).getLongitude() + ");\n" +
                            "\tend if;\n" +
                            "END first_block $$;"
            )) {
                preparedStatement.executeUpdate();
            }
        } else if (entity instanceof Country) {
            Connection connection = Database.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "DO $$ \n" +
                            "<<first_block>>\n" +
                            "DECLARE\n" +
                            "  v_exists integer := 0;\n" +
                            "BEGIN \n" +
                            "   select count(*) into v_exists from (select name from hcountries where name='" + entity.getName() + "') foo;\n" +
                            "\tif v_exists = 0 \n" +
                            "\tthen \n" +
                            "\t\tinsert into hcountries (name, continent) values ('" + entity.getName() + "', '" + ((Country) entity).getContinent() + "');\n" +
                            "\tend if;\n" +
                            "END first_block $$;"
            )) {
                preparedStatement.executeUpdate();
            }
        } else if (entity instanceof Continent) {
            Connection connection = Database.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "DO $$ \n" +
                            "<<first_block>>\n" +
                            "DECLARE\n" +
                            "  v_exists integer := 0;\n" +
                            "BEGIN \n" +
                            "   select count(*) into v_exists from (select name from continents where name='" + entity.getName() + "') foo;\n" +
                            "\tif v_exists = 0 \n" +
                            "\tthen \n" +
                            "\t\tinsert into continents (name) values ('" + entity.getName() + "');\n" +
                            "\tend if;\n" +
                            "END first_block $$;"
            )) {
                preparedStatement.executeUpdate();
            }
        }
    }

    public Integer findByName (Entity entity) throws SQLException {
        if (entity instanceof City) {
            Connection connection = Database.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "select id from hcities where name='" + entity.getName() + "'")) {
                return resultSet.next() ? resultSet.getInt(1) : null;
            }
        } else if (entity instanceof Country) {
            String countryName = entity.getName().replace("'", " ");

            Connection connection = Database.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "select id from hcountries where name='" + countryName + "'")) {
                return resultSet.next() ? resultSet.getInt(1) : null;
            }
        } else if (entity instanceof Continent) {
            Connection connection = Database.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "select id from continents where name='" + entity.getName() + "'")) {
                return resultSet.next() ? resultSet.getInt(1) : null;
            }
        }
        return -1;
    }

    public String findById (Entity entity, Integer id) throws SQLException {
        if (entity instanceof City) {
            Connection connection = Database.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "select name from cities where id=" + id
                 )
            ) {
                return resultSet.next() ? resultSet.getString(1) : null;
            }
        } else if (entity instanceof Country) {
            Connection connection = Database.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "select name from countries where id=" + id
                 )
            ) {
                return resultSet.next() ? resultSet.getString(1) : null;
            }
        } else if (entity instanceof Continent) {
            Connection connection = Database.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "select name from continents where id=" + id
                 )
            ) {
                return resultSet.next() ? resultSet.getString(1) : null;
            }
        }
        return "No entity of type " + entity.getClass() + " exists in the database.";
    }

    public String findAll (Entity entity) throws SQLException {
        if (entity instanceof City)
            return "homework.entity.Entity provided is of type homework.model.City, and it does not have any components to find.";
        else if (entity instanceof Country) {
            Connection connection = Database.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "select name from hcities where country='" + entity.getName() + "'"
                 )
            ) {
                StringBuilder stringBuilder = new StringBuilder();
                while (resultSet.next()) {
                    stringBuilder.append(resultSet.getString("name"));
                    stringBuilder.append("\n");
                }

                System.out.println("All components of " + entity.getName() + ":\n" + stringBuilder.toString());
            }
        }
        else if (entity instanceof Continent) {
            Connection connection = Database.getConnection();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(
                         "select name from hcountries where continent='" + entity.getName() + "'"
                 )
            ) {
                StringBuilder stringBuilder = new StringBuilder();
                while (resultSet.next()) {
                    stringBuilder.append(resultSet.getString("name"));
                    stringBuilder.append("\n");
                }
                System.out.println("All components of " + entity.getName() + ":\n" + stringBuilder.toString());
            }
        }
        return null;
    }

    public Double getLatitude(Entity city) {
        if (city instanceof Country || city instanceof Continent)
            return -1.0;

        return ((City) city).getLatitude();
    }

    public Double getLongitude(Entity city) {
        if (city instanceof Country || city instanceof Continent)
            return -1.0;

        return ((City) city).getLongitude();
    }
}
