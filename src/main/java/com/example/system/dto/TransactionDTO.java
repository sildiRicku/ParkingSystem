package com.example.system.dto;

import com.example.system.models.ParkingSystem;
import com.example.system.models.TransactionPaymentType;
import com.example.system.models.TransactionStatus;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private int transactionId;
    private TransactionPaymentType transactionPaymentType;
    private String plateNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double transactionValue;
    private TransactionStatus transactionStatus;
    private ParkingSystem parkingSystem;
}
