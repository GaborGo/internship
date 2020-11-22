package com.nexttech.internship.contApp.JDBC;

import com.nexttech.internship.contApp.model.Invoice;
import com.nexttech.internship.contApp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {

    private DBConnection conn = null;
    private Statement statement = null;

    public DBConnection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }

    public void addUser(User user) {
        conn = new DBConnection();
        try {
            PreparedStatement preparedStatement = conn.getConn().prepareStatement("insert into users values(?,?,?,?,?,?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.setInt(6, user.getFirmId());
            preparedStatement.executeUpdate();
            conn.closeConnection();
        } catch (Exception ex) {
            System.out.println("Cannot add user! " + ex);
        }
    }

    public void updateUser(User user) {
        conn = new DBConnection();
        try {
            PreparedStatement preparedStatement = conn.getConn().prepareStatement("update users set username=?, password=?, email=?, role=?, firm_ID=? where user_ID=?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, user.getFirmId());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.executeUpdate();
            conn.closeConnection();
        } catch (Exception ex) {
            System.out.println("Cannot update user! " + ex);
        }
    }

    public void deleteUserWithId(int id) {
        conn = new DBConnection();
        try {
            PreparedStatement preparedStatement = conn.getConn().prepareStatement("delete from users where user_ID=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            conn.closeConnection();
        } catch (Exception ex) {
            System.out.println("Cannot delete user! " + ex);
        }
    }

    public void deleteUser(User user) {
        conn = new DBConnection();
        try {
            PreparedStatement preparedStatement = conn.getConn().prepareStatement("delete from users where user_ID=?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            conn.closeConnection();
        } catch (Exception ex) {
            System.out.println("Cannot delete user! " + ex);
        }
    }

    public List<User> getAllUsersByRolesForFirmWithId(int firmId) {
        conn = new DBConnection();
        statement = conn.getStatement();
        List<User> users = new ArrayList<>();
        String selectSql = "SELECT * FROM users " +
                "JOIN firms ON users.firm_ID = firms.firm_ID " +
                "WHERE users.firm_ID = " + firmId +
                " ORDER BY users.role;";
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            users = extractUsersFromResultSet(resultSet);
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        printUsers(users);
        return users;
    }

    public User getUserById(int id) {
        conn = new DBConnection();
        statement = conn.getStatement();
        String selectSql = "SELECT * FROM users WHERE user_ID = " + id + ";";
        User user = new User();
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            user = extractUserFromResultSet(resultSet);
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        printUser(user);
        return user;
    }

    public List<User> getAllUsers() {
        conn = new DBConnection();
        statement = conn.getStatement();
        String selectSql = "SELECT * FROM users;";
        List<User> users = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            users = extractUsersFromResultSet(resultSet);

        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        printUsers(users);
        return users;
    }

    public List<User> extractUsersFromResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setId(rs.getInt("user_ID"));
            user.setEmail(rs.getString("email"));
            user.setFirmId(rs.getInt("firm_ID"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            users.add(user);
        }
        return users;
    }

    public User extractUserFromResultSet(ResultSet rs) throws SQLException {
        rs.next();
        return new User(rs.getInt("user_ID"), rs.getInt("firm_ID"),
                rs.getString("username"), rs.getString("password"),
                rs.getString("email"), rs.getString("role"));
    }


    public void printUsers(List<User> users) {
        users.stream()
                .collect(Collectors.toList())
                .forEach(n -> System.out.println("Username:" + n.getUsername()
                        + " Email:" + n.getEmail() + " Role:" + n.getRole()
                        + " Firm ID:" + n.getFirmId()));
    }

    public void printUser(User user) {
        System.out.println("Username:" + user.getUsername()
                + " Email:" + user.getEmail() + " Role:" + user.getRole()
                + " Firm ID:" + user.getFirmId());
    }
}
