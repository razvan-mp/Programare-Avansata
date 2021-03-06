package compulsory.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/lab8";
    private static final String USER = "postgres";
    private static final String PASSWORD = "greutare";
    private static Connection connection = null;
    private Database() {}

    public static Connection getConnection() {
        if(connection == null)
            createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);

        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();

        }
    }
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
