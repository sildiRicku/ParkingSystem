package com.example.training.transaction;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transactionss {
    @Id
    private int transId;
    @Column(name = "paymentType")
    private String paymentType;
    @Column(name = "platenumber")
    private String plateNumber;
    @Column(name = "dateOfTransaction")
    private Date dateOfTransaction;
    @Column(name = "value")

    private double transactionValue;
    @Column(name = "transactionStatus")

    private String transactionStatus;

    public Transactionss() {
    }

    public Transactionss(int id, String paymentType, String plateNumber, Date dateOfTransaction, double value, String transactionStatus) {
        this.transId = id;
        this.paymentType = paymentType;
        this.plateNumber = plateNumber;
        this.dateOfTransaction = dateOfTransaction;
        this.transactionValue = value;
        this.transactionStatus = transactionStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Transactionss(double value) {
        this.transactionValue = value;
    }

    public int getId() {
        return transId;
    }

    public void setId(int id) {
        this.transId = id;
    }

    public double getValue() {
        return transactionValue;
    }

    public void setValue(double value) {
        this.transactionValue = value;
    }

    @Override
    public String toString() {
        return "Transaction value" + transactionValue;
    }
}
