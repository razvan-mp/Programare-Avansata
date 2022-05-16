package com.compulsory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserListController {
    List<User> userList = new ArrayList<>();

    @GetMapping("/get_users")
    public List<User> user() throws SQLException {
        userList.clear();
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql;
        sql = "select max(id) from users";
        ResultSet resultSet = statement.executeQuery(sql);

        int maxId = resultSet.next() ? resultSet.getInt(1) : -1;

        if (maxId == -1)
            return null;

        for (int index = 1; index <= maxId; index++) {
            sql = "select name from users where id=" + index;
            resultSet = statement.executeQuery(sql);

            resultSet.next();
            userList.add(new User(index, resultSet.getString(1)));
        }

        return userList;
    }
}
