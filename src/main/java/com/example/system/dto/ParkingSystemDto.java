package com.example.system.dto;

import com.example.system.entities.Rule;
import com.example.system.entities.Transactions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingSystemDto {
    private int id;

    private String address;

    private String workingStatus;

    private Date firstInstallDate;

    private String lastUpdate;

    private double totalMoney;

    private String firmwareVersion;

    private List<Transactions> transactions;

    private List<Rule> rules;
}
