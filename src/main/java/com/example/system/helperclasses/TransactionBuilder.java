package com.example.system.helperclasses;

import com.example.system.dto.TransactionDTO;
import com.example.system.exceptionhandlers.InvalidArgument;
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
        while (transactionPaymentType == TransactionPaymentType.CASH) {
            if (transactionValue <= 0) {
                throw new InvalidArgument("You can not use negative money input");
            } else {
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO.setParkingSystem(parkingSystem);
                transactionDTO.setTransactionStatus(TransactionStatus.SUCCESS);
                transactionDTO.setTransactionPaymentType(transactionPaymentType);
                transactionDTO.setTransactionValue(transactionValue);
                transactionDTO.setEstimatedExitTime(exitTimeCalculator.calculateExitTime(entryTime, transactionValue, parkingSystem.getRules()));
                transactionDTO.setPlateNumber(plateNumber);
                return transactionDTO;
            }
        }
        throw new InvalidArgument("Only Cash Acepted at the moment");
    }
}