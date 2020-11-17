package com.nexttech.internship.contApp;

import java.sql.Date;

public class Payment {
    private int id;
    private String type;
    private Date date;
    private int value;
    private int invoiceId;

    public Payment(int id, String type, Date date, int value, int invoiceId) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.value = value;
        this.invoiceId = invoiceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
