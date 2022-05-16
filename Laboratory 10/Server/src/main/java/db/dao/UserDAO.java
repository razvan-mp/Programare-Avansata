package db.dao;

import db.singleton.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into users (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            con.commit();
        }
    }

    public static String findById(Integer userId) throws SQLException {
        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT name from USERS WHERE id=" + userId;

        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet.next() ? resultSet.getString(1) : null;
    }

    public static Integer findByName(String name) throws SQLException {
        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT id from users where name='" + name + "'";

        ResultSet result = statement.executeQuery(sql);
        return result.next() ? result.getInt(1) : null;
    }

    public static boolean friendshipExists(Integer firstId, Integer secondId) throws SQLException {
        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT id_2 from friends where id_1=" + firstId + " and id_2=" + secondId;
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet.next();
    }

    public static void addFriendship(Integer firstId, Integer secondId) throws SQLException {
        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();

        try (PreparedStatement pstmt = connection.prepareStatement(
                "insert into friends (id_1, id_2) values (?, ?)")) {
            pstmt.setInt(1, firstId);
            pstmt.setInt(2, secondId);
            pstmt.executeUpdate();
            connection.commit();
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "insert into friends (id_1, id_2) values (?, ?)")) {
            pstmt.setInt(1, secondId);
            pstmt.setInt(2, firstId);
            pstmt.executeUpdate();
            connection.commit();
        }
    }

    public static void sendMessage(Integer senderId, String message) throws SQLException {
        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();

        String sql = "select id_2 from friends where id_1 =" + senderId;
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int receiverId = resultSet.getInt(1);

            try (PreparedStatement pstmt = connection.prepareStatement(
                    "update friends set messages=? where id_1=? and id_2=?")) {
                pstmt.setString(1, message);
                pstmt.setInt(2, senderId);
                pstmt.setInt(3, receiverId);

                System.out.println(pstmt);

                pstmt.executeUpdate();
                connection.commit();
            }

        }
    }

    public static List<List<Integer>> getFriendships() throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();

        String sql;
        sql = "select max(id) from users";
        ResultSet resultSet = statement.executeQuery(sql);

        Integer maxId = resultSet.next() ? resultSet.getInt(1) : -1;

        if (maxId == -1)
            return null;

        List<List<Integer>> adjacencyList = new ArrayList<>(maxId);

        for (int index = 1; index <= maxId; index++) {
            adjacencyList.add(index - 1, new ArrayList<>());
            sql = "select id_2 from friends where id_1=" + index;
            resultSet = statement.executeQuery(sql);

            while (resultSet.next())
                adjacencyList.get(index - 1).add(resultSet.getInt(1) - 1);
        }

//        System.out.println(adjacencyList.size());
        return adjacencyList;
    }

    public static String readMessages(Integer connectedUserId) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();

        String sql = "select messages, id_1 from friends where id_2=" + connectedUserId;
        ResultSet resultSet = statement.executeQuery(sql);

        StringBuilder messages = new StringBuilder();
        Integer messageCounter = 1;
        while (resultSet.next()) {
            messages.append("Message ");
            messages.append(messageCounter).append(", from ").append(findById(resultSet.getInt(2)));
            messages.append(": ");
            messages.append(resultSet.getString(1));
            messages.append("\n");
            messageCounter++;
        }

        return messages.toString();
    }
}
