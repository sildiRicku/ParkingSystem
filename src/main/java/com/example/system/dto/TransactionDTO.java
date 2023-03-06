package com.example.system.dto;

import com.example.system.entities.ParkingSystem;
import com.example.system.entities.Transactions.PaymentType;
import com.example.system.entities.Transactions.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
    private int transId;
    private PaymentType paymentType;
    private String plateNumber;
    private Date dateOfTransaction;
    private double transactionValue;
    private TransactionStatus transactionStatus;
    private ParkingSystem parkingSystem;
}
