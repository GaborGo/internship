package com.nexttech.internship.contApp.JDBC;

import com.nexttech.internship.contApp.model.Invoice;
import com.nexttech.internship.contApp.model.Supplier;
import com.nexttech.internship.contApp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBConnection {

    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String URL = "jdbc:mysql://localhost:3306/contapp";
    final String USERNAME = "root";
    final String PASSWORD = "";
    private Connection conn = null;
    private Statement statement = null;

    public DBConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected to DB.");
            } catch (SQLException ex) {
                System.out.println("Err: " + ex);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (conn != null) {
            try {
                statement = conn.createStatement();
            } catch (Exception ex) {
                System.out.println("Statement creation failed." + ex);
            }
        }
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }


    public void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}

