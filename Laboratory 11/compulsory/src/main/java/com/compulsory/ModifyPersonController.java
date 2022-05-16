package com.compulsory;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.List;

@RestController
public class ModifyPersonController {
    @PutMapping(value = "/modify_person")
    public String modifyUser(@RequestParam("user") List<String> users) throws SQLException {
        if (users.isEmpty())
            return "Must provide usernames!";

        String firstUser = users.get(0);
        if (users.get(1) == null)
            return "Must provide a new username!";

        String secondUser = users.get(1);

        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();

        String sql = "select id from users where name='" + firstUser + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.next())
            return "Username does not exist in database.";

        try (PreparedStatement pstmt = connection.prepareStatement(
                "update users set name=? where name=?")) {
            pstmt.setString(1, secondUser);
            pstmt.setString(2, firstUser);
            pstmt.executeUpdate();
            connection.commit();
            return "Changed user " + firstUser + " to " + secondUser + ".";
        }
    }
}
