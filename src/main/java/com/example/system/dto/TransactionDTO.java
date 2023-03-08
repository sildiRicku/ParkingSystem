package com.example.system.dto;

import com.example.system.entities.ParkingSystem;
import com.example.system.entities.Transactions.PaymentType;
import com.example.system.entities.Transactions.TransactionStatus;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private int transId;
    private PaymentType paymentType;
    private String plateNumber;
    private Date dateOfTransaction;
    private double transactionValue;
    private TransactionStatus transactionStatus;
    private ParkingSystem parkingSystem;
}
