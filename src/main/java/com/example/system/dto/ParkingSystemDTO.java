package com.example.system.dto;

import com.example.system.models.Admin;
import com.example.system.models.ParkingSystem.workingStatus;

import com.example.system.models.Rule;
import com.example.system.models.Transaction;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ParkingSystemDTO {
    private int id;

    private String identifier;

    private String address;

    private workingStatus workingStatus;

    private Date firstInstallDate;

    private String lastUpdate;

    private double totalMoney;

    private String firmwareVersion;

    private List<Transaction> transactions;

    private List<Rule> rules;

    private Admin admin;

}
