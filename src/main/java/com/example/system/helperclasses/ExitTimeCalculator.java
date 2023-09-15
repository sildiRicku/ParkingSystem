package com.example.system.helperclasses;

import com.example.system.models.Rule;
import com.example.system.serviceimplementations.ParkingSystemServiceImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExitTimeCalculator {
    private final ParkingSystemServiceImpl parkingSystemServiceImplementation;

    public ExitTimeCalculator(ParkingSystemServiceImpl parkingSystemServiceImplementation) {
        this.parkingSystemServiceImplementation = parkingSystemServiceImplementation;
    }

    public LocalDateTime calculateExitTime(
            LocalDateTime entryTime,
            double transactionValue,
            List<Rule> rules
    ) {
        MutableDouble money = new MutableDouble(transactionValue);
        return parkingSystemServiceImplementation.calculateExitTime(entryTime, money, rules);
    }
}