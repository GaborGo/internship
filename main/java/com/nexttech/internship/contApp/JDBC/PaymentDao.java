package com.nexttech.internship.contApp.JDBC;

import com.nexttech.internship.contApp.model.Payment;
import com.nexttech.internship.contApp.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentDao {

    private DBConnection conn = null;
    private Statement statement = null;

    public DBConnection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return statement;
    }

    public Map<String, Integer> getTotalPaymentsToSuppliersDescFromDateForFirmWithId(String beginDate, int firmId) {
        conn = new DBConnection();
        statement = conn.getStatement();
        Map results = new HashMap<String, Integer>();
        String selectSql = "SELECT  suppliers.name, SUM(payments.value) FROM payments " +
                "JOIN invoices ON invoices.invoice_ID = payments.invoice_ID " +
                "JOIN suppliers ON suppliers.supplier_ID = invoices.supplier_ID " +
                "JOIN firms ON firms.firm_ID = suppliers.firm_ID " +
                "WHERE firms.firm_ID = " + firmId +
                " AND invoices.emit_Date >= " + "'" + beginDate + "' " +
                "GROUP BY suppliers.name " +
                "ORDER BY SUM(payments.value) DESC;";
        try {
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                results.put(resultSet.getString("name"), resultSet.getString("SUM(payments.value)"));
            }
            System.out.println(results.entrySet());
        } catch (Exception ex) {
            System.out.println("Unable to retrieve results. " + ex);
        }
        return results;
    }


}

