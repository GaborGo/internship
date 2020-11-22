package com.nexttech.internship.contApp.model;

import java.sql.Date;

public class Invoice {
    private int id;
    private int number;
    private Date emitDate;
    private Date scadentDate;
    private int value;
    private int sold;
    private int supplierId;

    public Invoice() {}

    public Invoice(int id, int number, Date emitDate, Date scadentDate, int value, int sold, int supplierId) {
        this.id = id;
        this.number = number;
        this.emitDate = emitDate;
        this.scadentDate = scadentDate;
        this.value = value;
        this.sold = sold;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getEmitDate() {
        return emitDate;
    }

    public void setEmitDate(Date emitDate) {
        this.emitDate = emitDate;
    }

    public Date getScadentDate() {
        return scadentDate;
    }

    public void setScadentDate(Date scadentDate) {
        this.scadentDate = scadentDate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
