package com.nexttech.internship.contApp.model;

public class Income {
  private int id;
    private int type;
    private int number;
    private int value;
    private int firmId;

    public Income(int id, int type, int number, int value, int firmId) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.value = value;
        this.firmId = firmId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }
}

