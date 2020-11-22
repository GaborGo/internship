package com.nexttech.internship.contApp.model;

public class Supplier {
    private int id;
    private String name;
    private int firmId;

    public Supplier() {}

    public Supplier(int id, String name, int firmId) {
        this.id = id;
        this.name = name;
        this.firmId = firmId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }
}
