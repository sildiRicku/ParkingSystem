package com.example.system.dto;

import com.example.system.models.ParkingSystem;
import com.example.system.enums.TransactionPaymentType;
import com.example.system.enums.TransactionStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private final LocalDateTime entryTime = LocalDateTime.now();
    @JsonIgnore
    private LocalDateTime estimatedExitTime;
    private double transactionValue;
    private TransactionStatus transactionStatus;
    private ParkingSystem parkingSystem;
}
