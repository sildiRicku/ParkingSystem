package com.example.system.helperclasses;

import com.example.system.dto.TransactionDTO;
import com.example.system.models.ParkingSystem;
import com.example.system.models.TransactionPaymentType;
import com.example.system.models.TransactionStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransactionBuilder {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public TransactionDTO buildTransactionDTO(ParkingSystem parkingSystem, TransactionPaymentType transactionPaymentType, LocalDateTime entryTime, double transactionValue, String exitTime, String plateNumber) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setParkingSystem(parkingSystem);
        transactionDTO.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionDTO.setTransactionPaymentType(transactionPaymentType);
        transactionDTO.setEntryTime(entryTime);
        transactionDTO.setTransactionValue(transactionValue);
        transactionDTO.setExitTime(LocalDateTime.parse(exitTime, formatter));
        transactionDTO.setPlateNumber(plateNumber);
        return transactionDTO;
    }
}
