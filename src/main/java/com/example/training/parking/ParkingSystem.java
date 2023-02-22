package com.example.training.parking;

import com.example.training.rule.Rule;
import com.example.training.transaction.Transaction;

public class ParkingSystem {
    private String id;
    private double totalMoney;
    private String firmwareVersion;
    private Rule rule;
    private Transaction transaction;

    public ParkingSystem(String id, double totalMoney, String firmwareVersion, Rule rule,Transaction transaction) {
        this.id = id;
        this.totalMoney = totalMoney;
        this.firmwareVersion = firmwareVersion;
        this.rule = rule;
        this.transaction=transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public ParkingSystem() {
    }

    @Override
    public String toString() {
        return "ParkingSystem{" +
                "id='" + id + '\'' +
                ", total_money=" + totalMoney +
                ", firmware_version='" + firmwareVersion + '\'' +
                '}';
    }
}
