package com.example.system.helperclasses;

import com.example.system.dto.TransactionDTO;
import com.example.system.models.ParkingSystem;
import com.example.system.models.TransactionPaymentType;
import com.example.system.models.TransactionStatus;
import com.example.system.serviceimplementations.ParkingSystemServiceImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TransactionBuilder {
    private final ParkingSystemServiceImpl parkingSystemService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public TransactionBuilder(ParkingSystemServiceImpl parkingSystemService) {
        this.parkingSystemService = parkingSystemService;
    }

    public TransactionDTO buildTransactionDTO(ParkingSystem parkingSystem, TransactionPaymentType transactionPaymentType, LocalDateTime entryTime, double transactionValue, LocalDateTime exitTime, String plateNumber) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setParkingSystem(parkingSystem);
        transactionDTO.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionDTO.setTransactionPaymentType(transactionPaymentType);
        transactionDTO.setEntryTime(entryTime);
        transactionDTO.setTransactionValue(transactionValue);
        transactionDTO.setEstimatedExitTime(calculateExitTime(parkingSystem,transactionDTO));
        transactionDTO.setPlateNumber(plateNumber);
        return transactionDTO;
    }

    public LocalDateTime calculateExitTime(ParkingSystem parkingSystem,TransactionDTO transactionDTO){
        MutableDouble money = new MutableDouble(transactionDTO.getTransactionValue());
        LocalDateTime exitTime = parkingSystemService.calculateExitTime(transactionDTO.getEntryTime(),money,parkingSystem.getRules());
        return exitTime;
    }

}