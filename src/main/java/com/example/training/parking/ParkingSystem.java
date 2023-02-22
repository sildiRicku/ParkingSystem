/*
package com.example.training.parking;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.training.rule.Rule;
import com.example.training.transaction.Transactionss;

import java.util.Date;

@Entity(name = "ParkingSystem")
public class ParkingSystem {
    @Id
    private String id;
    private String address;
    private String workingStatus;
    private Date firstInstallDate;
    private String lastUpdate;
    private double totalMoney;
    private String firmwareVersion;
private Rule rule;
    private Transactionss transaction;


    public ParkingSystem(String id, double totalMoney, String firmwareVersion, Rule rule, Transaction transaction) {
        this.id = id;
        this.totalMoney = totalMoney;
        this.firmwareVersion = firmwareVersion;
   this.rule = rule;
        this.transaction = transaction;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    public Date getFirstInstallDate() {
        return firstInstallDate;
    }

    public void setFirstInstallDate(Date firstInstallDate) {
        this.firstInstallDate = firstInstallDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
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
        return "ParkingSystem{" + "id='" + id + '\'' + ", total_money=" + totalMoney + ", firmware_version='" + firmwareVersion + '\'' + '}';
    }
}
*/
