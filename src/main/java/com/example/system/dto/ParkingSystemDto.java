package com.example.system.dto;

import com.example.system.entities.ParkingSystem.workingStatus;
import com.example.system.entities.Rule;
import com.example.system.entities.Transactions;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingSystemDto {
    private int id;

    private String address;

    private workingStatus workingStatus;

    private Date firstInstallDate;

    private String lastUpdate;

    private double totalMoney;

    private String firmwareVersion;

    private List<Transactions> transactions;

    private List<Rule> rules;
}
