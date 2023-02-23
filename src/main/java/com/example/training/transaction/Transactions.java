package com.example.training.transaction;


import com.example.training.parking.ParkingSystem;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @SequenceGenerator(name = "SEQ_TransactionId", sequenceName = "SEQ_TransactionId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TransactionId")
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
    @ManyToOne
    @JoinColumn(name = "parking_system_id")
    private ParkingSystem parkingSystem;

    public Transactions() {

    }

    public ParkingSystem getParkingSystem() {
        return parkingSystem;
    }

    public void setParkingSystem(ParkingSystem parkingSystem) {
        this.parkingSystem = parkingSystem;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Transactions(String paymentType, String plateNumber, double value, String transactionStatus) {
        this.paymentType = paymentType;
        this.plateNumber = plateNumber;
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


    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }

}
