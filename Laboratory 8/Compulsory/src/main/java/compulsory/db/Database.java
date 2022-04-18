package compulsory.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/lab8";
    private static final String USER = "postgres";
    private static Connection connection = null;

    private Database() throws SQLException {
        connection.setAutoCommit(false);
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null)
            createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, "greutare");
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            System.err.println(exception);
        }
    }

    public static void closeConnection() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException exception) {
                System.err.println(exception);
            }
    }
}
