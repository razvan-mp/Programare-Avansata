package app;

import networking.Server;
import utilitaries.PropertyGenerator;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            new Server(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertyGenerator.generate();
    }
}
