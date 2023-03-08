package com.example.system.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @SequenceGenerator(name = "SEQ_TransactionId", sequenceName = "SEQ_TransactionId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TransactionId")
    private int transId;
    @Enumerated(EnumType.STRING)
    @Column(name = "paymentType")
    private PaymentType paymentType;
    @Column(name = "platenumber")
    private String plateNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfTransaction")
    private Date dateOfTransaction;
    @Column(name = "value")
    private double transactionValue;
    @Enumerated(EnumType.STRING)
    @Column(name = "transactionStatus")
    private TransactionStatus transactionStatus;
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
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

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }


    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public enum PaymentType {
        CASH,
        CARD,
        MOBILE_WALLET
    }

    public enum TransactionStatus {
        READY,
        PROCESSING,
        ERROR,
        SUCCESS,
        DONE
    }
}
