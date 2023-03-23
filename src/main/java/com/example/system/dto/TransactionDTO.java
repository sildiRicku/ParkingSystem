package com.example.system.dto;

import com.example.system.entities.ParkingSystem;
import com.example.system.entities.TransactionPaymentType;
import com.example.system.entities.TransactionStatus;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private int transId;
    private TransactionPaymentType transactionPaymentType;
    private String plateNumber;
    private Date dateOfTransaction;
    private double transactionValue;
    private TransactionStatus transactionStatus;
    private ParkingSystem parkingSystem;
}
