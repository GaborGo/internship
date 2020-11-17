package com.nexttech.internship.JDBC;

import com.nexttech.internship.contApp.Invoice;
import com.nexttech.internship.contApp.Supplier;
import com.nexttech.internship.contApp.User;

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
    final String PASSWORD = "pass";
    private Connection conn = null;
    private Statement statement = null;

    public DBConnection() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to DB.");
        } catch (SQLException ex) {
            System.out.println("Err: " + ex);
        }

        if (conn != null) {
            try {
                statement = conn.createStatement();
            } catch (Exception ex) {
                System.out.println("Statement creation failed." + ex);
            }
        }
    }

    //1 select toate facturile firmei mele care au fost platite prin banca
    public void printAllInvoicesPayedOnlineForFirmWithId(int id) {
        String selectSql =
                "SELECT * FROM contapp.invoices RIGHT JOIN payments ON invoices.invoice_ID = payments.payment_ID RIGHT JOIN suppliers " +
                        "ON suppliers.supplier_ID = invoices.supplier_ID " +
                        "RIGHT JOIN firms " +
                        "ON suppliers.firm_ID = firms.firm_ID " +
                        "AND payments.type = \"bank\" " +
                        "AND firms.firm_ID = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            List<Invoice> invoices = new ArrayList<>();
            while (resultSet.next()) {
                Invoice invoice = new Invoice(
                        resultSet.getInt("invoice_ID"),
                        resultSet.getInt("number"),
                        resultSet.getDate("emit_Date"),
                        resultSet.getDate("scadent_Date"),
                        resultSet.getInt("value"),
                        resultSet.getInt("sold"),
                        resultSet.getInt("supplier_ID")
                );
                invoices.add(invoice);
            }
            invoices.stream()
                    .filter(n -> n.getId() != 0)
                    .collect(Collectors.toList())
                    .forEach(n -> System.out.println("Invoice nr: " + n.getNumber() + " Invoice value: " + n.getValue()));
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        closeConnection();
    }

    //2.select toate facturile pe care le nu le-am platit, ordonate dupa data de scadenta cele care au scadenta cel mai urgent sa imi apara primele
    public void printAllScadentInvoicesOrderedByDueTimeForFirmWithId(int firmId) {
        String selectSql = "SELECT * FROM contapp.invoices " +
                "RIGHT JOIN suppliers " +
                "ON suppliers.supplier_ID = invoices.supplier_ID " +
                "RIGHT JOIN firms " +
                "ON suppliers.firm_ID = firms.firm_ID " +
                "WHERE sold > 0 AND firms.firm_ID = " + firmId +
                " ORDER BY scadent_Date ASC;";

        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            List<Invoice> invoices = new ArrayList<>();
            while (resultSet.next()) {
                Invoice invoice = new Invoice(
                        resultSet.getInt("invoice_ID"),
                        resultSet.getInt("number"),
                        resultSet.getDate("emit_Date"),
                        resultSet.getDate("scadent_Date"),
                        resultSet.getInt("value"),
                        resultSet.getInt("sold"),
                        resultSet.getInt("supplier_ID")
                );
                invoices.add(invoice);
            }
            invoices.stream()
                    .filter(n -> n.getId() != 0)
                    .collect(Collectors.toList())
                    .forEach(n -> System.out.println("Invoice nr: " + n.getNumber() + " Invoice sold: " + n.getSold() + " Scadency: " + n.getScadentDate()));
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        closeConnection();
    }

    // toate facturile de la electrica
    public void printAllInvoicesFromSupplierForFirmWithId(String supplier, int firmId) {
        String selectSql = "SELECT * FROM contapp.invoices " +
                "RIGHT JOIN suppliers " +
                "ON suppliers.supplier_ID = invoices.supplier_ID " +
                "RIGHT JOIN firms " +
                "ON suppliers.firm_ID = firms.firm_ID " +
                "WHERE suppliers.name = " + "'" + supplier + "'" +
                " AND firms.firm_ID = " + firmId;

        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            List<Invoice> invoices = new ArrayList<>();
            while (resultSet.next()) {
                Invoice invoice = new Invoice(
                        resultSet.getInt("invoice_ID"),
                        resultSet.getInt("number"),
                        resultSet.getDate("emit_Date"),
                        resultSet.getDate("scadent_Date"),
                        resultSet.getInt("value"),
                        resultSet.getInt("sold"),
                        resultSet.getInt("supplier_ID")
                );
                invoices.add(invoice);
            }
            invoices.stream()
                    .filter(n -> n.getId() != 0)
                    .collect(Collectors.toList())
                    .forEach(n -> System.out.println("Supplier: " + supplier + " Invoice nr: " + n.getNumber() + " Invoice value: " + n.getValue() + " Scadency: " + n.getScadentDate()));
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        closeConnection();
    }

    //toate facturile Orange din ultimul an
    public void printAllInvoicesFromDateForSupplierForFirmWithId(String beginDate, String supplier, int firmId) {
        String selectSql = "SELECT * FROM contapp.invoices " +
                "RIGHT JOIN suppliers " +
                "ON suppliers.supplier_ID = invoices.supplier_ID " +
                "RIGHT JOIN firms " +
                "ON suppliers.firm_ID = firms.firm_ID " +
                "WHERE suppliers.name =" + "'" + supplier + "' " +
                "AND invoices.emit_Date > " + "'" + beginDate + "' " +
                "AND firms.firm_ID = " + firmId;

        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            List<Invoice> invoices = new ArrayList<>();
            while (resultSet.next()) {
                Invoice invoice = new Invoice(
                        resultSet.getInt("invoice_ID"),
                        resultSet.getInt("number"),
                        resultSet.getDate("emit_Date"),
                        resultSet.getDate("scadent_Date"),
                        resultSet.getInt("value"),
                        resultSet.getInt("sold"),
                        resultSet.getInt("supplier_ID")
                );
                invoices.add(invoice);
            }
            invoices.stream()
                    .filter(n -> n.getId() != 0)
                    .collect(Collectors.toList())
                    .forEach(n -> System.out.println("Supplier: " + supplier + " Invoice nr: " + n.getNumber() + " Invoice value: " + n.getValue() + " Date: " + n.getEmitDate()));
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        closeConnection();
    }

    //toti userii firmei grupati pe roluri
    public void printAllUsersByRolesForFirmWithId(int firmId) {
        String selectSql = "SELECT * FROM users RIGHT JOIN firms ON users.firm_ID = firms.firm_ID " +
                "WHERE users.firm_ID = " + firmId +
                " ORDER BY users.role;";
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("user_ID"),
                        resultSet.getInt("firm_ID"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("role"));
                users.add(user);
            }
            users.forEach(n -> System.out.println("Username: " + n.getUsername() + " Role: " + n.getRole()));
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        closeConnection();
    }

    //numele tuturor furnizorilor pe care i-am platit cu cash in ultimele doua luni
    public void printAllSupplierNamesByPaymentFromDateForFirmWithId(String payment, String date, int firmId) {
        String selectSql = "SELECT * FROM suppliers " +
                "RIGHT JOIN firms ON firms.firm_ID = suppliers.firm_ID " +
                "RIGHT JOIN invoices ON suppliers.supplier_ID = invoices.supplier_ID " +
                "RIGHT JOIN payments ON invoices.invoice_ID = payments.invoice_ID " +
                "WHERE payments.type = " + "'" + payment + "' " +
                "AND payments.date > " + "'" + date + "' " +
                "AND firms.firm_ID = " + firmId;
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            List<Supplier> suppliers = new ArrayList<>();
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getInt("supplier_ID"),
                        resultSet.getString("name"),
                        resultSet.getInt("firm_ID"));

                suppliers.add(supplier);
            }
            suppliers.forEach(n -> System.out.println("Supplier name: " + n.getName()));
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        closeConnection();
    }

    public void printTotalPaymentsToSupplierDescForFirmWithId(int firmId) {
        String selectSql = " SELECT suppliers.name, SUM(payments.value) FROM payments " +
                "RIGHT JOIN invoices ON invoices.invoice_ID = payments.invoice_ID " +
                "RIGHT JOIN suppliers ON suppliers.supplier_ID = invoices.supplier_ID " +
                "RIGHT JOIN firms ON firms.firm_ID = suppliers.firm_ID " +
                "WHERE firms.firm_ID = " + firmId +
                " GROUP BY suppliers.name " +
                "ORDER BY SUM(payments.value) DESC;";
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            Map results = new HashMap<String, Integer>();
            while (resultSet.next()) {
                results.put(resultSet.getString("name"), resultSet.getString("SUM(payments.value)"));
            }
            System.out.println(results.entrySet());
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        closeConnection();
    }


    public void printAllUserNames() {
        String selectSql = "SELECT * FROM users;";
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("user_ID"),
                        resultSet.getInt("firm_ID"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("role"));
                users.add(user);
            }
            users.forEach(n -> System.out.println(n.getUsername()));
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        closeConnection();
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

