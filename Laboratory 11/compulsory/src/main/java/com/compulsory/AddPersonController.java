package com.compulsory;

import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
public class AddPersonController {
    @PostMapping(value = "/add_person")
    public String addUser(@RequestParam(value = "name") String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into users (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            con.commit();
            return "User " + name + " added successfully to database.";
        }
    }
}
