package com.compulsory;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class DeletePersonController {
    @DeleteMapping(value = "/delete_person")
    public String deleteUser(@RequestParam(value = "name") String name) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();

        String sql = "select id from users where name='" + name + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.next())
            return "User does not exist in database!";

        try (PreparedStatement pstmt = connection.prepareStatement(
                "delete from users where name=?")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            connection.commit();
            return "Removed user " + name + " from database.";
        }
    }
}
