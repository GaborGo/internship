package com.nexttech.internship.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    final String JDBC_DRIVER = "org.apache,der";
    final String URL = "";
    final String USERNAME = "";
    final String PASSWORD = "";

    public DBConnection () {
        try {
              Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (SQLException ex) {
            System.out.println("" + ex);
        }

    }
}

