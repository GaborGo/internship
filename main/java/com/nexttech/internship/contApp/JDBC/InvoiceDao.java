package com.nexttech.internship.contApp.JDBC;

import com.nexttech.internship.contApp.model.Invoice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceDao {
    private DBConnection conn = null;
    private Statement statement = null;

    public DBConnection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }



    public List<Invoice> getAllInvoicesByPaymentTypeForFirmWithId(String payType, int id) {
        conn = new DBConnection();
        statement = conn.getStatement();
        List<Invoice> invoices = new ArrayList<>();
        String selectSql =
                "SELECT * FROM contapp.invoices " +
                        "JOIN payments ON payments.invoice_ID = invoices.invoice_ID " +
                        "JOIN suppliers ON suppliers.supplier_ID = invoices.supplier_ID " +
                        "JOIN firms " +
                        "ON suppliers.firm_ID = firms.firm_ID " +
                        "AND payments.type = " + "'" + payType + "'" +
                        " AND firms.firm_ID = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            invoices = extractInvoicesFromResultSet(resultSet);
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        printInvoices(invoices);
        return invoices;
    }

    public List<Invoice> getAllScadentInvoicesOrderedByDueTimeForFirmWithId(int firmId) {
        conn = new DBConnection();
        statement = conn.getStatement();
        List<Invoice> invoices = new ArrayList<>();
        String selectSql = "SELECT * FROM contapp.invoices " +
                "JOIN suppliers " +
                "ON suppliers.supplier_ID = invoices.supplier_ID " +
                "JOIN firms " +
                "ON suppliers.firm_ID = firms.firm_ID " +
                "WHERE sold > 0 AND firms.firm_ID = " + firmId +
                " ORDER BY scadent_Date ASC;";

        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            invoices = extractInvoicesFromResultSet(resultSet);
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        printInvoices(invoices);
        return invoices;
    }

    public List<Invoice> getAllInvoicesFromSupplierForFirmWithId(String supplier, int firmId) {
        conn = new DBConnection();
        statement = conn.getStatement();
        List<Invoice> invoices = new ArrayList<>();
        String selectSql = "SELECT * FROM contapp.invoices " +
                "JOIN suppliers " +
                "ON suppliers.supplier_ID = invoices.supplier_ID " +
                "JOIN firms " +
                "ON suppliers.firm_ID = firms.firm_ID " +
                "WHERE suppliers.name = " + "'" + supplier + "'" +
                " AND firms.firm_ID = " + firmId;

        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            invoices = extractInvoicesFromResultSet(resultSet);
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        printInvoices(invoices);
        return invoices;
    }

    public List<Invoice> getAllInvoicesFromDateForSupplierForFirmWithId(String beginDate, String supplier, int firmId) {
        conn = new DBConnection();
        statement = conn.getStatement();
        List<Invoice> invoices = new ArrayList<>();
        String selectSql = "SELECT * FROM contapp.invoices " +
                "JOIN suppliers " +
                "ON suppliers.supplier_ID = invoices.supplier_ID " +
                "JOIN firms " +
                "ON suppliers.firm_ID = firms.firm_ID " +
                "WHERE suppliers.name =" + "'" + supplier + "' " +
                "AND invoices.emit_Date >= " + "'" + beginDate + "' " +
                "AND firms.firm_ID = " + firmId;

        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            invoices = extractInvoicesFromResultSet(resultSet);
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        printInvoices(invoices);
        return invoices;
    }

    public List<Invoice> extractInvoicesFromResultSet(ResultSet rs) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        while (rs.next()) {
            Invoice invoice = new Invoice();
            invoice.setId(rs.getInt("invoice_ID"));
            invoice.setNumber(rs.getInt("number"));
            invoice.setEmitDate(rs.getDate("emit_Date"));
            invoice.setScadentDate(rs.getDate("scadent_Date"));
            invoice.setValue(rs.getInt("value"));
            invoice.setSold(rs.getInt("sold"));
            invoice.setSupplierId(rs.getInt("supplier_ID"));

            invoices.add(invoice);
        }
        return invoices;
    }

    public void printInvoices(List<Invoice> invoices) {
        invoices.stream()
                .filter(n -> n.getId() != 0)
                .collect(Collectors.toList())
                .forEach(n -> System.out.println("Invoice nr:" + n.getNumber()
                        + " Invoice value:" + n.getValue() + " Emit date:" + n.getEmitDate()));
    }


}
