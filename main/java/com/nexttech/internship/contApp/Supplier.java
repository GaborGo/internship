package com.nexttech.internship.contApp;

public class Supplier {
    private int id;
    private String name;
    private int firmID;

    public Supplier(int id, String name, int firmID) {
        this.id = id;
        this.name = name;
        this.firmID = firmID;
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

    public int getFirmID() {
        return firmID;
    }

    public void setFirmID(int firmID) {
        this.firmID = firmID;
    }
}
