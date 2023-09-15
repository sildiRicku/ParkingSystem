package com.example.system.helperclasses;

import com.example.system.dto.TransactionDTO;
import com.example.system.models.ParkingSystem;
import com.example.system.models.TransactionPaymentType;
import com.example.system.models.TransactionStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionBuilder {
    private final ExitTimeCalculator exitTimeCalculator;

    public TransactionBuilder(ExitTimeCalculator exitTimeCalculator) {
        this.exitTimeCalculator = exitTimeCalculator;
    }

    public TransactionDTO buildTransactionDTO(ParkingSystem parkingSystem, TransactionPaymentType transactionPaymentType, LocalDateTime entryTime, double transactionValue, String plateNumber) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setParkingSystem(parkingSystem);
        transactionDTO.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionDTO.setTransactionPaymentType(transactionPaymentType);
        transactionDTO.setEntryTime(entryTime);
        transactionDTO.setTransactionValue(transactionValue);
        transactionDTO.setEstimatedExitTime(exitTimeCalculator.calculateExitTime(entryTime, transactionValue, parkingSystem.getRules()));
        transactionDTO.setPlateNumber(plateNumber);
        return transactionDTO;
    }
}