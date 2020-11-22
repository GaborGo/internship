package com.nexttech.internship.contApp.JDBC;

import com.nexttech.internship.contApp.model.Supplier;
import com.nexttech.internship.contApp.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierDao {

    private DBConnection conn = null;
    private Statement statement = null;

    public DBConnection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }

    public List<Supplier> getAllSupplierNamesByPaymentFromDateForFirmWithId(String payment, String date, int firmId) {
        conn = new DBConnection();
        statement = conn.getStatement();
        List<Supplier> suppliers = new ArrayList<>();
        String selectSql = "SELECT * FROM suppliers " +
                "JOIN firms ON firms.firm_ID = suppliers.firm_ID " +
                "JOIN invoices ON suppliers.supplier_ID = invoices.supplier_ID " +
                "JOIN payments ON invoices.invoice_ID = payments.invoice_ID " +
                "WHERE payments.type = " + "'" + payment + "' " +
                "AND payments.date > " + "'" + date + "' " +
                "AND firms.firm_ID = " + firmId;
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            suppliers = extractSuppliersFromResultSet(resultSet);
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        printSuppliers(suppliers);
        return suppliers;
    }

    public List<Supplier> extractSuppliersFromResultSet(ResultSet rs) throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        while (rs.next()) {
            Supplier supplier = new Supplier();
            supplier.setId(rs.getInt("supplier_ID"));
            supplier.setName(rs.getString("name"));
            supplier.setFirmId(rs.getInt("firm_ID"));

            suppliers.add(supplier);
        }
        return suppliers;
    }

    public void printSuppliers(List<Supplier> suppliers) {
        suppliers.stream()
                .collect(Collectors.toList())
                .forEach(n -> System.out.println("Supplier name:" + n.getName()
                        + " ID:" + n.getId() + " Firm ID:" + n.getFirmId()));
    }

    public void printSupplier(Supplier supplier) {
        System.out.println("Supplier name:" + supplier.getName()
                + " ID:" + supplier.getId() + " Firm ID:" + supplier.getFirmId());
    }

}
